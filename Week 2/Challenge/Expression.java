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

    
}