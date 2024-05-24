import java.awt.Color;

import javax.swing.*;

import java.awt.event.*;



public class Paint extends JFrame{
    
    DrawingPanel dp;
    JPanel bp;
    JLabel eraser, pencil;

    public Paint(){
        initFrame();
        initDrawingPanel();
        initPanelButtons();
        this.setVisible(true);
    }

    private void initFrame(){
        this.setSize(700, 450);
        this.setTitle("Paint");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(61, 58, 46));
    }

    private void initDrawingPanel(){
        dp = new DrawingPanel(500, 375);
        dp.setLocation(20, 20);
        this.add(dp);
    }

    private void initPanelButtons(){
        bp = new JPanel();
        bp.setBounds(500, 20, 180, 375);
        bp.setOpaque(false);
        bp.setLayout(null);
        pencilButton();
        eraserButton();
        colorComboBox();
        strokeComboBox();
        clearButton();
        this.add(bp);
    }

    private void pencilButton(){
        pencil = new JLabel(" ");
        pencil.setBounds(90, 40, 30, 30);
        Icon icon = new ImageIcon(new ImageIcon(getClass().getResource("Lapiz.png")).getImage().getScaledInstance(pencil.getWidth(), pencil.getHeight(), 0));
        pencil.setIcon(icon);
        pencil.setOpaque(true);
        pencil.setBackground(Color.WHITE);

        pencil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pencil.setOpaque(true);
                pencil.setBackground(Color.WHITE);
                eraser.setOpaque(false);
                eraser.setBackground(getForeground());
                dp.setEraseMode(false);
            }
        });

        bp.add(pencil);
    }

    private void eraserButton(){
        eraser = new JLabel(" ");
        eraser.setBounds(90, 90, 30, 30);
        Icon icon = new ImageIcon(new ImageIcon(getClass().getResource("Goma.png")).getImage().getScaledInstance(eraser.getWidth(), eraser.getHeight(), 0));
        eraser.setIcon(icon);

        eraser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eraser.setOpaque(true);
                eraser.setBackground(Color.WHITE);
                pencil.setOpaque(false);
                pencil.setBackground(getForeground());
                dp.setEraseMode(true);
            }
        });

        bp.add(eraser);
    }

    private void colorComboBox(){
        String[] options = {"blanco", "rojo", "azul", "verde", "amarillo", "naranja", "gris", "morado", "rosa", "cyan", "marrón", "beige", "turquesa", "plateado"};
        JComboBox<String> colorComboBox = new JComboBox<>(options);
        colorComboBox.setBounds(70, 170, 80, 20);

        colorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dp.changeColor((String)colorComboBox.getSelectedItem());
            }
        });
        bp.add(colorComboBox);
    }

    private void strokeComboBox(){
        Integer[] options = {1,2,3,4,5,6,7,8};
        JComboBox<Integer> strokeComboBox = new JComboBox<>(options);
        strokeComboBox.setBounds(70, 200, 80, 20);

        strokeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dp.changeStroke(Integer.parseInt(strokeComboBox.getSelectedItem().toString()));
            }
        });
        bp.add(strokeComboBox);
    }

        
    private void clearButton(){
        JButton clear = new JButton("Limpiar");
        clear.setBounds(70, 300, 80, 20);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dp.clear();
            }
        });

        bp.add(clear);
    }

    public static void main(String[] args) {
        new Paint();
    }
}
