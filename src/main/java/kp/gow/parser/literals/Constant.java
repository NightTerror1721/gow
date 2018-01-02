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
public abstract class Constant extends Literal
{
    @Override
    public final boolean isConstant() { return true; }
    
    @Override
    public final boolean isMutable() { return false; }
    
    public byte toInt8() { throw new IllegalStateException(); }
    public short toInt16() { throw new IllegalStateException(); }
    public int toInt32() { throw new IllegalStateException(); }
    public long toInt64() { throw new IllegalStateException(); }
    public float toFloat32() { throw new IllegalStateException(); }
    public double toFloat64() { throw new IllegalStateException(); }
    public char toChar() { throw new IllegalStateException(); }
    public boolean toBool() { throw new IllegalStateException(); }
    @Override public String toString() { throw new IllegalStateException(); }
}
