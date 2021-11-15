import java.awt.*;

public class Circle extends Ellipse {

    private int length;
    private int w;
    private int l;

    public Circle(int px, int py, Color c) {
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
            g.fillOval(p.getX(),p.getY(), length, length);
        }
        else if (w > 0 && l < 0) {
            g.fillOval(p.getX(),p.getY() - length, length, length);
        }
        else if (w < 0 && l > 0) {
            g.fillOval(p.getX() -length,p.getY(), length, length);
        }
        else {
            g.fillOval(p.getX() - length, p.getY() - length, length, length);
        }
    }

}
