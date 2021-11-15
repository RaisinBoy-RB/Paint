
import java.awt.*;

abstract public class Figure implements java.io.Serializable {

    protected Color c;
    protected Point p;

    public Figure(int x, int y, Color c)
    {
        this.p = new Point(x,y);
        this.c = c;
    }

    public abstract void setBoundingBox(int w, int l);

    public abstract void draw(Graphics g);

    public Color getColor(){
        return c;
    }

    public Point getPoint(){
        return p;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "color =" + c +
                ", point =" + p +
                '}';
    }

}

