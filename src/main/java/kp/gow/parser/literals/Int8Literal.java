/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser.literals;

/**
 *
 * @author Asus
 */
public final class Int8Literal extends Constant
{
    private final byte value;
    
    public Int8Literal(byte value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.INT8; }
    
    @Override
    public final byte toInt8() { return value; }
}
