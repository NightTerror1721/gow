/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

/**
 *
 * @author Asus
 */
public abstract class OperatorSymbol implements UnparsedCode
{
    private final String symbol;
    private final Type type;
    private final Order order;
    private final int priority;
    private final String functionName;
    
    private OperatorSymbol(String symbol, Order order, Type type, int priority, String functionName)
    {
        this.symbol = symbol;
        this.order = order;
        this.type = type;
        this.priority = priority;
        this.functionName = functionName;
    }
    
    public final String getSymbol() { return symbol; }
    
    public final int getPriority() { return priority; }
    
    public final boolean hasAssociatedFunction() { return functionName != null; }
    public final String getAssociatedFunction() { return functionName; }
    
    public final int comparePriority(OperatorSymbol operatorSymbol)
    {
        int cmp = type.comparePriority(operatorSymbol.type);
        return cmp != 0 ? 0 : Integer.compare(priority, operatorSymbol.priority);
    }
    
    public final boolean isLeftOrder() { return order == Order.LEFT || order == Order.BOTH; }
    public final boolean isRightOrder() { return order == Order.RIGHT || order == Order.BOTH; }
    
    public OperatorSymbol getLinkedOperator() { return null; }
    
    public final boolean isUnary() { return type == Type.UNARY; }
    public final boolean isBinary() { return type == Type.BINARY; }
    public final boolean isTernary() { return type == Type.TERNARY; }
    public final boolean isCall() { return type == Type.CALL; }
    public final boolean isInvoke() { return type == Type.INVOKE; }
    public final boolean isNew() { return type == Type.NEW; }
    public final boolean isAssignation() { return type == Type.ASSIGNATION; }
    
    public final boolean isCallable() { return isCall() || isInvoke(); }
    
    @Override
    public final CodeType getCodeType() { return CodeType.OPERATOR_SYMBOL; }
    
    @Override
    public final String toString() { return symbol; }
    
    
    
    public static final OperatorSymbol
            PROPERTY_ACCESS = new BinaryOperatorSymbol(".", 14, null),
            ACCESS = new BinaryOperatorSymbol("[]", 14, null),
            NEW = new NewOperatorSymbol("new", 14, null),
            INVOKE = new InvokeOperatorSymbol(".()", 14, null),
            CALL = new CallOperatorSymbol("()", 14, null),
            
            NEGATE = new UnaryOperatorSymbol("!", 13, "negate"),
            LOGIC_NOT = new UnaryOperatorSymbol("~", 13, "logicNot"),
            NEGATIVE = new UnaryOperatorSymbol("-", 13, "negative"),
            INCREMENT = new UnaryOperatorSymbol("++", 13, true, "increase"),
            DECREMENT = new UnaryOperatorSymbol("--", 13, true, "decrease"),
            //TYPEOF = new UnaryOperatorSymbol("typeof", 13, null),
            //IMPORT = new UnaryOperatorSymbol("import", 13, null),
            
            MULTIPLY = new BinaryOperatorSymbol("*", 12, "multiply"),
            DIVIDE = new BinaryOperatorSymbol("/", 12, "divide"),
            MODULE = new BinaryOperatorSymbol("%", 12, "module"),
            
            PLUS = new BinaryOperatorSymbol("+", 11, "plus"),
            MINUS = new BinaryOperatorSymbol("-", 11 ,"minus"),
            
            SHIFT_LEFT = new BinaryOperatorSymbol("<<", 10, "shiftLeft"),
            SHIFT_RIGHT = new BinaryOperatorSymbol(">>", 10, "shiftRight"),
            
            LESS_THAN = new BinaryOperatorSymbol("<", 9, "smallerThan"),
            LESS_THAN_EQUALS = new BinaryOperatorSymbol("<=", 9, "smallerOrEqualsThan"),
            GREATER_THAN = new BinaryOperatorSymbol(">", 9, "greaterThan"),
            GREATER_THAN_EQUALS = new BinaryOperatorSymbol(">=", 9, "greaterOrEqualsThan"),
            CONTAINS = new BinaryOperatorSymbol("in", 9, "contains"),
            INSTANCEOF = new BinaryOperatorSymbol("instanceof", 9, null),
            
            EQUALS = new BinaryOperatorSymbol("==", 8, "equals"),
            NOT_EQUALS = new BinaryOperatorSymbol("!=", 8, "notEquals"),
            EQUALS_REFERENCE = new BinaryOperatorSymbol("===", 8, null),
            NOT_EQUALS_REFERENCE = new BinaryOperatorSymbol("!==", 8, null),
            
