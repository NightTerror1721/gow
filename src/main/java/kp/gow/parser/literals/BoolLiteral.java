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
public final class BoolLiteral extends Constant
{
    private final boolean value;
    
    public BoolLiteral(boolean value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.BOOL; }
    
    @Override
    public final boolean toBool() { return value; }
}
