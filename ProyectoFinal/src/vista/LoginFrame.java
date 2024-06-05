package vista;
import javax.swing.*;
import controlador.LoginControl;

public class LoginFrame extends JFrame {
  private JTextField emailField;
  private JPasswordField passwordField;

  public LoginFrame() {
    super("Inicio de Sesión - MyHotel");

    JPanel loginPanel = new JPanel();
    GroupLayout layout = new GroupLayout(loginPanel);
    loginPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    JLabel emailLabel = new JLabel("Correo:");
    JLabel passwordLabel = new JLabel("Contraseña:");
    emailField = new JTextField(20);
    passwordField = new JPasswordField(20);
    JButton loginButton = new JButton("Iniciar Sesión");
    JButton registerButton = new JButton("Registrarse");

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

    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(emailLabel)
                    .addComponent(passwordLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(emailField)
                    .addComponent(passwordField)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(loginButton)
                .addComponent(registerButton)));

    layout.setVerticalGroup(
        layout.createSequentialGroup()
            .addGap(50)
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
                .addComponent(registerButton)));

    add(loginPanel);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new LoginFrame());
  }
}
