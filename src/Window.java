import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Window extends JFrame implements ActionListener {

    ArrayList <String> ButtonOfColorName = new ArrayList<String>();
    ArrayList <Color> ButtonOfColor = new ArrayList<Color>();
    ArrayList <String> ButtonOfFigure = new ArrayList<String>();
    ArrayList <String> ButtonForOther = new ArrayList<String>();
    Drawing draw = new Drawing(Color.BLACK,"paintPackage.Rectangle",1, Color.WHITE);


    public Window(String Title, int x,int y){

        super(Title);
        this.setSize(x,y);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JMenuBar m = new JMenuBar();

        JMenu menu1 = new JMenu("File");
        JMenuItem newdraw = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem quit = new JMenuItem("Quit");
        menu1.add(newdraw);
        menu1.add(open);
        menu1.add(save);
        menu1.add(quit);
        newdraw.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        quit.addActionListener(this);
        m.add(menu1);

        JMenu menu2 = new JMenu("About");
        JMenuItem creator = new JMenuItem("Creator");
        JMenuItem version = new JMenuItem("Version");
        menu2.add(creator);
        menu2.add(version);
        creator.addActionListener(this);
        version.addActionListener(this);
        m.add(menu2);


        setJMenuBar(m);


        Container contentPanel = this.getContentPane();
        contentPanel.add(draw);


        JPanel South = new JPanel();
        South.setLayout(new GridLayout(1,4));

        //Buttons for the color
        JPanel SouthWest = new JPanel();
        SouthWest.setLayout(new GridLayout(3,4));

        ColorButton(SouthWest,"Black",Color.BLACK,Color.WHITE);
        ColorButton(SouthWest,"Gray",Color.GRAY,Color.BLACK);
        ColorButton(SouthWest,"Light Gray",Color.LIGHT_GRAY,Color.BLACK);
        ColorButton(SouthWest,"White",Color.WHITE,Color.BLACK);

        ColorButton(SouthWest,"Yellow",Color.YELLOW,Color.BLACK);
        ColorButton(SouthWest,"Orange",Color.ORANGE,Color.BLACK);
        ColorButton(SouthWest,"Pink",Color.PINK,Color.BLACK);
        ColorButton(SouthWest,"Magenta",Color.MAGENTA,Color.BLACK);

        ColorButton(SouthWest,"Green",Color.GREEN,Color.BLACK);
        ColorButton(SouthWest,"Cyan",Color.CYAN,Color.BLACK);
        ColorButton(SouthWest,"Blue",Color.BLUE,Color.WHITE);
        ColorButton(SouthWest,"Red",Color.RED,Color.BLACK);

        South.add(SouthWest);

        //Buttons for the figure
        JPanel SouthEast = new JPanel();
        SouthEast.setLayout(new GridLayout(3,2));
        DrawButton(SouthEast,"paintPackage.Ellipse");
        DrawButton(SouthEast,"paintPackage.Circle");
        DrawButton(SouthEast,"paintPackage.Rectangle");
        DrawButton(SouthEast,"paintPackage.Square");
        DrawButton(SouthEast,"line");
        DrawButton(SouthEast,"eraser");

        South.add(SouthEast);


        //Button for the Size line
        JPanel SizeLine = new JPanel();
        SizeLine.setLayout(new GridLayout(3,1));

        LineSize(SizeLine,"Size : 1");
        LineSize(SizeLine,"Size : 2");
        LineSize(SizeLine,"Size : 3");
        South.add(SizeLine);



        JPanel ColorChange = new JPanel();
        ColorChange.setLayout(new GridLayout(2,1));

        ColorChangeButton(ColorChange,"Lighter");
        ColorChangeButton(ColorChange,"Darker");
        South.add(ColorChange);



        contentPanel.add(South,BorderLayout.SOUTH);

        this.setVisible(true);
    }
    //end of constructor



    public void ColorButton(Container SouthWest,String Name,Color color, Color foreGround){

        JButton button  = new JButton(Name);
        button.setBackground(color);
        button.setForeground(foreGround);
        button.addActionListener(this);
        SouthWest.add(button);
        ButtonOfColorName.add(Name);
        ButtonOfColor.add(color);

    }

    public void DrawButton(Container SouthEast,String Name){
        JButton button = new JButton(Name);
        button.addActionListener(this);
        SouthEast.add(button);
        ButtonOfFigure.add(Name);
    }

    public void LineSize(Container Cont,String Name){
        JButton button = new JButton(Name);
        button.addActionListener(this);
        Cont.add(button);
        ButtonForOther.add(Name);
    }

    public void ColorChangeButton(Container Cont,String Name){
        JButton button = new JButton(Name);
        button.addActionListener(this);
        Cont.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        int i = 0;

       for (String color: this.ButtonOfColorName){
            if (s=="null"){break;}
            if (s==color){
                System.out.println("Color");
                draw.changeColor(ButtonOfColor.get(i));
                s="null";
                break;
            }
            i++;
        }

        i=0;
        for (String figure: this.ButtonOfFigure){
            if (s=="null"){break;}
            if (s==figure){
                System.out.println("Figure");
                draw.changeFigure(ButtonOfFigure.get(i));
                s="null";
                break;
            }
            i++;
        }

        i=0;
        for (String other: this.ButtonForOther){
            if (s=="null"){break;}
            if (s==other){
                System.out.println("Size of line");
                draw.changeSize(i);
                s="null";
                break;
            }
            i++;
        }

        i=0;
        switch (s){
            case "null":
                break;
            case "Creator":
                JOptionPane info_creator = new JOptionPane();
                info_creator.showInternalMessageDialog(info_creator,"Authors : G. Musard","Creator",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Version":
                JOptionPane info_version = new JOptionPane();
                info_version.showInternalMessageDialog(info_version,"Latest version : 3.2.4","Version",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Open":
                System.out.println("Open");
                draw.open();
                break;
            case "Save":
                System.out.println("Save");
                draw.save();
                break;
            case "New":
                System.out.println("New");
                draw.newDraw();
                break;
            case "Quit":
                System.out.println("Quit");
                this.dispose();
                break;
            case "Lighter":
                System.out.println("Lighter");
                draw.changeColor(draw.getColor().brighter());
                break;
            case "Darker":
                System.out.println("Darker");
                draw.changeColor(draw.getColor().darker());
                break;
        }


    }


}
