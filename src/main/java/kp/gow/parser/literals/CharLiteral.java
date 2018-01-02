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
public final class CharLiteral extends Constant
{
    private final char value;
    
    public CharLiteral(char value) { this.value = value; }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.CHAR; }
    
    @Override
    public final char toChar() { return value; }
}
