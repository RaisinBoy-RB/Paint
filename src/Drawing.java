import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Drawing extends JPanel implements MouseMotionListener, MouseListener {

    private Color currentColor;
    private String currentFigureName;
    private Figure currentFigure;
    private int currentSize;
    private int initialx;
    private int initialy;
    private Color BackgroundColor;
    ArrayList<Figure> FigureList = new ArrayList<Figure>();
    ArrayList<Figure> DrawList = new ArrayList<Figure>();

    private boolean SaveSuccess;
    private int NumberOfFiguresSaved;

    public Drawing(Color currentColor, String currentFigure, int currentSize, Color BackColor) {
        this.BackgroundColor = BackColor;
        this.setBackground(BackgroundColor);
        this.currentColor = currentColor;
        this.currentFigureName = currentFigure;
        this.currentSize = currentSize;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    public void changeColor(Color c) {
        currentColor = c;
    }

    public void changeFigure(String f) {
        currentFigureName = f;
    }

    public void changeSize(int Size) {
        currentSize = Size;
    }

    public void resetDrawList() {
        DrawList.clear();
        this.repaint();
    }

    public ArrayList getFigureList() {
        return FigureList;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialx = e.getX();
        initialy = e.getY();
        switch (currentFigureName) {
            case "paintPackage.Rectangle":
                currentFigure = new Rectangle(initialx, initialy, currentColor);
                DrawList.add(currentFigure);
                break;
            case "paintPackage.Square":
                currentFigure = new Square(initialx, initialy, currentColor);
                DrawList.add(currentFigure);
                break;
            case "paintPackage.Ellipse":
                currentFigure = new Ellipse(initialx, initialy, currentColor);
                DrawList.add(currentFigure);
                break;
            case "paintPackage.Circle":
                currentFigure = new Circle(initialx, initialy, currentColor);
                DrawList.add(currentFigure);
                break;
            case "line":
                currentFigure = new Line(initialx, initialy, currentColor, currentSize);
                break;
            case "eraser":
                currentFigure = new Eraser_class(initialx, initialy, currentColor, currentSize);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     /*   Graphics g = getGraphics();

        for (int i = 0; i < DrawList.size(); i++) {
            DrawList.get(i).draw(g);
        }
*/
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1000,500);
        for (int i = 0; i < DrawList.size(); i++) {
            DrawList.get(i).draw(g);
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = getGraphics();

        //OK
        if (currentFigureName != "eraser" && currentFigureName != "line") {
            currentFigure.setBoundingBox(e.getX() - initialx, e.getY() - initialy);
            DrawList.add(DrawList.size() - 1, currentFigure);
            this.repaint();
        }

        //OK
        else if (currentFigureName == "line") {
            currentFigure = new Line(initialx, initialy, currentColor, currentSize);
            currentFigure.setBoundingBox(e.getX(), e.getY());
            currentFigure.draw(g);
            DrawList.add(currentFigure);
        }

        //OK
        else if (currentFigureName == "eraser") {
            currentFigure = new Eraser_class(initialx, initialy, BackgroundColor, currentSize);
            currentFigure.setBoundingBox(e.getX(), e.getY()); // ici dans setBoundingBox je n'ai pas mis la longueur / largeur de la figure ma sa position
            currentFigure.draw(g);
            DrawList.add(currentFigure);
        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    public void undo() {
        Graphics g = getGraphics();
        DrawList.remove(DrawList.size() - 1);
        this.repaint();
        for (int i = 0; i < DrawList.size(); i++) {
            DrawList.get(i).draw(g);
        }
    }


    public void save() { //Will save all objets of the draw in a file
        JFileChooser fileChooser = new JFileChooser(); //To chose which directory to save in
        int val = fileChooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile().getPath());
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeInt(DrawList.size());
                for (Figure f : DrawList) {
                    oos.writeObject(f);
                }
                oos.close();
                fos.close();
            } catch (Exception e) {
                System.out.println("Drawing could not be saved");
            }
            SaveSuccess = true;
        }
        else if (val == JFileChooser.CANCEL_OPTION) {
            SaveSuccess = false;
            NumberOfFiguresSaved = DrawList.size();
        }
    }

    public void open() {
        JFileChooser fileChooser = new JFileChooser(); //To chose which file to open
        int val = fileChooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile().getName());
                ObjectInputStream ois = new ObjectInputStream(fis);

                int size = ois.readInt();
                DrawList.clear();
                for (int i = 0; i < size; i++) {
                    DrawList.add((Figure) ois.readObject());
                }
                ois.close();
                fis.close();
                this.repaint();

            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("File could not be opened");
            }
        }

    }

    public boolean recentSave(){
        if (NumberOfFiguresSaved == DrawList.size()){
            return true;
        }
        else {
            return false;
        }
    }

    public void newDraw(){
        if (!recentSave()){
            int result =  JOptionPane.showConfirmDialog(this,"Do you want to save the modifications ?","Save ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result ==JOptionPane.YES_NO_OPTION){
                save();
                resetDrawList();
            }
            else {
                resetDrawList();
            }

        }
        else{
            resetDrawList();
        }
    }
}