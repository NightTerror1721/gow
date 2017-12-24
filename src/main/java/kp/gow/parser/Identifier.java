/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

import java.util.regex.Pattern;
import kp.gow.exception.CompilationError;
import kp.gow.lang.types.GOWType;

/**
 *
 * @author Asus
 */
public class Identifier implements UnparsedCode, ParsedCode
{
    private final String identifier;
    private final GOWType type;
    
    public Identifier(String identifier, GOWType type) throws CompilationError
    {
        if(type == null)
            throw new NullPointerException();
        if(identifier == null)
            throw new NullPointerException();
        if(identifier.isEmpty())
            throw new CompilationError("Invalid empty identifier");
        this.identifier = checkIsValid(identifier);
        this.type = type;
    }
    
    public final String getIdentifier() { return identifier; }
    
    @Override
    public final String toString() { return identifier; }
    
    @Override
    public CodeType getCodeType() { return CodeType.IDENTIFIER; }
    
    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");
    public static final boolean isValidIdentifier(String name) { return IDENTIFIER_PATTERN.matcher(name).matches(); }
    
    public static final String checkIsValid(String identifier) throws CompilationError
    {
        if(!isValidIdentifier(identifier))
            throw new CompilationError("Invalid identifier format: Requred: " + IDENTIFIER_PATTERN.pattern());
        return identifier;
    }
}
