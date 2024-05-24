import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cronometro extends JFrame {
    private Timer timer;
    private int horas, minutos, segundos, nanosegundos;
    private JLabel labelTiempo;

    public Cronometro() {
        setTitle("Cronómetro");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        labelTiempo = new JLabel("00:00:00:00", SwingConstants.CENTER);
        labelTiempo.setFont(new Font("Arial", Font.BOLD, 24));
        add(labelTiempo, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnIniciar = new JButton("Iniciar");
        JButton btnDetener = new JButton("Detener");
        JButton btnReiniciar = new JButton("Reiniciar");

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarCronometro();
            }
        });

        btnDetener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                detenerCronometro();
            }
        });

        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarCronometro();
            }
        });

        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);
        panelBotones.add(btnReiniciar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void iniciarCronometro() {
        timer = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nanosegundos++;
                    if(nanosegundos == 100){
                        nanosegundos = 0;
                        segundos++;
                        if (segundos == 60) {
                            segundos = 0;
                            minutos++;
                            if (minutos == 60) {
                                minutos = 0;
                                horas++;
                            }
                        }
                    }
                    actualizarTiempoLabel();
            }
        });
        timer.start();
    }

    private void detenerCronometro() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void reiniciarCronometro() {
        detenerCronometro();
        horas = 0;
        minutos = 0;
        segundos = 0;
        nanosegundos = 0;
        actualizarTiempoLabel();
    }

    private void actualizarTiempoLabel() {
        String tiempo = String.format("%02d:%02d:%02d:%02d", horas, minutos, segundos, nanosegundos);
        labelTiempo.setText(tiempo);
    }	

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cronometro().setVisible(true);
            }
        });
    }
}
