package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import controlador.RoomControl;
import modelo.Reservation;
import modelo.Room;


public class MakeReservationFrame extends JFrame {
  private JComboBox<Room> roomComboBox;
  private JTextField nameField;
  private JTextField checkInDateField;
  private JTextField checkOutDateField;
  private JTextField numGuestsField;
  private SimpleDateFormat dateFormat;

  public MakeReservationFrame() {
    super("Realizar Reserva - MyHotel");

    dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);

    JLabel roomLabel = new JLabel("Seleccionar Habitación:");
    JLabel nameLabel = new JLabel("Nombre:");
    JLabel checkInDateLabel = new JLabel("Fecha de Entrada (yyyy-MM-dd):");
    JLabel checkOutDateLabel = new JLabel("Fecha de Salida (yyyy-MM-dd):");
    JLabel numGuestsLabel = new JLabel("Número de Huéspedes:");

    List<Room> rooms = RoomControl.getRooms();
    roomComboBox = new JComboBox<>(rooms.toArray(new Room[0]));
    nameField = new JTextField(20);
    checkInDateField = new JTextField(10);
    checkOutDateField = new JTextField(10);
    numGuestsField = new JTextField(5);

    JButton reserveButton = new JButton("Realizar Reserva");

    reserveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Room selectedRoom = (Room) roomComboBox.getSelectedItem();
        String name = nameField.getText();
        String checkInDateStr = checkInDateField.getText();
        String checkOutDateStr = checkOutDateField.getText();
        String numGuestsStr = numGuestsField.getText();

        if (selectedRoom != null && !name.isEmpty() && !checkInDateStr.isEmpty() && !checkOutDateStr.isEmpty()
            && !numGuestsStr.isEmpty()) {
          try {
            Date checkInDate = dateFormat.parse(checkInDateStr);
            Date checkOutDate = dateFormat.parse(checkOutDateStr);
            int numGuests = Integer.parseInt(numGuestsStr);

            if (numGuests <= selectedRoom.getCapacity()) {
              Reservation newReservation = new Reservation(checkInDate, checkOutDate, name, numGuests);
              boolean reservationSuccessful = RoomControl.bookRoom(selectedRoom, newReservation);
              if (reservationSuccessful) {
                JOptionPane.showMessageDialog(MakeReservationFrame.this, "Reserva realizada satisfactoriamente", "Operación Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
              } else {
                JOptionPane.showMessageDialog(MakeReservationFrame.this, "La habitación ya no está disponible para las fechas seleccionadas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
              }
            } else {
              JOptionPane.showMessageDialog(MakeReservationFrame.this, "El número de huéspedes excede la capacidad de la habitación.", "Error",
                  JOptionPane.ERROR_MESSAGE);
            }
          } catch (ParseException ex) {
            JOptionPane.showMessageDialog(MakeReservationFrame.this, "Por favor, ingrese fechas en el formato correcto (yyyy-MM-dd).", "Error",
                JOptionPane.ERROR_MESSAGE);
          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(MakeReservationFrame.this, "Por favor, ingrese un número válido de huéspedes.", "Error",
                JOptionPane.ERROR_MESSAGE);
          }
        } else {
          JOptionPane.showMessageDialog(MakeReservationFrame.this, "Por favor, complete todos los campos.", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(roomLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
    panel.add(roomComboBox, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    panel.add(nameLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    panel.add(nameField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    panel.add(checkInDateLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 2;
    panel.add(checkInDateField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    panel.add(checkOutDateLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 3;
    panel.add(checkOutDateField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 4;
    panel.add(numGuestsLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 4;
    panel.add(numGuestsField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 5;
    constraints.gridwidth = 2;
    panel.add(reserveButton, constraints);

    add(panel);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
