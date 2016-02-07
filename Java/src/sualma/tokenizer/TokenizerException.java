/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.tokenizer;

public class TokenizerException extends TextError
{
    public TokenizerException(String msg, TextLocation location)
    {
        super(msg, location);
    }
}
