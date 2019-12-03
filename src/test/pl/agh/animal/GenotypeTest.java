package pl.agh.animal;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pl.agh.animalMap.WorldMap;
import pl.agh.movementUtils.Direction;
import pl.agh.movementUtils.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class GenotypeTest {
    ArrayList<Integer> genes = new ArrayList<>();
    Genotype test1 = new Genotype();
    Genotype test2 = new Genotype();
    Genotype test3 = new Genotype();
    private final Integer[] baseGenes = {0,1,2,3,4,5,6,7};
    @Test
    public void repairGenesTest(){
        System.out.println(test1.getGenes());
        System.out.println(test2.getGenes());
        System.out.println(test3.getGenes());
        HashSet<Integer>testSet = new HashSet<>();
        testSet.addAll(test1.getGenes());
        Assert.assertTrue(testSet.containsAll(Arrays.asList(baseGenes)));
        testSet.clear();
        testSet.addAll(test2.getGenes());
        Assert.assertTrue(testSet.containsAll(Arrays.asList(baseGenes)));
        testSet.clear();
        testSet.addAll(test3.getGenes());
        Assert.assertTrue(testSet.containsAll(Arrays.asList(baseGenes)));
    }

}
