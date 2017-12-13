/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.exception;

/**
 *
 * @author Asus
 */
public class CompilationError extends Exception
{
    public CompilationError(String message) { super(message); }
    public CompilationError(String message, Throwable cause) { super(message, cause); }
}
