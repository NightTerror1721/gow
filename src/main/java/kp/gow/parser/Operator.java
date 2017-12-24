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
        this.symbol = symbol;
        this.operands = operands;
    }
    
    @Override
    public CodeType getCodeType() { return CodeType.OPERATOR; }
}
