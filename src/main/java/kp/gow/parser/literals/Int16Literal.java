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
public final class Int16Literal extends Constant
{
    private final short value;
    
    public Int16Literal(short value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.INT16; }
    
    @Override
    public final short toInt16() { return value; }
}
