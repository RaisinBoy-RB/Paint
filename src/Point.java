
public class Point implements java.io.Serializable {
    private int x = 0;
    private int y = 0;

    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    public Point(){}

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public String toString() {
        return "(" + x +","+ y +")";
    }


}
