/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

/**
 *
 * @author Asus
 */
public interface UnparsedCode extends Code
{
    default boolean isOperand() { return false; }
}
