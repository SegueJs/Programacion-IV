package src.vista;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField idTypeField, idNumberField, firstNameField, lastNameField, emailField, residentialAddressField, cityOfResidenceField, contactPhoneNumberField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton backButton, registerButton;

    public RegisterFrame() {
        setTitle("Register");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(12, 2));

        idTypeField = new JTextField();
        idNumberField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        residentialAddressField = new JTextField();
        cityOfResidenceField = new JTextField();
        contactPhoneNumberField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        backButton = new JButton("Back");
        registerButton = new JButton("Register");

        add(new JLabel("Tipo de identificacion (CC, TI, PA):"));
        add(idTypeField);
        add(new JLabel("Numero de identificacion:"));
        add(idNumberField);
        add(new JLabel("Nombre:"));
        add(firstNameField);
        add(new JLabel("Apellido:"));
        add(lastNameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Direccion:"));
        add(residentialAddressField);
        add(new JLabel("Ciudad de residencia:"));
        add(cityOfResidenceField);
        add(new JLabel("Numero de contacto:"));
        add(contactPhoneNumberField);
        add(new JLabel("Contraseña:"));
        add(passwordField);
        add(new JLabel("Confirmar contraseña:"));
        add(confirmPasswordField);
        add(backButton);
        add(registerButton);
    }

    public JTextField getIdTypeField() { return idTypeField; }
    public JTextField getIdNumberField() { return idNumberField; }
    public JTextField getFirstNameField() { return firstNameField; }
    public JTextField getLastNameField() { return lastNameField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getResidentialAddressField() { return residentialAddressField; }
    public JTextField getCityOfResidenceField() { return cityOfResidenceField; }
    public JTextField getContactPhoneNumberField() { return contactPhoneNumberField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JPasswordField getConfirmPasswordField() { return confirmPasswordField; }
    public JButton getBackButton() { return backButton; }
    public JButton getRegisterButton() { return registerButton; }
}
