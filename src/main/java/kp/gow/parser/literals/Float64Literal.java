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
public final class Float64Literal extends Constant
{
    private final double value;
    
    public Float64Literal(double value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.FLOAT64; }
    
    @Override
    public final double toFloat64() { return value; }
}
