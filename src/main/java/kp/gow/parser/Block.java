/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

import java.util.function.Consumer;
import kp.gow.exception.CompilationError;

/**
 *
 * @author mpasc
 * @param <C>
 */
public abstract class Block<C extends ParsedCode> implements UnparsedCode, ParsedCode
{
    private Block() {}
    
    public abstract int getCodeCount();
    public abstract C getCode(int index);
    public abstract ParsedCode[] toArray();
    public abstract ParsedCode[] putInArray(ParsedCode[] array, int offset);
    public abstract void forEach(Consumer<C> consumer) throws CompilationError;
    
    public final boolean isScope() { return getCodeType() == CodeType.SCOPE; }
    public final boolean isParenthesis() { return getCodeType() == CodeType.PARENTHESIS; }
    public final boolean isSquare() { return getCodeType() == CodeType.SQUARE; }
    public final boolean isArgumentsList() { return getCodeType() == CodeType.ARGUMENTS_LIST; }
    
    @Override
    public final boolean isOperand() { return is(CodeType.PARENTHESIS, CodeType.ARGUMENTS_LIST); }
}
