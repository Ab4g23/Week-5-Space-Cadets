import javax.swing.*; // canvas
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class MainCanvas {

    private static double width = 900;
    private static double height = 600;

    public static void paintHyp(double R, double r, double offset,  double red, double green, double bue, JFrame f) throws InterruptedException {
        double x, oldx, y, oldy;
        double firstx = (R-r)*Math.cos(0) + offset*Math.cos(0) + width/2;
        double firsty = (R-r)*Math.sin(0) - offset*Math.sin(0) + height/2;
        oldx = firstx;
        oldy = firsty;
        x = 0;
        y = 0;

        System.out.println("fx = " + Math.round(firstx) + ", fy = " + Math.round(firsty));


        double t = 5;
        while(!((Math.round(firstx) == Math.round(x)) && (Math.round(firsty) == Math.round(y)))) { // ROUND
            double tr = Math.toRadians(t);
            t = t + 5;

            x = (R-r)*Math.cos(tr) + offset*Math.cos(((R - r) /r)*tr) + width/2;
            y = (R-r)*Math.sin(tr) - offset*Math.sin(((R - r) /r)*tr) + height/2;
            Path hyp = new Path(oldx, oldy, x, y, red, green, bue);
            f.add(hyp);

            oldx = x;
            oldy = y;

            f.setVisible(true);

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static ArrayDeque<java.lang.Double> tokeniser(String line) {
        // turns a line of the text document into tokens

        ArrayDeque<Double> tokenLine = new ArrayDeque<>();
        String token = "";

        for (int i=0; i< line.length(); i++) {
            if ((line.charAt(i) != ' ') && (line.charAt(i) != ';')) { // add a character to token when no whitespace or
                token = token.concat(String.valueOf(line.charAt(i)));
            }

            if (!token.isEmpty() && ((line.charAt(i) == ' ') || (line.charAt(i) == ';'))) { // if finished token, add to the toke list
                tokenLine.addLast(Double.parseDouble(token));
                token = "";
            }
        }

        return tokenLine;
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame f = new JFrame();

        f.setSize((int)width,(int)height);
        f.setTitle("Drawing in Java");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\abby\\IntelliJ\\Spirograph\\src\\SpirographData.txt"));
            String line = reader.readLine(); // read into a line string
            System.out.println("line = " + line);

            while (line != null) {
                ArrayDeque<Double> values = tokeniser(line); // splits a line into tokens
                // R r offset r g b

                double R = values.getFirst();
                values.removeFirst();
                double r = values.getFirst();
                values.removeFirst();
                double offset = values.getFirst();
                values.removeFirst();
                double red = values.getFirst();
                values.removeFirst();
                double green = values.getFirst();
                values.removeFirst();
                double blue = values.getFirst();
                values.removeFirst();

                paintHyp(R, r, offset, red, green, blue, f);

                //System.out.println("GET OUT");

                line = reader.readLine(); // reads a new line
                System.out.println("line = " + line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace(); // if all fails
        }

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
