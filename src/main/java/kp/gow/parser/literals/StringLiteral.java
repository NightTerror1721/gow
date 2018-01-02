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
public final class StringLiteral extends Constant
{
    private final String value;
    
    public StringLiteral(String value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.STRING; }
    
    @Override
    public final String toString() { return value; }
}
