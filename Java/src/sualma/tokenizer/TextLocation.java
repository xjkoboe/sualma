package sualma.tokenizer;

/**
 * @author wijnand.schepens@gmail.com
 */
public class TextLocation
{
    public TextLocation(String line, int lineNumber, int colNumber)
    {
        this.line = line;
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("line ").append(lineNumber).append(":\n");
        sb.append(line).append('\n');
        for (int i = 0; i < colNumber; i++)
        {
            sb.append(' ');
        }
        sb.append("^");
        return sb.toString();
    }
    
    // TODO: filename, url, ...
    private String line;
    private int lineNumber;  // counting from 1
    private int colNumber;   // counting from 1
}
