
import java.awt.*;

public class Rectangle extends Figure {

    private int length;
    private int width;

    public Rectangle (int px, int py, Color c){
        super(px,py,c);
        this.length = 0;
        this.width = 0;
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
            g.fillRect(p.getX(),p.getY(), width, length);
        }
        else if (width > 0 && length < 0) {
            g.fillRect(p.getX(),p.getY() + length, width, -length);
        }
        else if (width < 0 && length > 0) {
            g.fillRect(p.getX()+width,p.getY(), -width, length);
        }
        else {
            g.fillRect(p.getX() + width, p.getY() + length, -width, -length);
        }

    }


}
