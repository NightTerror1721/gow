/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

/**
 *
 * @author mpasc
 */
public abstract class Operand implements UnparsedCode
{
    @Override
    public final boolean isOperand() { return true; }
}
