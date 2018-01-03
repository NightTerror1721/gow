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
public class Parenthesis extends Operand implements ParsedCode
{
    private final ParsedCode code;
    
    public Parenthesis(ParsedCode code)
    {
        if(code == null)
            throw new NullPointerException();
        this.code = code;
    }
    public Parenthesis(UnparsedCodeList list) { this(list.parse()); }
    
    public final ParsedCode getCode() { return code; }

    @Override
    public CodeType getCodeType() { return CodeType.PARENTHESIS; }
    
    @Override
    public final String toString() { return "(" + code.toString() + ")"; }
}
