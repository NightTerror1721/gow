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
public final class Int64Literal extends Constant
{
    private final long value;
    
    public Int64Literal(long value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.INT64; }
    
    @Override
    public final long toInt64() { return value; }
}
