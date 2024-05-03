package src.vista;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {
    private JComboBox<String> reservationsDropdown;
    private JComboBox<String> roomsDropdown;
    private JComboBox<String> usersDropdown;

    public HomeFrame(String role) {
        setTitle("Home - " + role);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        String[] adminReservationsOptions = {"Ver Reservaciones", "Modificar Reservacion", "Cancelar Reservacion"};
        String[] clientReservationsOptions = {"Hacer Reservacion", "Ver mis Reservaciones"};
        String[] adminRoomsOptions = {"Agregar Cuarto", "Modificar Cuarto", "Remover Cuarto"};
        String[] adminUsersOptions = {"Agregar Usuario", "Modificar Usuario", "Remover Usuario"};

        reservationsDropdown = new JComboBox<>(role.equals("admin") ? adminReservationsOptions : clientReservationsOptions);
        add(new JLabel("Reservaciones:"));
        add(reservationsDropdown);

        if (role.equals("admin")) {
            roomsDropdown = new JComboBox<>(adminRoomsOptions);
            add(new JLabel("Cuartos:"));
            add(roomsDropdown);

            usersDropdown = new JComboBox<>(adminUsersOptions);
            add(new JLabel("Usuarios:"));
            add(usersDropdown);
        }

        reservationsDropdown.addActionListener(e -> handleSelection(reservationsDropdown.getSelectedItem().toString(), "Reservaciones"));
        if (role.equals("admin")) {
            roomsDropdown.addActionListener(e -> handleSelection(roomsDropdown.getSelectedItem().toString(), "Cuartos"));
            usersDropdown.addActionListener(e -> handleSelection(usersDropdown.getSelectedItem().toString(), "Usuarios"));
        }
    }

    private void handleSelection(String option, String category) {
        JOptionPane.showMessageDialog(this, "Opcion seleccionada: " + option);
    }

    public JComboBox<String> getReservationsDropdown() {
        return reservationsDropdown;
    }

    public JComboBox<String> getRoomsDropdown() {
        return roomsDropdown;
    }

    public JComboBox<String> getUsersDropdown() {
        return usersDropdown;
    }
}
