package src.controlador;

import src.modelo.User;
import src.vista.HomeFrame;
import src.vista.LoginFrame;
import src.vista.RegisterFrame;
import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {
    private ArrayList<User> users = new ArrayList<>();
    private LoginFrame loginFrame;
    private RegisterFrame registerFrame;

    public UserController() {
        loginFrame = new LoginFrame();
        registerFrame = new RegisterFrame();

        users.add(new User("CC", "0001", "Admin", "User", "admin@admin.com", "123 Admin St", "Admin City", "1234567890", "admin"));

        loginFrame.getLoginButton().addActionListener(e -> loginUser());
        loginFrame.getRegisterButton().addActionListener(e -> showRegisterForm());
        registerFrame.getRegisterButton().addActionListener(e -> registerUser());
        registerFrame.getBackButton().addActionListener(e -> registerFrame.setVisible(false));

        loginFrame.setVisible(true);
    }

    private void loginUser() {
        String email = loginFrame.getEmail();
        String password = loginFrame.getPassword();

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                openHomeFrame(user);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Credenciales invalidas", "Inicio de sesion fallido", JOptionPane.ERROR_MESSAGE);
    }

    private void openHomeFrame(User user) {
        String role = user.getEmail().equals("admin@admin.com") ? "admin" : "client";
        HomeFrame homeFrame = new HomeFrame(role);

        homeFrame.setVisible(true);
    }

    private void showRegisterForm() {
        registerFrame.setVisible(true);
    }

    private void registerUser() {
        String idType = registerFrame.getIdTypeField().getText().toUpperCase();
        String idNumber = registerFrame.getIdNumberField().getText();
        String firstName = registerFrame.getFirstNameField().getText();
        String lastName = registerFrame.getLastNameField().getText();
        String email = registerFrame.getEmailField().getText();
        String residentialAddress = registerFrame.getResidentialAddressField().getText();
        String cityOfResidence = registerFrame.getCityOfResidenceField().getText();
        String contactPhoneNumber = registerFrame.getContactPhoneNumberField().getText();
        String password = new String(registerFrame.getPasswordField().getPassword());
        String confirmPassword = new String(registerFrame.getConfirmPasswordField().getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(registerFrame, "Las contraseñas no coinciden.", "Error de registro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidIdType(idType) || !isValidIdNumber(idNumber) || !isValidEmail(email)) {
            return;
        }

        User newUser = new User(idType, idNumber, firstName, lastName, email, residentialAddress, cityOfResidence, contactPhoneNumber, password);
        users.add(newUser);
        JOptionPane.showMessageDialog(registerFrame, "Usuario registrado.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        registerFrame.setVisible(false);
    }

    private boolean isValidIdType(String idType) {
        if (!(idType.equals("CC") || idType.equals("TI") || idType.equals("PA"))) {
            JOptionPane.showMessageDialog(registerFrame, "Identificacion invalida. debe ser CC, TI o PA.", "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isValidIdNumber(String idNumber) {
        if (!idNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(registerFrame, "Numero de identificacion invalido. debe ser un numero.", "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(registerFrame, "Formato de email invalido.", "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
