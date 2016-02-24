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
    
    private Obj parseObj(int precedence)
    {
        int ls = Integer.MAX_VALUE; 
        while (next().is(Token.Type.Indent))
            ls = getPrecedence(eat());
        
        Obj res = parsePre();
        if (res == null)
            return null;
        
        int lastPrec = Integer.MAX_VALUE; 
        int nextPrec = getPrecedence(next());
        while (nextPrec > precedence)
        {
            if (nextPrec >= 500) // .
            {
                // TODO
            }
            else if (nextPrec >= 400) // call
            {
                Obj obj2 = parseObj(nextPrec); // call is left assoc., mainly because also used as list indexing
                if (obj2 == null)
                    throw new ParserException("Expected tail object", next()); // TODO: location
                res = new Call(res, obj2);
            }
            else if (nextPrec >= 200) // + - * / ^ =
            {
                Token t = eat();
                int pr = isRightAssociative(t) ? nextPrec - 1 : nextPrec;
                Obj obj2 = parseObj(pr);
                if (obj2 == null)
                    throw new ParserException("Expected second argument", next());
                if (t.is("="))
                {
                    if (!(res instanceof Name))
                        throw new ParserException("Expected name before '='", t); // TODO: location
                    if (res.getLabel() != null)
                        throw new ParserException("Object is already named", t); // TODO: location; maybe move to semantic
                    obj2.setLabel(((Name)res).getValue());
                    res = obj2;
                }
                else
                    res = new Call(new Name(t.getText()), 
                                   new List().addElement(res).addElement(obj2)); // TODO: location
            }
            else if (nextPrec >= 100) // ; ,
            {
                // add to list
                eat();
                assert(nextPrec <= lastPrec);
                if (nextPrec != lastPrec)
                    res = new List().addElement(res);
                Obj obj2 = parseObj(nextPrec);
                if (obj2 != null)
                    ((List)res).addElement(obj2);
                lastPrec = nextPrec;
            }
            else // ls
            {
                assert(next().is(Token.Type.Indent));
                if (nextPrec > ls)
                {
                    // call
                    Obj obj2 = parseObj(nextPrec - 1);
                    if (obj2 != null)
                        res = new Call(res, obj2);
                    // else ?
                }
                else if (nextPrec == ls)
                {
                    // add to list
                    if (ls < lastPrec)
                        res = new List().addElement(res);
                    Obj obj2 = parseObj(nextPrec);
                    if (obj2 != null)
                        ((List)res).addElement(obj2);
                    // else ?
                    lastPrec = ls;
                }
                else
                    return res;
                //throw new ParserException("Unexpected lower indent", next());
            }
            
            nextPrec = getPrecedence(next());
        }
        
        return res;
    }
    
    private Obj parsePre()
    {
        assert(!next().is(Token.Type.Indent));
        
        if (next().is("true"))
        {
            eat();
            return new Bool(true);
        }
        if (next().is("false"))
        {
            eat();
            return new Bool(false);
        }
        if (next().is(Token.Type.Name))
        {
            Token token = eat();
            return new Name(token.getText());
        }
        if (next().is(Token.Type.Number))
        {
            Token token = eat();
            return new Num(token.getText());
        }
        if (next().is(Token.Type.String))
        {
            Token token = eat();
            return new Str(token.getText().substring(1, token.getText().length()-1));
        }
        if (next().is("("))
        {
            Token token = eat();
            //Obj res = next().is(")") ? new List() : parseObj(-1); // TODO: allow indent before ')'
            Obj obj2 = parseObj(-1);
            Obj res = obj2 != null ? obj2 : new List();
            // check next indent is same as initial?
            while (next().is(Token.Type.Indent))
                eat();
            eat(")");
            return res;
        }
        return null;
//        if (token.is(Token.Type.EndOfText))
//            throw new ParserException("parsePre: unexpected end of file", token);
//        throw new ParserException("parsePre: unexpected token", token);
    }
    
    private int getPrecedence(Token token)
    {
        if (token.is(Token.Type.EndOfText) || token.is(")"))
            return -1;
        if (token.getType() == Token.Type.Indent)
            return token.getText().length();
        if (token.is(";"))
            return 100;
        if (token.is(","))
            return 101;
        if (token.is("="))
            return 200;
        if (token.is("+") || token.is("-"))
            return 300;
        if (token.is("*") || token.is("/"))
            return 310;
        if (token.is("^"))
            return 320;
        if (token.is("."))
            return 500;
        return 400; // call
    }
    
    private boolean isRightAssociative(Token token)
    {
        return token.is("^");
    }
    
    private Token eat(String expected)
    {
        if (!next().is(expected))
            throw new RuntimeException("Expected token " + expected 
                                       + " but found " + next());
        return eat();
    }
    
    private Token eat()
    {
        return tokens[currIndex++];
    }

    private Token next() // look ahead
    {
        return tokens[currIndex];
    }    
    
    private final Tokenizer tokenizer;
    private Token[] tokens;
    private int currIndex;
}