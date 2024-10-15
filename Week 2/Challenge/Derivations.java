public class Derivations{
    public static void main(String[] args){

        /* Problem 1, Derive MIIU from MIIUII */
        Expression derivation1 = new Expression("MIIUII"); // Need to derive MIIU
        derivation1.ruleTwo();
        derivation1.ruleThree();
        derivation1.ruleFour();
        derivation1.ruleThree();
        derivation1.ruleFour();
        derivation1.ruleOne();
        System.out.println(derivation1);

        /* Solution is to apply rules in the order 2,3,4,3,4,1 */
    }
}