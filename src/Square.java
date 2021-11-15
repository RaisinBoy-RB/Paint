import java.awt.*;

public class Square extends Rectangle {

    private int length;
    private int w;
    private int l;

    public Square(int px, int py, Color c) {
        super(px, py, c);
    }

    @Override
    public void setBoundingBox(int w, int l) {
        this.length = Math.max(Math.abs(w),Math.abs(l));
        this.w = w;
        this.l = l;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(c);


        if (w > 0 && l > 0) {
            g.fillRect(p.getX(),p.getY(), length, length);
        }
        else if (w > 0 && l < 0) {
            g.fillRect(p.getX(),p.getY() - length, length, length);
        }
        else if (w < 0 && l > 0) {
            g.fillRect(p.getX() -length,p.getY(), length, length);
        }
        else {
            g.fillRect(p.getX() - length, p.getY() - length, length, length);
        }
    }
}