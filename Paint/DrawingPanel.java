import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawingPanel extends JPanel {
    private int lastX, lastY;
    private Color currentColor;
    private int currentStroke;
    private boolean eraseMode;
    private HashMap<String, Color> colorsMap;


    public DrawingPanel(int width, int height) {
        initPanel(width, height);
        addPaintFunction();
        initColors();
    }

    private void initPanel(int width, int height){
        setSize(width, height);
        setBackground(Color.BLACK);
        currentColor = Color.WHITE;
        currentStroke = 1;
        eraseMode = false;
    }

    private void addPaintFunction(){
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Graphics g = getGraphics();

                if (eraseMode) {
                    g.setColor(getBackground());
                } else {
                    g.setColor(currentColor);
                }

                ((Graphics2D) g).setStroke(new BasicStroke(currentStroke));
                int x = e.getX();
                int y = e.getY();
                g.drawLine(lastX, lastY, x, y);
                lastX = x;
                lastY = y;
            }
        });
    }

    private void initColors(){
        // Crear la HashMap
        colorsMap = new HashMap<>();

        // Agregar colores a la HashMap
        colorsMap.put("rojo", Color.RED);
        colorsMap.put("azul", Color.BLUE);
        colorsMap.put("verde", Color.GREEN);
        colorsMap.put("amarillo", Color.YELLOW);
        colorsMap.put("naranja", Color.ORANGE);
        colorsMap.put("blanco", Color.WHITE);
        colorsMap.put("gris", Color.GRAY);
        colorsMap.put("morado", new Color(128, 0, 128)); // Morado (RGB: 128, 0, 128)
        colorsMap.put("rosa", new Color(255, 192, 203)); // Rosa (RGB: 255, 192, 203)
        colorsMap.put("cyan", Color.CYAN);
        colorsMap.put("marrón", new Color(165, 42, 42)); // Marrón (RGB: 165, 42, 42)
        colorsMap.put("beige", new Color(245, 245, 220)); // Beige (RGB: 245, 245, 220)
        colorsMap.put("turquesa", new Color(64, 224, 208)); // Turquesa (RGB: 64, 224, 208)
        colorsMap.put("plateado", new Color(192, 192, 192)); 
    }

    public void changeColor(String color){
        currentColor = colorsMap.get(color);
    }

    public void changeStroke(int stroke){
        currentStroke = stroke;
    }

    public void clear(){
        Graphics g = getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setEraseMode(boolean eraseMode) {
        this.eraseMode = eraseMode;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(currentColor);
    }
}
