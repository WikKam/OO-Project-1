package pl.agh.animal;

import java.util.*;

    public class Genotype implements Cloneable {

    private final int GENESSIZE = 32;
    private final int GENESAMOUNT = 8;
    private final int[] baseGenes = {0,1,2,3,4,5,6,7};
    private ArrayList<Integer> genes = new ArrayList<>();
    public Genotype(){
        Random randNum = new Random();
        for ( int i=0;i<GENESSIZE;i++){
            int gene = randNum.nextInt(GENESAMOUNT);
            genes.add(gene);
        }
        Collections.sort(genes);
        this.genes = repairGenes(this.genes);
    }
    private Genotype(ArrayList<Integer> genes){
        this.genes = genes;
    }
    public ArrayList<Integer> getGenes(){
        return (ArrayList<Integer>) this.genes.clone();
    }
    public Genotype(Animal parent1, Animal parent2) {
        this.genes = createNewGenes(parent1.getGenotype().getGenes(),parent2.getGenotype().getGenes());
    }
    private ArrayList<Integer> createNewGenes(ArrayList<Integer>genes1, ArrayList<Integer> genes2){
        HashSet<Integer> genesSet = new HashSet<>();
        Random random = new Random();
       int rand = random.nextInt();
        int index1;
        int index2;
        do{
            index1 = random.nextInt(GENESSIZE);
            index2 = random.nextInt(GENESSIZE);}while(index1==index2 ||index1==0 || index2==0);
        int lower = Math.min(index1,index2);
        int higher = lower == index1 ? index2 : index1;
        ArrayList<Integer> ret = new ArrayList<>();
        ret.addAll(genes1.subList(0,lower - 1));
        ret.addAll(genes2.subList(lower,higher-1));
        ret.addAll(genes1.subList(higher,GENESSIZE-1));
        genesSet.addAll(ret);
        if(!genesSet.containsAll(Arrays.asList(baseGenes))){
            return repairGenes(ret);
        }
        return ret;
    }
    public ArrayList<Integer> repairGenes(ArrayList<Integer> ret){
        int[] genesCount = new int[GENESAMOUNT];
        for(int i =0;i<GENESAMOUNT;i++){
            genesCount[i]=0;
        }
        int missingAmount = 0;
        for(int gene : ret){
            genesCount[gene]++;
        }
        for(int i=0;i<GENESAMOUNT;i++){
            if(genesCount[i]==0){ missingAmount++;
            ret.add(i);}
        }
        System.out.println(missingAmount);
        while(missingAmount>0){
            for(int i = 0; i<GENESAMOUNT;i++){
                if(genesCount[i]>1){
                    ret.remove(i);
                    genesCount[i]--;
                    missingAmount--;
                }
                if(missingAmount==0)break;
            }
        }
        Collections.sort(ret);
        return ret;
    }
    @Override
    public Object clone(){
        return new Genotype((ArrayList<Integer>) genes.clone());
    }

}
