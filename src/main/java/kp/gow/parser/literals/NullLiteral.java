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
public final class NullLiteral extends Constant
{
    @Override
    public final LiteralType getLiteralType() { return LiteralType.NULL; }
    
}
