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

    }
}