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
        //OLD FUNCTION: Had a high error rate for passing different command types

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
            case USE: //Done
                return parseUseCommand(tokenListSize, tokens);
            case GET: //Done
                return parseGetCommand(tokenListSize, tokens);
            case DROP: //Done
                return parseDropCommand(tokenListSize, tokens);
            case LOOK: //Done
                return parseLookCommand(tokenListSize, tokens);
            case STATUS: //Done
                return parseStatusCommand(tokenListSize, tokens);
            case HELP: //Done
                return parseHelpCommand(tokenListSize, tokens);
            case QUIT: //Done
                //For quit command, we can just return a new Quit() Object - No need to paarse
                return new Quit();
            default:
                //Only to be reached if commandType is unknown (should be impossible)
                throw new CommandErrorException("Invalid Command Type. Use the 'help' command to learn more.");
        }
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
                //All help topics actually belong to the CommandType Enum so we can use that to check validity
                CommandType.valueOf(tokens.get(1).getTokenType().name());
                return new Help(tokens.get(1).getValue());
            }catch(IllegalArgumentException e){
                throw new CommandErrorException("Invalid Help Topic");
            }
        }else{
            throw new CommandErrorException("Invalid Help Command. Use the 'help' command to learn more.");
        }
    }

    private Command parseUseCommand(int tokenListSize, ArrayList<Token> tokens) throws CommandErrorException{
        Token equipmentToken = tokens.get(1); //Should be second token in Token ArrayList
        String target;

        //Check if the equipment token is a valid variable
        if(equipmentToken.getTokenType() != TokenType.VAR) throw new CommandErrorException("Invalid Use command (Equipment). Use the 'help use' command to learn more");

        if(tokenListSize == 3){ //<UseToken><EquipmentVAR><EOLToken>
            target = "NONE";
        }else if(tokenListSize == 5){ //<UseToken><Equipment1VAR><PrepositionToken><Equipment2Var><EOL>
            target = tokens.get(3).getValue();
        }else{
            throw new CommandErrorException("Invalid Use command. Use the 'help use' command to learn more.");
        }

        return new Use(equipmentToken.getValue(), target);
    }

    private Command parseGetCommand(int tokenListSize, ArrayList<Token> tokens) throws CommandErrorException{
        if(tokenListSize == 3){//<GetToken><ItemTokenVAR><EOLToken>
            if(tokens.get(1).getTokenType() == TokenType.VAR){
                return new Get(tokens.get(1).getValue());
            }else{
                throw new CommandErrorException("Invalid Get Command (Item). Use the 'help get' command to learn more");
            }
        }else{
            throw new CommandErrorException("Invalid Get Command. Use the 'help get' command to learn more");
        }
    }

    private Command parseDropCommand(int tokenListSize, ArrayList<Token> tokens) throws CommandErrorException{
        if(tokenListSize == 3){ //<DropToken><ItemVAR><EOLToken>
            Token varToken = tokens.get(1);
            if(varToken.getTokenType() == TokenType.VAR){
                return new Drop(varToken.getValue());
            }else{
                throw new CommandErrorException("Invalid Drop Command (Item). Use the 'help drop' command to learn more");
            }
        }else{
            throw new CommandErrorException("Invalid Drop Command. Use the 'help drop' command to learn more");
        }
    }

    private Command parseLookCommand(int tokenListSize, ArrayList<Token> tokens) throws CommandErrorException{
        if(tokenListSize == 3){ //<LookToken><WhereVAR><EOLToken>
            Token varToken = tokens.get(1);
            if(varToken.getTokenType() == TokenType.VAR){
                return new Look(varToken.getValue());
            }else throw new CommandErrorException("Invalid Look Command (VAR). Use the 'help look' command to learn more");
        }else throw new CommandErrorException("Invalid Look Command. Use the 'help look' command to learn more.");
    }

    private Command parseStatusCommand(int tokenListSize, ArrayList<Token> tokens) throws CommandErrorException {
        if(tokenListSize == 3){ //<StatusToken><VARToken><EOLToken>
            Token varToken = tokens.get(1);
            if(varToken.getTokenType() == TokenType.VAR){
                return new Status(varToken.getValue());
            }else throw new CommandErrorException("Invalid Status Command (VAR). Use the 'help status' command to learn more");
        }else throw new CommandErrorException("Invalid Status command. Use the 'help status' command to learn more");
    }
 
}
