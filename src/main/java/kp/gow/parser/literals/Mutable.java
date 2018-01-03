/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser.literals;

import kp.gow.parser.Literal;
import kp.gow.parser.ParsedCode;
import kp.gow.parser.UnparsedCodeList;

/**
 *
 * @author Asus
 */
public abstract class Mutable extends Literal
{
    private final Item[] items;
    
    Mutable(Item[] items)
    {
        if(items == null)
            throw new NullPointerException();
        this.items = items;
    }
    
    public final int getItemCount() { return items.length; }
    public final Item getItem(int index) { return items[index]; }
    
    @Override
    public final boolean isConstant() { return false; }

    @Override
    public final boolean isMutable() { return true; }
    
    
    public static final class Item
    {
        private final ParsedCode key;
        private final ParsedCode value;
        
        private Item(ParsedCode key, ParsedCode value)
        {
            if(key == null)
                throw new NullPointerException();
            if(value == null)
                throw new NullPointerException();
            this.key = key;
            this.value = value;
        }
        Item(UnparsedCodeList key, UnparsedCodeList value) { this(key.parse(), value.parse()); }
        private Item(ParsedCode value)
        {
            if(value == null)
                throw new NullPointerException();
            this.key = null;
            this.value = value;
        }
        Item(UnparsedCodeList value) { this(value.parse()); }
        
        public final boolean hasKey() { return key != null; }
        
        public final ParsedCode getKey() { return key; }
        public final ParsedCode getValue() { return value; }
        
        @Override
        public final String toString()
        {
            if(key != null)
                return key + ": " + value;
            return value.toString();
        }
    }
}
