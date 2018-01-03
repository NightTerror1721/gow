/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser.literals;

import kp.gow.exception.CompilationError;
import kp.gow.parser.Separator;
import kp.gow.parser.UnparsedCodeList;

/**
 *
 * @author Asus
 */
public final class ListLiteral extends Mutable
{
    public ListLiteral(UnparsedCodeList list) throws CompilationError
    {
        super(parse(list));
    }
    
    private static Item[] parse(UnparsedCodeList list) throws CompilationError
    {
        UnparsedCodeList[] parts = list.split(Separator.COMMA);
        return UnparsedCodeList.mapArray(parts, t -> new Item(t), new Item[parts.length]);
    }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.LIST; }
}
