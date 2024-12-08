package org.uob.a2.parser;

import java.util.ArrayList;

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser {

    //Converts a string input into a list of tokens that can be parsed into commands by the game

    private ArrayList<Token> tokens;

    public Tokeniser(){
        this.tokens = new ArrayList<Token>();
    }

    public ArrayList<Token> getTokens(){
        return this.tokens;
    }

    public String sanitise(String s){
        //Santiise input string by converting it to lowercase and trimming whitespace
        return s.toLowerCase().replaceAll("\\s+", " ").trim();
    }

    public void tokenise(String s){
        //Reset old variables for when tokeniser is reused
        this.tokens = new ArrayList<Token>();
        //Tokenises the input string into a list of Token objects based on predefined keywords and patterns

        //Words that do not match keywords are treates as variables and assigned the VAR type

        String[] words = s.split(" ");
        for(String word : words){
            word = sanitise(word);
            Token newToken;
            switch(word){
                case "move":
                    newToken = new Token(TokenType.MOVE);
                    break;
                case "get":
                    newToken = new Token(TokenType.GET);
                    break;
                case "use":
                    newToken = new Token(TokenType.USE);
                    break;
                case "drop":
                    newToken = new Token(TokenType.DROP);
                    break;
                case "look":
                    newToken = new Token(TokenType.LOOK);
                    break;
                case "status":
                    newToken = new Token(TokenType.STATUS);
                    break;
                case "help":
                    newToken = new Token(TokenType.HELP);
                    break;
                case "quit":
                    newToken = new Token(TokenType.QUIT);
                    break;
                case "on":
                    newToken = new Token(TokenType.PREPOSITION, word);
                    break;
                case "with":
                    newToken = new Token(TokenType.PREPOSITION, word);
                case "using":
                    newToken = new Token(TokenType.PREPOSITION, word);
                    break;
                default:
                    newToken = new Token(TokenType.VAR, word);
                    break;
            }
            tokens.add(newToken);
        }
        tokens.add(new Token(TokenType.EOL));
    }
}
