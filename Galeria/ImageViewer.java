import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageViewer extends JFrame {
    private JLabel imageLabel;
    private JButton previousButton;
    private JButton nextButton;

    private String[] imagePaths = {
            "imagen1.jpg",  // Ruta de la primera imagen
            "imagen2.jpg",  // Ruta de la segunda imagen
            "imagen3.jpg",  // Ruta de la segunda imagen
            "imagen4.jpg",  // Ruta de la segunda imagen
            // Agrega más rutas de imágenes aquí según sea necesario
    };
    private int currentImageIndex = 0;

    public ImageViewer() {
        setTitle("Image Viewer");
        setSize(400, 400);
        setLayout(null); // Establece el diseño a absoluto
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE); // Establece el color de fondo de la ventana

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBounds(50, 50, 300, 300); // Establece la posición y el tamaño del JLabel
        add(imageLabel);

        previousButton = new JButton("Anterior");
        previousButton.setBounds(50, 10, 100, 30); // Establece la posición y el tamaño del botón anterior
        previousButton.setBackground(Color.LIGHT_GRAY); // Establece el color de fondo del botón anterior
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousImage();
            }
        });
        add(previousButton);

        nextButton = new JButton("Siguiente");
        nextButton.setBounds(250, 10, 100, 30); // Establece la posición y el tamaño del botón siguiente
        nextButton.setBackground(Color.LIGHT_GRAY); // Establece el color de fondo del botón siguiente
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });
        add(nextButton);

        showImage();
    }

    private void showImage() {
        Icon icon = new ImageIcon(new ImageIcon(getClass().getResource(imagePaths[currentImageIndex])).getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), 0));
        imageLabel.setIcon(icon);
    }

    private void showPreviousImage() {
        currentImageIndex = (currentImageIndex - 1 + imagePaths.length) % imagePaths.length;
        showImage();
    }

    private void showNextImage() {
        currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
        showImage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageViewer().setVisible(true);
            }
        });
    }
}
