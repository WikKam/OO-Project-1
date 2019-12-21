package pl.agh.movementUtils;



public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return "(" + x + "," + y + ")";

    }
    public boolean precedes(Vector2d other){
        return (this.x<=other.x&&this.y<=other.y);
    }
    public boolean follows(Vector2d other){
        return (this.x>=other.x&&this.y>=other.y);
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x,this.y+other.y);
    }
    public boolean equals(Object other){
        if(super.equals(other)) return true;
        if(!(other instanceof Vector2d))return false;
        Vector2d temp = (Vector2d)other;
        return(this.x==temp.x&&this.y==temp.y);
    }
    public int hashCode(){
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
