
import java.awt.*;

public class Ellipse extends Figure {

    private int length;
    private int width;

    public Ellipse(int px, int py, Color c) {
        super(px,py,c);
    }



    @Override
    public void setBoundingBox(int w, int l) {
        this.length = l;
        this.width = w;

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        if (width > 0 && length > 0) {
            g.fillOval(p.getX(),p.getY(), width, length);
        }
        else if (width > 0 && length < 0) {
            g.fillOval(p.getX(),p.getY() + length, width, -length);
        }
        else if (width < 0 && length > 0) {
            g.fillOval(p.getX()+width,p.getY(), -width, length);
        }
        else {
            g.fillOval(p.getX() + width, p.getY() + length, -width, -length);
        }
    }


}