            LOGIC_AND = new BinaryOperatorSymbol("&", 7, "logicAnd"),
            
            LOGIC_XOR = new BinaryOperatorSymbol("^", 6, "logicXor"),
            
            LOGIC_OR = new BinaryOperatorSymbol("|", 5, "logicOr"),
            
            STRING_CONCAT = new BinaryOperatorSymbol("..", 4, null),
            
            AND = new BinaryOperatorSymbol("&&", 3, null),
            
            OR = new BinaryOperatorSymbol("||", 2, null),
            
            TERNARY_CONDITION = new TernaryOperatorSymbol("?", 1, null),
            
            ASSIGNATION = new AssignationSymbol("=", null),
            ASSIGNATION_PLUS = new AssignationSymbol("+=", PLUS),
            ASSIGNATION_MINUS = new AssignationSymbol("-=", MINUS),
            ASSIGNATION_MULTIPLY = new AssignationSymbol("*=", MULTIPLY),
            ASSIGNATION_DIVIDE = new AssignationSymbol("/=", DIVIDE),
            ASSIGNATION_MODULE = new AssignationSymbol("%=", MODULE),
            
            ASSIGNATION_SHIFT_LEFT = new AssignationSymbol("<<=", SHIFT_LEFT),
            ASSIGNATION_SHIFT_RIGHT = new AssignationSymbol(">>=", SHIFT_RIGHT),
            
            ASSIGNATION_LOGIC_AND = new AssignationSymbol("&=", LOGIC_AND),
            ASSIGNATION_LOGIC_OR = new AssignationSymbol("|=", LOGIC_OR),
            ASSIGNATION_LOGIC_XOR = new AssignationSymbol("^=", LOGIC_XOR);
    
    
    
    
    
    
    private enum Order { LEFT, RIGHT, BOTH; }
    private enum Type {
        UNARY(2),
        BINARY(2),
        TERNARY(1),
        CALL(2),
        INVOKE(2),
        NEW(2),
        ASSIGNATION(0);
        
        private final int priority;
        private Type(int priority) { this.priority = priority; }
        
        private int comparePriority(Type type) { return Integer.compare(priority, type.priority); }
    }
    
    private static final class UnaryOperatorSymbol extends OperatorSymbol
    {
        public UnaryOperatorSymbol(String symbol, int priority, boolean canBeBothOrder, String functionName)
        {
            super(symbol, canBeBothOrder ? Order.BOTH : Order.LEFT, Type.UNARY, priority, functionName);
        }
        public UnaryOperatorSymbol(String symbol, int priority, String functionName) { this(symbol, priority, false, functionName); }
    }
    
    private static final class BinaryOperatorSymbol extends OperatorSymbol
    {
        public BinaryOperatorSymbol(String symbol, int priority, String functionName)
        {
            super(symbol, Order.LEFT, Type.BINARY, priority, functionName);
        }
    }
    
    private static final class TernaryOperatorSymbol extends OperatorSymbol
    {
        public TernaryOperatorSymbol(String symbol, int priority, String functionName)
        {
            super(symbol, Order.LEFT, Type.TERNARY, priority, functionName);
        }
    }
    
    private static class InvokeOperatorSymbol extends OperatorSymbol
    {
        public InvokeOperatorSymbol(String symbol, int priority, String functionName)
        {
            super(symbol, Order.LEFT, Type.INVOKE, priority, functionName);
        }
    }
    
    private static class CallOperatorSymbol extends OperatorSymbol
    {
        public CallOperatorSymbol(String symbol, int priority, String functionName)
        {
            super(symbol, Order.LEFT, Type.CALL, priority, functionName);
        }
    }
    
    private static class NewOperatorSymbol extends OperatorSymbol
    {
        public NewOperatorSymbol(String symbol, int priority, String functionName)
        {
            super(symbol, Order.LEFT, Type.NEW, priority, functionName);
        }
    }
    
    private static class AssignationSymbol extends OperatorSymbol
    {
        private final OperatorSymbol linkedOperator;
        
        public AssignationSymbol(String symbol, OperatorSymbol linkedOperator)
        {
            super(symbol, Order.RIGHT, Type.ASSIGNATION, 0, null);
            this.linkedOperator = linkedOperator;
        }
        
        @Override
        public final OperatorSymbol getLinkedOperator() { return linkedOperator; }
    }
}
