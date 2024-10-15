public class Test{
    public static void main(String[] args){
        /* Tests to test Rule 1*/

        Expression test1 = new Expression("MI");
        System.out.println(test1); //Should output MI
        test1.ruleOne();
        System.out.println(test1); //Should output MIU

        Expression test2 = new Expression("MUU");
        System.out.println(test2); //Should output MUU
        test2.ruleOne();
        System.out.println(test2); //Should output MUU

        /*Tests to test Rule 2*/
        Expression test3 = new Expression("MIU");
        System.out.println(test3); //Should output MIU
        test3.ruleTwo();
        System.out.println(test3); //Should output MIUIU

        /*Tests to test Rule 3*/
        Expression test4 = new Expression("MUIIIU");
        System.out.println(test4); //Should output MUIIIU
        test4.ruleThree();
        System.out.println(test4); //Should output MUUU
    }
}