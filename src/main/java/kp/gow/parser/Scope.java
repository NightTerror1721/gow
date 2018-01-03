/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

import java.util.Iterator;
import java.util.StringJoiner;

/**
 *
 * @author Asus
 */
public class Scope implements UnparsedCode, ParsedCode, Iterable<Command>
{
    private final Command[] commands;
    
    public Scope(Command... codes)
    {
        if(codes == null)
            throw new NullPointerException();
        this.commands = codes;
    }
    
    public final int getCommandCount() { return commands.length; }
    public final Command getCommand(int index) { return commands[index]; }
    public final Command[] toArray()
    {
        Command[] array = new Command[commands.length];
        System.arraycopy(commands, 0, array, 0, commands.length);
        return array;
    }
    public final Command[] putInArray(Command[] array, int offset)
    {
        System.arraycopy(commands, 0, array, offset, commands.length);
        return array;
    }
    
    @Override
    public final boolean isOperand() { return false; }

    @Override
    public final CodeType getCodeType() { return CodeType.SCOPE; }

    @Override
    public final ScopeIterator iterator() { return new ScopeIterator(); }
    
    @Override
    public final String toString()
    {
        StringJoiner joiner = new StringJoiner(", ");
        for(Command cmd : commands)
            joiner.add(cmd.toString());
        return "(" + joiner + ")";
    }
    
    public final class ScopeIterator implements Iterator<Command>
    {
        private int it = 0;

        @Override
        public final boolean hasNext() { return it < commands.length; }

        @Override
        public final Command next() { return commands[it++]; }

        public final Command peek() { return commands[it - 1]; }

        public final Command peekNext() { return commands[it]; }
    }
}
