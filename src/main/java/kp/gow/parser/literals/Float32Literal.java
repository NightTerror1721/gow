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
public final class Float32Literal extends Constant
{
    private final float value;
    
    public Float32Literal(float value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.FLOAT32; }
    
    @Override
    public final float toFloat32() { return value; }
}
