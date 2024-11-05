import java.util.Random;

public class Chromosome{
    private int size;
    private boolean[] genes;
    private Random generator;

    public Chromosome(int size, Random generator){
        this.size = size;
        this.generator = generator;
        this.genes = new boolean[size];

        for(int i = 0; i < this.size; i++){
            this.genes[i] = (generator.nextInt(2) == 1);
        }
    }

    public String toString(){

        String result = "";

        for(int i = 0; i<this.size; i++){
            if(this.genes[i]){
                result += "1";
            }else{
                result += "0";
            }
        }

        return result;
    }

    public void mutate(){
        int randomGene = this.generator.nextInt(this.size+1);
        this.genes[randomGene] = !this.genes[randomGene];
    }

    public Chromosome copy(){
        Chromosome chrom = new Chromosome(size, generator);
        for(int i=0; i<size;i++){
            chrom.genes[i] = this.genes[i];
        }

        return chrom;
    }

    public int fitness(){
        int count=0;

        for(int i=0; i<this.size;i++){
            if(this.genes[i]){
                count += 1;
            }else{
                break;
            }
        }

        return count;
    }
}