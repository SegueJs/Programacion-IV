package vista;

import javax.swing.*;
import controlador.LoginControl;
import java.net.URL;
import java.util.List;
import java.util.Arrays;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel bannerLabel;
    private List<String> imageUrls;
    private int currentImageIndex;

    public LoginFrame() {
        super("Inicio de Sesión - MyHotel");

        imageUrls = Arrays.asList(
            "https://i.pinimg.com/originals/06/a8/5b/06a85b703ccc50fcc2214bac56214f48.gif",
            "https://i.pinimg.com/originals/cb/10/99/cb109999e76292b5288868a61c043b90.gif",
            "https://i.redd.it/1asyzovvrn8a1.gif" // URL de un GIF
        );
        currentImageIndex = 0;

        JPanel loginPanel = new JPanel();
        GroupLayout layout = new GroupLayout(loginPanel);
        loginPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        bannerLabel = new JLabel();
        updateBannerImage();

        JLabel emailLabel = new JLabel("Correo:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Iniciar Sesión");
        JButton registerButton = new JButton("Registrarse");
        JButton changeBackgroundButton = new JButton("Cambiar Fondo");

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Por favor, complete todos los campos", "Campos Vacíos",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (email.equals("admin@admin.com") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Inicio de sesión como Administrador", "¡Bienvenido!",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new AdminFrame();
                } else {
                    boolean access = LoginControl.login(email, password);
                    if (access) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Inicio de sesión exitoso", "¡Bienvenido!",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new HomeFrame();
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Credenciales incorrectas", "Error de Inicio de Sesión",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        registerButton.addActionListener(e -> {
            dispose();
            RegisterFrame registerScreen = new RegisterFrame();
            registerScreen.setVisible(true);
        });

        changeBackgroundButton.addActionListener(e -> {
            currentImageIndex = (currentImageIndex + 1) % imageUrls.size();
            updateBannerImage();
        });

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(bannerLabel)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(emailLabel)
                                        .addComponent(passwordLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(emailField)
                                        .addComponent(passwordField)))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginButton)
                                .addComponent(registerButton))
                        .addComponent(changeBackgroundButton)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(bannerLabel)
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(emailField))
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField))
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(loginButton)
                                .addComponent(registerButton))
                        .addGap(20)
                        .addComponent(changeBackgroundButton)
        );

        add(loginPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateBannerImage() {
        try {
            URL url = new URL(imageUrls.get(currentImageIndex));
            ImageIcon bannerIcon = new ImageIcon(url);
            bannerLabel.setIcon(bannerIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
