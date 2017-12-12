/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Asus
 */
public final class CodeList
{
    private final UnparsedCode[] code;
    
    public CodeList(UnparsedCode... code) { this.code = check(code); }
    public CodeList(Collection<? extends UnparsedCode> c) { this(c.toArray(new UnparsedCode[c.size()])); }
    public CodeList(UnparsedCode[] code, int off, int len)
    {
        this.code = new UnparsedCode[len];
        System.arraycopy(check(code), off, this.code, 0, len);
    }
    private CodeList(UnparsedCode[] code, boolean dummy) { this.code = code; }
    private CodeList(UnparsedCode[] code, int off, int len, boolean dummy)
    {
        this.code = new UnparsedCode[len];
        System.arraycopy(code, off, this.code, 0, len);
    }
    
    public final CodeList copy() { return subList(0, code.length); }
    
    public final int length() { return code.length; }
    public final boolean isEmpty() { return code.length == 0; }
    
    public final void set(int index, UnparsedCode code)
    {
        if(code == null)
            throw new NullPointerException();
        this.code[index] = code;
    }
    
    public final <UC extends UnparsedCode> UC get(int index) { return (UC) code[index]; }
    
    public final CodeList subList(int offset, int length) { return new CodeList(code, offset, length, false); }
    public final CodeList subList(int offset) { return subList(offset, code.length - offset); }
    
    public final CodeList concat(CodeList clist) { return concat(clist.code); }
    public final CodeList concat(Collection<? extends UnparsedCode> c) { return concat(c.toArray(new UnparsedCode[c.size()])); }
    public final CodeList concat(UnparsedCode... code)
    {
        UnparsedCode[] array = new UnparsedCode[this.code.length + code.length];
        System.arraycopy(this.code, 0, array, 0, this.code.length);
        System.arraycopy(check(code), 0, array, this.code.length, code.length);
        return new CodeList(array, false);
    }
    public final CodeList concat(UnparsedCode code)
    {
        if(code == null)
            throw new NullPointerException();
        UnparsedCode[] array = new UnparsedCode[this.code.length + 1];
        System.arraycopy(this.code, 0, array, 0, this.code.length);
        array[array.length - 1] = code;
        return new CodeList(array, false);
    }
    
    public final CodeList concatFirst(CodeList clist) { return concatFirst(clist.code); }
    public final CodeList concatFirst(Collection<? extends UnparsedCode> c) { return concatFirst(c.toArray(new UnparsedCode[c.size()])); }
    public final CodeList concatFirst(UnparsedCode... code)
    {
        UnparsedCode[] array = new UnparsedCode[this.code.length + code.length];
        System.arraycopy(check(code), 0, array, 0, code.length);
        System.arraycopy(this.code, 0, array, code.length, this.code.length);
        return new CodeList(array, false);
    }
    public final CodeList concatFirst(UnparsedCode code)
    {
        if(code == null)
            throw new NullPointerException();
        UnparsedCode[] array = new UnparsedCode[this.code.length + 1];
        System.arraycopy(this.code, 0, array, 1, this.code.length);
        array[0] = code;
        return new CodeList(array, false);
    }
    
    public final CodeList concatMiddle(int index, CodeList clist) { return concatMiddle(index, clist.code); }
    public final CodeList concatMiddle(int index, Collection<? extends UnparsedCode> c) { return concatMiddle(index, c.toArray(new UnparsedCode[c.size()])); }
    public final CodeList concatMiddle(int index, UnparsedCode... code)
    {
        if(index < 0 || index > this.code.length)
            throw new IllegalArgumentException("Index out of range: " + index);
        if(index == 0)
            return concatFirst(code);
        else if(index == this.code.length)
            return concat(code);
        UnparsedCode[] array = new UnparsedCode[this.code.length + code.length];
        System.arraycopy(this.code, 0, array, 0, index);
        System.arraycopy(check(code), 0, array, index, code.length);
        System.arraycopy(this.code, index, array, index + code.length, this.code.length - index);
        return new CodeList(array, false);
    }
    public final CodeList concatMiddle(int index, UnparsedCode code)
    {
        if(code == null)
            throw new NullPointerException();
        if(index < 0 || index > this.code.length)
            throw new IllegalArgumentException("Index out of range: " + index);
        if(index == 0)
            return concatFirst(code);
        else if(index == this.code.length)
            return concat(code);
        UnparsedCode[] array = new UnparsedCode[this.code.length + 1];
        System.arraycopy(this.code, 0, array, 0, index);
        System.arraycopy(this.code, index, array, index + 1, this.code.length - index);
        array[index] = code;
        return new CodeList(array, false);
    }
    
