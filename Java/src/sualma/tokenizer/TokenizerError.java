/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.tokenizer;

public class TokenizerError extends TextError
{
    public TokenizerError(String msg, TextLocation location)
    {
        super(msg, location);
    }
}
