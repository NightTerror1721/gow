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
public final class Int32Literal extends Constant
{
    private final int value;
    
    public Int32Literal(int value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.INT32; }
    
    @Override
    public final int toInt32() { return value; }
}
