/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

import java.util.HashMap;

/**
 *
 * @author Asus
 */
public final class Separator implements UnparsedCode, ParsedCode
{
    private final String symbol;
    
    private Separator(String symbol) { this.symbol = symbol; }
    
    @Override
    public String toString() { return symbol; }
    
    @Override
    public final CodeType getCodeType() { return CodeType.SEPARATOR; }
    
    public static final Separator
            COMMA = new Separator(","),
            COLON = new Separator(";"),
            TWO_POINTS = new Separator(":");
    
    private static final HashMap<String, Separator> HASH = Utils.catchFields(Separator.class, new HashMap<>(), e -> e.symbol);
    
    public static final boolean isSeparator(String str) { return HASH.containsKey(str); }
    
    public static final Separator getSeparator(String str) { return HASH.get(str); }
}
