/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.tokenizer;

public class Token
{
    public enum Type
    {
        LeadingSpaces,
		Reserved,
        Number,
        String, 
        Name, 
        Bracket,
        Operator,
        EndOfText
    }

    public Token(String text, Type type)
    {
        this.text = text;
        this.type = type;
        this.location = null;
    }

    public Token(String text, Type type, TextLocation location)
    {
        this.text = text;
        this.type = type;
        this.location = location;
    }

    public String getText()
    {
        return text;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public TextLocation getLocation()
    {
        return location;
    }

    @Override
    public String toString()
    {
        return "token '" + getText() + "' (" + getType() + ")";
    }
    
    private String       text;
    private Type         type;
    private TextLocation location;
}
