package org.uob.a2.parser;

/**
 * Represents a token in the parsing process, consisting of a {@code TokenType} and an optional value.
 * 
 * <p>
 * Tokens are used to represent the smallest units of meaning in the command input,
 * such as keywords, or variables.
 * </p>
 */
public class Token {

    private TokenType tokenType;
    private String value;

    public Token(TokenType tokenType, String value){
        this.tokenType = tokenType;
        this.value = value;
    }

    public Token(TokenType tokenType){
        this.tokenType = tokenType;
        this.value = null;
    }

    public TokenType getTokenType(){
        return this.tokenType;
    }

    public String getValue(){
        return this.value;
    }

    public String toString(){
        return "Token {\n" +
                "Token Type: " + this.tokenType + "\n" +
                "Value: " + this.value + "\n}";
    }
   
}
