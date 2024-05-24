import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.*;
import static java.awt.Font.PLAIN;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculadora extends JFrame {

    public static void main(String[] args) {
        new Calculadora();
    }

    //Display para mostrar las expresiones
    JLabel display;
    //Array de strings para las etiquetas de los botones
    String textButtons[] = {"=","7","8","9","/","4","5","6","*","1","2","3","-","C","0",".","+"};
    //Array de botones para números y operaciones
    JButton botones[] = new JButton[textButtons.length];
    //Array de posiciones en X de cada botón
    int x[] = {15, 15, 80, 145, 210, 15, 80, 145, 210, 15, 80, 145, 210, 15, 80, 145, 210};
    //Array de posiciones en Y de cada botón
    int y[] = {90, 155, 155, 155, 155, 220, 220, 220, 220, 285, 285, 285, 285, 350, 350, 350, 350};
    
    public Calculadora(){
        //Caracteristicas de la ventana
        this.setResizable(false);
        this.setTitle("Calculadora");
        this.setBounds(0, 0, 290, 455);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Inicialización de los componentes
        this.initComponents();

        //Ajustando visibilidad de la ventana
        this.setVisible(true);
    }

    private void initComponents(){
        this.initDisplay();
        this.initButtons();
    }

    private void initDisplay(){
        display = new JLabel("0"); //Inicio JLabel
        display.setBounds(15, 15, 245, 60); //Posición y dimensiones
        display.setOpaque(true); //Para poder darle un color de fondo
        display.setBackground(Color.BLACK); //Color de fondo
        display.setForeground(Color.WHITE); //Color de fuente
        display.setBorder(new LineBorder(Color.GRAY)); //Borde
        display.setFont(new Font("MONOSPACED", PLAIN, 24)); //Fuente
        display.setHorizontalAlignment(SwingConstants.RIGHT); //Alineamiento horizontal derecha
        this.add(display); //Añado el JLabel al JFrame
    }

    private void initButtons(){
        for (int i = 0; i < textButtons.length; i++){

            JButton button = new JButton(textButtons[i]); //Inicializo JButton
            int fontSize = (i == 0) ? 24 : 16; //EL botón de Resultado tendrá un tamaño de fuente menor que todos los demás
            int width = (i == 0) ? 245 : 50; //EL botón de Resultado será más ancho que todos los demás
            int heigth = 50; //Todos los botones tienen la misma altura

            button.setBounds(x[i], y[i], width, heigth); //Posición y dimensiones
            button.setFont(new Font("MONOSPACED",PLAIN, fontSize)); //Fuente
            button.setOpaque(true); //Para poder darle un color de fondo
            button.setFocusPainted(false); //Para que no salga una recuadro azul cuando tenga el foco
            button.setBackground(Color.DARK_GRAY); //Color de fondo
            button.setForeground(Color.WHITE); //Color de fuente
            button.setBorder(new LineBorder(Color.DARK_GRAY)); //Borde
            //Eventos
            if(textButtons[i].contentEquals("=")){
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Acción a realizar cuando se hace clic en el botón
                        
                        // Crear un gestor de motores de script
                        ScriptEngineManager manager = new ScriptEngineManager();
                        // Obtener un motor de script
                        ScriptEngine engine = manager.getEngineByName("JavaScript");

                        try {
                            // Evaluar la expresión y mostrar el resultado
                            Object result = engine.eval(display.getText());
                            display.setText(result + "");
                        } catch (ScriptException error) {
                            display.setText("Syntax ERROR");
                        }
                    }
                });
            } else if(textButtons[i].contentEquals("C")){
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Acción a realizar cuando se hace clic en el botón
                        display.setText("0");
                    }
                });
            }else{
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Acción a realizar cuando se hace clic en el botón
                        addText(button.getText());
                    }
                });
            }

            this.add(button); //Añado el JButton al JFrame
        }
    }

    private void addText(String s){
        if(display.getText().contentEquals("0")){
            display.setText(s);
        } else if(display.getText().contentEquals("Syntax ERROR")){
            display.setText(s);
        }else{
            display.setText(display.getText() + s);
        }
    }

}
