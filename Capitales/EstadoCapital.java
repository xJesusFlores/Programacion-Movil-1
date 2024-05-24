import javax.swing.*;
import java.awt.event.*;

public class EstadoCapital {
    // Array con los estados de la república y sus capitales
    private static final String[][] estadosCapitales = {
            {"Aguascalientes", "Aguascalientes"},
            {"Baja California", "Mexicali"},
            {"Baja California Sur", "La Paz"},
            {"Campeche", "San Francisco de Campeche"},
            {"Coahuila", "Saltillo"},
            {"Colima", "Colima"},
            {"Chiapas", "Tuxtla Gutiérrez"},
            {"Chihuahua", "Chihuahua"},
            {"Ciudad de México", "Ciudad de México"},
            {"Durango", "Durango"},
            {"Guanajuato", "Guanajuato"},
            {"Guerrero", "Chilpancingo"},
            {"Hidalgo", "Pachuca"},
            {"Jalisco", "Guadalajara"},
            {"Estado de México", "Toluca"},
            {"Michoacán", "Morelia"},
            {"Morelos", "Cuernavaca"},
            {"Nayarit", "Tepic"},
            {"Nuevo León", "Monterrey"},
            {"Oaxaca", "Oaxaca"},
            {"Puebla", "Puebla"},
            {"Querétaro", "Querétaro"},
            {"Quintana Roo", "Chetumal"},
            {"San Luis Potosí", "San Luis Potosí"},
            {"Sinaloa", "Culiacán"},
            {"Sonora", "Hermosillo"},
            {"Tabasco", "Villahermosa"},
            {"Tamaulipas", "Ciudad Victoria"},
            {"Tlaxcala", "Tlaxcala"},
            {"Veracruz", "Xalapa"},
            {"Yucatán", "Mérida"},
            {"Zacatecas", "Zacatecas"}
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Estado y Capital");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel estadoLabel = new JLabel("Selecciona un estado:");
        estadoLabel.setBounds(10, 20, 150, 25);
        panel.add(estadoLabel);

        JComboBox<String> estadoComboBox = new JComboBox<>();
        for (String[] estado : estadosCapitales) {
            estadoComboBox.addItem(estado[0]);
        }
        estadoComboBox.setBounds(150, 20, 120, 25);
        panel.add(estadoComboBox);

        JLabel capitalLabel = new JLabel("Capital:");
        capitalLabel.setBounds(10, 50, 150, 25);
        panel.add(capitalLabel);

        JTextField capitalTextField = new JTextField();
        capitalTextField.setBounds(150, 50, 120, 25);
        capitalTextField.setEditable(false);
        panel.add(capitalTextField);

        estadoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEstado = (String) estadoComboBox.getSelectedItem();
                for (String[] estado : estadosCapitales) {
                    if (estado[0].equals(selectedEstado)) {
                        capitalTextField.setText(estado[1]);
                        break;
                    }
                }
            }
        });
    }
}
