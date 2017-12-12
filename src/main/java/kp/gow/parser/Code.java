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
public interface Code
{
    CodeType getCodeType();
    
    default boolean is(CodeType type) { return getCodeType() == type; }
    default boolean is(CodeType type0, CodeType type1)
    {
        CodeType type = getCodeType();
        return type == type0 || type == type1;
    }
    default boolean is(CodeType type0, CodeType type1, CodeType type2)
    {
        CodeType type = getCodeType();
        return type == type0 || type == type1 || type == type2;
    }
    default boolean is(CodeType... types)
    {
        CodeType type = getCodeType();
        for(CodeType t : types)
            if(type == t)
                return true;
        return false;
    }
}
