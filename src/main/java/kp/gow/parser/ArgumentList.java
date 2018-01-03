/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

import java.util.Iterator;
import java.util.StringJoiner;
import kp.gow.exception.CompilationError;

/**
 *
 * @author mpasc
 * @param <C>
 */
public class ArgumentList implements UnparsedCode, ParsedCode, Iterable<ParsedCode>
{
    private final ParsedCode[] code;
    
    
    public ArgumentList(ParsedCode... codes)
    {
        if(codes == null)
            throw new NullPointerException();
        this.code = codes;
    }
    public ArgumentList(UnparsedCodeList list, Separator separator)
    {
        UnparsedCodeList[] lists = list.split(separator);
        if(lists.length == 1 && lists[0].isEmpty())
            code = new ParsedCode[0];
        else
        {
            code = new ParsedCode[lists.length];
            for(int i=0;i<lists.length;i++)
                code[i] = lists[i].parse();
        }
    }
    
    public final int getCodeCount() { return code.length; }
    public final ParsedCode getCode(int index) { return code[index]; }
    public final ParsedCode[] toArray()
    {
        ParsedCode[] array = new ParsedCode[code.length];
        System.arraycopy(code, 0, array, 0, code.length);
        return array;
    }
    public final ParsedCode[] putInArray(ParsedCode[] array, int offset)
    {
        System.arraycopy(code, 0, array, offset, code.length);
        return array;
    }
    
    @Override
    public final boolean isOperand() { return true; }

    @Override
    public final CodeType getCodeType() { return CodeType.ARGUMENT_LIST; }

    @Override
    public final Iterator<ParsedCode> iterator()
    {
        return new Iterator<ParsedCode>()
        {
            private int it = 0;
            
            @Override
            public final boolean hasNext() { return it < code.length; }

            @Override
            public final ParsedCode next() { return code[it++]; }
            
        };
    }
    
    @Override
    public final String toString()
    {
        StringJoiner joiner = new StringJoiner(", ");
        for(ParsedCode code1 : code)
            joiner.add(code1.toString());
        return "(" + joiner + ")";
    }
    
    
    public static final ArgumentList toFor(UnparsedCodeList list) throws CompilationError
    {
        int colons = list.count(Separator.COLON);
        switch(colons)
        {
            case 2: {
                UnparsedCodeList[] parts = list.split(Separator.COLON);
                if(parts.length != 3)
                    throw new CompilationError("Malformed \"for\" structure: for(code;code;code) or for(<type> code : code)");
                Command initPart;
                if(!parts[0].isEmpty())
                {
                    initPart = Command.parse(parts[0]);
                }
                else initPart = null;
            } break;
            case 0: {
                
            } break;
            default: throw new CompilationError("Malformed \"for\" structure: for(code;code;code) or for(<type> code : code)");
        }
    }
}
