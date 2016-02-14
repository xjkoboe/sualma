/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.parser;

import sualma.model.Bool;
import sualma.model.List;
import sualma.model.Name;
import sualma.model.Num;
import sualma.model.Obj;
import sualma.model.Call;
import sualma.model.Str;

/**
 * A Parser can be used to convert a string representation to a sualma object.
 */
public class Parser
{
    public Parser()
    {
        tokenizer = new Tokenizer("true", "false"); // TODO: reserved words...
    }
    
    public Obj parse(String text)
    {
        return parse(tokenizer.scan(text)); 
    }
    
    public Obj parse(Token[] tokens) // TODO: in principle also feasible with iterator (one-token lookahead)
    {
        this.tokens = tokens;
        this.currIndex = 0;
        return parseObj(-1);
    }
    
    public Obj parseObj(int precedence)
    {
        int ls = 1000000; 
        while (next().is(Token.Type.Indent))
            ls = getPrecedence(eat());
        
        int lastPrec = Integer.MAX_VALUE; 
       
        Obj res = parsePre();
        
        int nextPrec = getPrecedence(next());
        while (nextPrec > precedence)
        {
            if (nextPrec >= 300) // call
            {
                res = new Call(res, parseObj(nextPrec - 1)); // call is right assoc
            }
            else if (nextPrec >= 200) // + *
            {
                Token t = eat();
                int pr = isRightAssociative(t) ? nextPrec - 1 : nextPrec;
                res = new Call(new Name(t.getText()), 
                               new List().addElement(res).addElement(parseObj(pr)));
            }
            else if (nextPrec >= 100) // ; ,
            {
                // add to list
                eat();
                assert(nextPrec <= lastPrec);
                if (nextPrec != lastPrec)
                    res = new List().addElement(res);
                if (!next().is(")"))
                    ((List)res).addElement(parseObj(nextPrec));
                lastPrec = nextPrec;
            }
            else // ls
            {
                assert(next().is(Token.Type.Indent));
                if (nextPrec > ls)
                {
                    // call
                    res = new Call(res, parseObj(nextPrec - 1));
                }
                else if (nextPrec == ls)
                {
                    // add to list
                    if (ls < lastPrec)
                        res = new List().addElement(res);
                    ((List)res).addElement(parseObj(nextPrec));
                    lastPrec = ls;
                }
                else
                    throw new ParserException("Unexpected lower indent", next());
            }
            
            nextPrec = getPrecedence(next());
        }
        
        return res;
    }
    
    private Obj parsePre()
    {
        Token token = eat();
        assert(!token.is(Token.Type.Indent));
        
        if (token.is("true"))
            return new Bool(true);
        if (token.is("false"))
            return new Bool(false);
        if (token.is(Token.Type.Name))
            return new Name(token.getText());
        if (token.is(Token.Type.Number))
            return new Num(token.getText());
        if (token.is(Token.Type.String))
            return new Str(token.getText().substring(1, token.getText().length()-1));
        if (token.is("("))
        {
            Obj res = next().is(")") ? new List() : parseObj(-1);
            consume(")");
            return res;
        }
        if (token.is(Token.Type.EndOfText))
            throw new ParserException("parsePre: unexpected end of file", token);
        throw new ParserException("parsePre: unexpected token", token);
    }
    
    private int getPrecedence(Token token)
    {
        if (token.getType() == Token.Type.Indent)
            return token.getText().length();
        else if (token.is(";"))
            return 100;
        else if (token.is(","))
            return 101;
        else if (token.is("+") || token.is("-"))
            return 200;
        else if (token.is("*") || token.is("/"))
            return 201;
        else if (token.is("^"))
            return 202;
        else if (token.is(Token.Type.EndOfText) || token.is(")"))
            return -1;
        else
            return 300;
    }
    
    private boolean isRightAssociative(Token token)
    {
        return token.is("^");
    }
    
    protected Token consume(String expected)
    {
        if (!next().is(expected))
            throw new RuntimeException("Expected token " + expected 
                                       + " but found " + next());
        return eat();
    }
    
    protected Token eat()
    {
        return tokens[currIndex++];
    }

    protected Token next() // look ahead
    {
        return tokens[currIndex];
    }    
    
    private final Tokenizer tokenizer;
    private Token[] tokens;
    private int currIndex;
}