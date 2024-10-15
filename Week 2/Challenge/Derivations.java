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

        /* Problem 2, Derive MUIIU from MII */
        Expression derivation2 = new Expression("MII");
        derivation2.ruleTwo();
        derivation2.ruleThree();
        derivation2.ruleOne();
        derivation2.ruleTwo();
        derivation2.ruleFour();
        System.out.println(derivation2);

        /* Solution is to apply rules in the order 2,3,1,2,4 */

        /* Problem 3, Derive MUIIU from MI */
        Expression derivation3 = new Expression("MI");
        derivation3.ruleTwo();
        derivation3.ruleTwo();
        derivation3.ruleThree();
        derivation3.ruleOne();
        derivation3.ruleTwo();
        derivation3.ruleFour();
        System.out.println(derivation3);

        /* Solution is to apply rule 2 to get the same expression as Derivation 2, then appply the same rules from derivation 2 (2,2,3,1,2,4) */

        /* Problem 4, Derive MIU from MUIIU */
        Expression derivation4 = new Expression("MUIIU");
        derivation4.ruleTwo();
        derivation4.ruleFour();
        derivation4.ruleThree();
        derivation4.ruleFour();
        System.out.println(derivation4);

        /* Solution is to apply rules in the order 2,4,3,4 */
    }
}