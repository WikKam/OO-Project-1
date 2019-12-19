package pl.agh.input;

import pl.agh.movementUtils.Vector2d;

public class InputInterpreter {
    private final int width;
    private final int height;
    public final int startEnergy;
    public final int moveEnergy;
    public final int energyGainedFromGrass;
    private final double jungleRatio;
    public final Vector2d steppeStart = new Vector2d(0,0);
    public final Vector2d steppeEnd;
    public final Vector2d jungleStart;
    public final Vector2d jungleEnd;
    public  InputInterpreter(String[] input){
        this.width = Integer.parseInt(input[0]);
        this.height = Integer.parseInt(input[1]);
        this.startEnergy = Integer.parseInt(input[2]);
        this.moveEnergy = Integer.parseInt(input[3]);
        this.energyGainedFromGrass = Integer.parseInt(input[4]);
        this.steppeEnd = new Vector2d(width,height);
        this.jungleRatio = Double.parseDouble(input[5]);
        this.jungleStart = calculateJungleStart();
        this.jungleEnd = calculateJungleEnd();
    }

    private Vector2d calculateJungleStart() {
        Vector2d temp = getWidthAndHeight();
        return new Vector2d(steppeEnd.x/2 - temp.x/2,steppeEnd.y/2 - temp.y/2);
    }

    private Vector2d calculateJungleEnd() {
        Vector2d temp = getWidthAndHeight();
        return new Vector2d(steppeEnd.x/2 + temp.x/2,steppeEnd.y/2 + temp.y/2);
    }
    private Vector2d getWidthAndHeight(){
        int val = (int) Math.sqrt(width*height/(1+1/jungleRatio));
        int a =  val;
        int b = a;
        while(a*b != val*val){
            a++;
            b--;
        }
        return new Vector2d(a,b);
    }
}
