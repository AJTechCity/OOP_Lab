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

    private ArrayList<Token> tokens;

    public Tokeniser(){}

    public ArrayList<Token> getTokens(){
        return this.tokens;
    }

    public String sanitise(String s){
        //Santiise input string by converting it to lowercase and trimming whitespace
        return s.toLowerCase().trim();
    }

    public void tokenise(String s){
        //Tokenises the input string into a list of Token objects based on predefined keywords and patterns

        //Words that do not match keywords are treates as variables and assigned the VAR type
    }


   
}
