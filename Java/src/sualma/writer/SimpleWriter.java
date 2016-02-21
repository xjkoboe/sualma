/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.writer;

import sualma.model.Obj;

/**
 * SimpleWriter converts a sualma object to a string-representation.
 * 
 * Uses brackets for every list and call,
 * and binary operators are represented as function calls.
 */
public final class SimpleWriter
{
    public static String toString(Obj obj)
    {
        return obj.toString();
    }
}