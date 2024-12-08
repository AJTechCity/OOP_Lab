package org.uob.a2.parser;

import java.util.ArrayList;

import org.uob.a2.commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {

    public Command oldParse(ArrayList<Token> tokens) throws CommandErrorException{
        //Parse a list of tokens into a Command Object
        //THrow error if command is invalid

        if(tokens.isEmpty() || tokens.size() < 2) {
            throw new CommandErrorException("Invalid Command");
        }

        Token commandTypeToken = tokens.get(0); //First token is going to be the commnad type
        if(commandTypeToken.getTokenType() == TokenType.QUIT && tokens.get(1).getTokenType() == TokenType.EOL){
            return new Quit();
        }else if(commandTypeToken.getTokenType() == TokenType.HELP){
            if(tokens.size() == 2 && tokens.get(1).getTokenType() == TokenType.EOL){ // General Help Command
                return new Help(null);
            }else if(tokens.size()==3 && tokens.get(1).getTokenType() == TokenType.VAR && tokens.get(2).getTokenType()==TokenType.EOL){
                return new Help(tokens.get(1).getValue());
            }
            throw new CommandErrorException("Invalid Help Command");
        }else if(tokens.size() != 3 && tokens.size() != 5){ //All other commands are at least 3 tokens in length (including EOL token)
            throw new CommandErrorException("Command is of an incorrect length");
//            throw new CommandErrorException("Token list should contain 3 or 5 tokens");
        }

        Token prepositionToken=null, var1Token=null, var2Token=null, eolToken=null;

        if(commandTypeToken.getTokenType() != TokenType.USE && tokens.get(1).getTokenType() == TokenType.VAR){
            var1Token = tokens.get(1);
            eolToken = tokens.get(2);
        }else if(commandTypeToken.getTokenType() == TokenType.USE && tokens.get(1).getTokenType() == TokenType.VAR && tokens.get(2).getTokenType() == TokenType.PREPOSITION && tokens.get(3).getTokenType() == TokenType.VAR && tokens.get(4).getTokenType() == TokenType.EOL){
            //Catches Preposition / use command
            var1Token = tokens.get(1);
            prepositionToken = tokens.get(2);
            var2Token = tokens.get(3);
            eolToken = tokens.get(4);
        }else{
            throw new CommandErrorException("Use the command 'help use' for instructions on the 'use' command");
        }

        //If above passes, then the token list is likely to be valid
        switch(commandTypeToken.getTokenType()) {
            case DROP: //Tokens - <Command> <Item> <EOL>
                return new Drop(var1Token.getValue());
            case MOVE: //Tokens - <Command> <Distance> <EOL>
                return new Move(var1Token.getValue());
            case GET: //Tokens - <Command> <Item> <EOL>
                return new Get(var1Token.getValue());
            case LOOK: //Tokens - <Command> <Location> <EOL>
                return new Look(var1Token.getValue());
            case STATUS: //Tokens - <Command> < StatusItem> <EOL>
                return new Status(var1Token.getValue());
            case USE: //Tokens - <Command> <Item1 - Equipment> <Preposition> <Item2 - Target> <EOL>
                if (var1Token == null || var2Token == null || prepositionToken == null || eolToken == null) {
                    throw new CommandErrorException("USE command is missing required tokens");
                }
                return new Use(var1Token.getValue(), var2Token.getValue());
            default:
                throw new CommandErrorException("Invalid Command type: " + commandTypeToken.getTokenType());
        }


    }

    public Command parse(ArrayList<Token> tokens) throws CommandErrorException{
        //Begin with basic token checks (i.e. length of tokens, first token is a Command Type and last is an EOL token)
        int tokenListSize = tokens.size();
        if(tokens.isEmpty() || tokens.size() < 2) {
            throw new CommandErrorException("Command is the wrong length. Use the 'help' command to learn more");
        }

        if(tokens.get(tokens.size()-1).getTokenType() != TokenType.EOL){
            throw new CommandErrorException("Internal error - Last Token isn't EOL");
        }

        CommandType commandType;
        Token commandTypeToken = tokens.get(0); //This token should contain the command type

        try{
            commandType = CommandType.valueOf(commandTypeToken.getTokenType().name());
        }catch(IllegalArgumentException e){
            throw new CommandErrorException("Invalid Command action. Use the 'help' command to learn more");
        }

        //At this point, we know the last token is EOL and first is a valid CommandType token specifier

        switch(commandType){
            case MOVE: //Done
                return parseMoveCommand(tokenListSize, tokens);
            case USE:
                break;
            case GET:
                break;
            case DROP:
                break;
            case LOOK:
                break;
            case STATUS:
                break;
            case HELP: //Done
                return parseHelpCommand(tokenListSize, tokens);
            case QUIT:
                //For quit command, we can just return a new Quit() Object
                return new Quit();
            default:
                //Only to be reached if commandType is unknown (should be impossible)
                throw new CommandErrorException("Invalid Command Type. Use the 'help' command to learn more.");
        }

        return null;

    }

    private Command parseMoveCommand(int tokenListSize, ArrayList<Token> tokens) throws CommandErrorException{
        if(tokenListSize == 3){ //<MoveToken><DirectionVAR><EOLToken>
            Token dirVarToken = tokens.get(1);
            String direction = dirVarToken.getValue();
            if(dirVarToken.getTokenType() == TokenType.VAR){
                if(direction.equals("north") || direction.equals("south") || direction.equals("east") || direction.equals("west")){
                    return new Move(direction);
                }else{
                    throw new CommandErrorException("Invalid Move Command (Direction). Use the 'help move' command to learn more");
                }
            }else{ //Middle token isn't a variable and therefore isn't valid
                throw new CommandErrorException("Invalid Move Command (Direction). Use the 'help move' command to learn more");
            }
        }else{ //Incorrect Token List Length
            throw new CommandErrorException("Invalid Move Command (Length). Use the 'help move' command to learn more");
        }
    }

    private Command parseHelpCommand(int tokenListSize, ArrayList<Token> tokens) throws CommandErrorException{
        if(tokenListSize == 2){ //<HelpToken> <EOLToken>
            return new Help(null);
        }else if(tokenListSize == 3) {//<HelpToken><Topic><EOLToken>
            try{
                CommandType.valueOf(tokens.get(1).getTokenType().name());
                return new Help(tokens.get(1).getValue());
            }catch(IllegalArgumentException e){
                throw new CommandErrorException("Invalid Help Topic");
            }
        }else{
            throw new CommandErrorException("Invalid Help Command. Use the 'help' command to learn more.");
        }
    }
 
}
