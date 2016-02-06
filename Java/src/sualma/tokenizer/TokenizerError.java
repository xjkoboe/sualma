package sualma.tokenizer;

/**
 * @author wijnand.schepens@gmail.com
 */
public class TokenizerError extends TextError
{
    public TokenizerError(String msg, TextLocation location)
    {
        super(msg, location);
    }
}
