package pl.agh.output;

import pl.agh.animalMap.MapStatistics;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class StatWriter {
    public static void writeStats(MapStatistics stats){
        String data = parse(stats);
        try{
        FileOutputStream file = new FileOutputStream("stats/stats.txt");
        file.write(data.getBytes());
        file.close();
        }catch (FileNotFoundException e){
            System.out.println("zla sciezka!");
        }catch (IOException e) {
            System.out.println("cos sie popsulo");
        }
    }
    private static String parse(MapStatistics stats) {
        StringBuilder out =  new StringBuilder();
        out.append("ilosc animal: " + stats.getAnimalNo() + "\n");
        out.append("srednia ilosc dzieci: " + stats.getAverageChildrenNo()+ "\n");
        out.append("srednia ilosc energii: " + stats.getAverageEnergy()+ "\n");
        out.append("sredni wiek: " + stats.getAveragelifespan()+ "\n");
        out.append("ilosc trawy: " + stats.getGrassNo()+ "\n");
        out.append("dominujący gen: " + stats.getDominatingGene()+ "\n");
        return new String(out);
    }
}
