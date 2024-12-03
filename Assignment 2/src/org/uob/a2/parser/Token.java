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
//
//    public Token(TokenType type){
//        this.tokenType = type;
//    }
//
//    public Token(String tokenString){
//        if(tokenString.toLowerCase().equals("quit")){
//            tokenType = TokenType.QUIT;
//        }
//    }
//
//    public TokenType getTokenType(){
//        return this.tokenType;
//    }

    public Token(TokenType tokenType, String value){
        this.tokenType = tokenType;
        this.value = value;
    }

    public Token(TokenType tokenType){
        this.tokenType = tokenType;
    }

    public TokenType getTokenType(){
        return this.tokenType;
    }

    public String getValue(){
        //Retrieves value of this token if applicable
    }
   
}