    public final CodeList wrapBetween(CodeList before, CodeList after) { return wrapBetween(before.code, after.code); }
    public final CodeList wrapBetween(UnparsedCode before, UnparsedCode after)
    {
        if(before == null)
            throw new NullPointerException();
        if(after == null)
            throw new NullPointerException();
        UnparsedCode[] array = new UnparsedCode[code.length + 2];
        System.arraycopy(code, 0, array, 1, code.length);
        array[0] = before;
        array[array.length - 1] = after;
        return new CodeList(array, false);
    }
    public final CodeList wrapBetween(UnparsedCode[] before, UnparsedCode[] after)
    {
        UnparsedCode[] array = new UnparsedCode[before.length + code.length + after.length];
        System.arraycopy(check(before), 0, array, 0, before.length);
        System.arraycopy(code, 0, array, before.length, code.length);
        System.arraycopy(check(after), 0, array, before.length + array.length, after.length);
        return new CodeList(array, false);
    }
    
    public final CodeList extract(UnparsedCode from, UnparsedCode to)
    {
        boolean init = false;
        int offset = -1, len = -1, idx = -1;
        for(UnparsedCode c : code)
        {
            idx++;
            if(!init)
            {
                if(!c.equals(from))
                    continue;
                init = true;
                offset = idx;
                continue;
            }
            if(c.equals(to))
                break;
            len++;
        }
        return subList(offset, len);
    }
    
    public final int count(UnparsedCode codePart)
    {
        int count = 0;
        for(Code c : code)
            if(c.equals(codePart))
                count++;
        return count;
    }
    
    public final boolean has(UnparsedCode codePart)
    {
        for(UnparsedCode cp : code)
            if(cp.equals(codePart))
                return true;
        return false;
    }
    
    public final int count(CodeType type)
    {
        int count = 0;
        for(Code c : code)
            if(c.getCodeType() == type)
                count++;
        return count;
    }
    
    public final int indexOf(UnparsedCode code)
    {
        for(int i=0;i<this.code.length;i++)
            if(this.code[i].equals(code))
                return i;
        return -1;
    }
    
    public final int indexOf(CodeType type)
    {
        for(int i=0;i<code.length;i++)
            if(code[i].getCodeType() == type)
                return i;
        return -1;
    }
    
    public final CodeList[] split(UnparsedCode separator) { return split(separator, -1); }
    public final CodeList[] split(UnparsedCode separator, int limit)
    {
        if(code.length == 0)
            return new CodeList[] { this };
        if(limit == 1)
            return new CodeList[] { copy() };
        LinkedList<CodeList> parts = new LinkedList<>();
        limit = limit < 1 ? -1 : limit;
        int i, off;
        for(i=0, off=0;i<code.length;i++)
            if(code[i].equals(separator) && limit != 0)
            {
                parts.add(subList(off, i - off));
                off = i + 1;
                limit--;
            }
        if(i > off)
            parts.add(subList(off, i - off));
        return parts.toArray(new CodeList[parts.size()]);
    }
    
    @Override
    public final String toString() { return Arrays.toString(code); }
    
    private static UnparsedCode[] check(UnparsedCode[] code)
    {
        for(UnparsedCode c : code)
            if(c == null)
                throw new NullPointerException();
        return code;
    }
    
    private static final CodeList EMPTY = new CodeList(new UnparsedCode[0], false);
    public static final CodeList empty() { return EMPTY; }
}
