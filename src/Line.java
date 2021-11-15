import java.awt.*;

public class Line extends Figure{

    private int Size;
    private int x;
    private int y;

    public Line(int px, int py, Color c,int Size) {
        super(px, py, c);
        this.Size = Size + 1;
    }


    @Override
    public void setBoundingBox(int w, int l) {
        this.x = w;
        this.y = l;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        g.fillOval(x,y,Size*5,Size*5);
    }

}