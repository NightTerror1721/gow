/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser.literals;

import kp.gow.parser.Literal;

/**
 *
 * @author Asus
 */
public abstract class Mutable extends Literal
{
    final Item[] items;
    
    @Override
    public final boolean isConstant() { return false; }

    @Override
    public final boolean isMutable() { return true; }
    
    
    public final class Item
    {
        //private final 
    }
}
