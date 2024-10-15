public class Expression{
    private String content;

    public Expression(String content){
        this.content = content;
    }

    public String toString(){
        return this.content;
    }

    public void ruleOne(){
        if(this.content.endsWith("I")){
            this.content+="U";
        }
    }

    public void ruleTwo(){
        String c = this.content.substring(0,1);
        String z = this.content.substring(1);
        this.content = c + z + z;
    }

    public void ruleThree(){
        this.content = this.content.replaceAll("III", "U");
    }
}