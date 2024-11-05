package org.uob.a1;

class CodePuzzle extends Puzzle{
    
    private String codeStr;
    private int[] code;
    private String[] startHints;
    private String[] hints;

    public CodePuzzle(String prizeItem, Player puzzleSolver, String code){
        super(
            "Code Puzzle", //puzzle name
            "", //puzzle description
            "", //prize item
            puzzleSolver //Player Object
        );

        this.code = new int[code.length()];
        for(int i=0; i<code.length(); i++){
            this.code[i] = (int) code.charAt(i);
        }
        this.codeStr = code;
        this.generateHints();
    }

    private void generateHints(){
        this.startHints = new String[code.length];
        this.hints = new String[code.length];

        for (int i=0; i<code.length; i++){
            if(i%2==0){
                startHints[i] = "The digit at position " + (i=1) + " is even";
            }else{
                startHints[i] = "The digit at position " + (i=1) + " is odd";
            }
        }

        hints[0] = "The first number is " + code[0];
        hints[1] = "The number " + code[4] + "is in an odd position";
    }

    public String[] getStartHints(){
        return this.startHints;
    }

    public void outputStartHints(){
        System.out.println("Starting Hints:");
        for(int i=0; i<startHints.length;i++){
            System.out.println(i + ") " + startHints[i]);
        }
    }

    public void startPuzzle(){
        System.out.println("Welcome to the Combination Code Puzzle!");
        System.out.println("The rules are simple, I will give you 6 starting hints which tell you whether each digit is odd or even. After that you may type 'hint' to gain a new hint or type a 6 digit number to guess my combination and win a prize.");
        System.out.println("Good Luck!");
    }
}