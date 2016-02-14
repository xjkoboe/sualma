/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.parser;

public class TokenizerException extends TextException
{
    public TokenizerException(String msg, TextLocation location)
    {
        super(msg, location);
    }
}
