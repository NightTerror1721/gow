/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

/**
 *
 * @author mpasc
 */
public class Operator implements ParsedCode
{
    private final OperatorSymbol symbol;
    private final ParsedCode[] operands;
    
    private Operator(OperatorSymbol symbol, ParsedCode... operands)
    {
        if(symbol == null)
            throw new NullPointerException();
        if(operands.length < 1)
            throw new IllegalStateException();
        for(ParsedCode operand : operands)
            if(operand == null) 
                throw new NullPointerException();
        this.symbol = symbol;
        this.operands = operands;
    }
    
    public final boolean isUnary() { return symbol.isUnary(); }
    public final boolean isBinary() { return symbol.isBinary(); }
    public final boolean isTernary() { return symbol.isTernary(); }
    public final boolean isCall() { return symbol.isCall(); }
    public final boolean isInvoke() { return symbol.isInvoke(); }
    public final boolean isNew() { return symbol.isNew(); }
    public final boolean isAssignation() { return symbol.isAssignation(); }
    
    public final OperatorSymbol getSymbol() { return symbol; }
    
    public final int getOperandCount() { return operands.length; }
    public final ParsedCode getOperand(int index) { return operands[index]; }
    public final void setOperand(int index, ParsedCode operand)
    {
        if(operand == null)
            throw new NullPointerException();
        operands[index] = operand;
    }
    
    public final boolean isLeftOrder() { return symbol.isLeftOrder(); }
    public final boolean isRightOrder() { return symbol.isRightOrder(); }
    
    @Override
    public CodeType getCodeType() { return CodeType.OPERATOR; }
    
    @Override
    public final String toString() { return symbol.toString(); }
    
    
    public static final Operator unary(OperatorSymbol symbol, ParsedCode op0)
    {
        return new Operator(symbol, op0);
    }
    
    public static final Operator binary(OperatorSymbol symbol, ParsedCode op0, ParsedCode op1)
    {
        return new Operator(symbol, op0, op1);
    }
    
    public static final Operator ternary(OperatorSymbol symbol, ParsedCode op0, ParsedCode op1, ParsedCode op2)
    {
        return new Operator(symbol, op0, op1, op2);
    }
    
    public static final Operator callOperator(Identifier functionName, ArgumentList parameters)
    {
        if(functionName == null)
            throw new NullPointerException();
        if(parameters == null)
            throw new NullPointerException();
        ParsedCode[] code = new ParsedCode[parameters.getCodeCount() + 1];
        code[0] = functionName;
        parameters.putInArray(code, 1);
        return new Operator(OperatorSymbol.CALL, code);
    }
    
    public static final Operator invokeOperator(ParsedCode invoker, Identifier methodName, ArgumentList parameters)
    {
        if(invoker == null)
            throw new NullPointerException();
        if(methodName == null)
            throw new NullPointerException();
        if(parameters == null)
            throw new NullPointerException();
        ParsedCode[] code = new ParsedCode[parameters.getCodeCount() + 2];
        code[0] = invoker;
        code[1] = methodName;
        parameters.putInArray(code, 2);
        return new Operator(OperatorSymbol.INVOKE, code);
    }
    
    public static final Operator newOperator(Identifier className, ArgumentList parameters)
    {
        if(className == null)
            throw new NullPointerException();
        if(parameters == null)
            throw new NullPointerException();
        ParsedCode[] code = new ParsedCode[parameters.getCodeCount() + 1];
        code[0] = className;
        parameters.putInArray(code, 1);
        return new Operator(OperatorSymbol.CALL, code);
    }
}
