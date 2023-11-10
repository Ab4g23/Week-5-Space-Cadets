import javax.swing.*; // canvas
import java.awt.*; // colour and graphics classes
import java.awt.geom.*; // shapes etc.

public class Path extends JComponent{

    private double oldx;
    private double oldy;
    private double x;
    private double y;
    private double red;
    private double green;
    private double blue;

    public Path(double oldx1, double oldy1, double x1, double y1, double red1, double green1, double blue1) {
        oldx = oldx1;
        oldy = oldy1;
        x = x1;
        y = y1;
        red = red1;
        green = green1;
        blue = blue1;
    }

    public void paintComponent(Graphics g) {

        // draw graphics in program
        Graphics2D g2d = (Graphics2D) g; // like graphics but can smooth do more stuff

        g2d.setColor(new Color((int)red, (int)green, (int)blue));

        Line2D shape = new Line2D.Double();
        shape.setLine(oldx,oldy,x,y);
        g2d.draw(shape);

    }
}
