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
public final class MapLiteral extends Mutable
{
    public MapLiteral(UnparsedCodeList list) throws CompilationError
    {
        super(parse(list));
    }
    
    private static Item[] parse(UnparsedCodeList list) throws CompilationError
    {
        UnparsedCodeList[] parts = list.split(Separator.COMMA);
        return UnparsedCodeList.mapArray(parts, t -> {
            UnparsedCodeList[] mapped = t.split(Separator.TWO_POINTS);
            if(mapped.length != 2)
                throw new CompilationError("Malformed map literal.");
            return new Item(mapped[0], mapped[1]);
        }, new Item[parts.length]);
    }
    
    @Override
    public final LiteralType getLiteralType() { return LiteralType.MAP; }
}
