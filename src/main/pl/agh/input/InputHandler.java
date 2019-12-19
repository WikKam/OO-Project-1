package pl.agh.input;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputHandler {
    private String[] output;
    public InputHandler(){
        JSONParser parser = new JSONParser();
        try{
            FileReader reader = new FileReader("parameters.json");
            Object res = parser.parse(reader);
            JSONObject params = (JSONObject) res;
            getParametersFromJson(params);
        } catch (ParseException e) {
            System.out.println("coś poszlo nie tak z parsowaniem");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("brak pliku parameters.json!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("coś innego");
            e.printStackTrace();
        }
    }

    private void getParametersFromJson(JSONObject params) {
        this.output = new String[]{String.valueOf(params.get("width")), String.valueOf(params.get("height")),String.valueOf(params.get("startEnergy"))
                ,String.valueOf(params.get("moveEnergy")), String.valueOf(params.get("energyGainedFromGrass")), String.valueOf(params.get("jungleRatio"))};
    }
    public String[] getParameters(){
        return this.output;
    }
}
