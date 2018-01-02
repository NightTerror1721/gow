/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

import kp.gow.parser.literals.*;

/**
 *
 * @author Asus
 */
public abstract class Literal extends Operand implements ParsedCode
{
    @Override
    public final CodeType getCodeType() { return CodeType.LITERAL; }
    
    public abstract LiteralType getLiteralType();
    
    public abstract boolean isConstant();
    public abstract boolean isMutable();
    
    public final boolean isNull() { return getLiteralType() == LiteralType.NULL; }
    public final boolean isInt8() { return getLiteralType() == LiteralType.INT8; }
    public final boolean isInt16() { return getLiteralType() == LiteralType.INT16; }
    public final boolean isInt32() { return getLiteralType() == LiteralType.INT32; }
    public final boolean isInt64() { return getLiteralType() == LiteralType.INT64; }
    public final boolean isFloat32() { return getLiteralType() == LiteralType.FLOAT32; }
    public final boolean isFloat64() { return getLiteralType() == LiteralType.FLOAT64; }
    public final boolean isChar() { return getLiteralType() == LiteralType.CHAR; }
    public final boolean isBool() { return getLiteralType() == LiteralType.BOOL; }
    public final boolean isString() { return getLiteralType() == LiteralType.STRING; }
    public final boolean isArray() { return getLiteralType() == LiteralType.ARRAY; }
    public final boolean isList() { return getLiteralType() == LiteralType.LIST; }
    public final boolean isSet() { return getLiteralType() == LiteralType.SET; }
    public final boolean isMap() { return getLiteralType() == LiteralType.MAP; }
    
    public enum LiteralType { NULL, INT8, INT16, INT32, INT64, FLOAT32, FLOAT64, CHAR, BOOL, STRING, ARRAY, LIST, SET, MAP; }
    
    
    
    public static final Literal NULL = new NullLiteral();
    public static final Literal TRUE = new BoolLiteral(true);
    public static final Literal FALSE = new BoolLiteral(false);
    public static final Literal EMPTY_STRING = new StringLiteral("");
    
    
    public static final Literal literalOf() { return NULL; }
    public static final Literal literalOf(byte value) { return new Int8Literal(value); }
    public static final Literal literalOf(short value) { return new Int16Literal(value); }
    public static final Literal literalOf(int value) { return new Int32Literal(value); }
    public static final Literal literalOf(long value) { return new Int64Literal(value); }
    public static final Literal literalOf(float value) { return new Float32Literal(value); }
    public static final Literal literalOf(double value) { return new Float64Literal(value); }
    public static final Literal literalOf(char value) { return new CharLiteral(value); }
    public static final Literal literalOf(boolean value) { return value ? TRUE : FALSE; }
    public static final Literal literalOf(String value) { return value.isEmpty() ? EMPTY_STRING : new StringLiteral(value); }
    
    
}
