package vista;
import javax.swing.*;
import java.awt.*;
import controlador.RoomControl;
import modelo.Room;


public class DeleteRoomFrame extends JFrame {
  private JComboBox<Room> roomComboBox;

  public DeleteRoomFrame() {
    super("Eliminar Habitación - MyHotel");

    JPanel deletePanel = new JPanel();
    GroupLayout layout = new GroupLayout(deletePanel);
    deletePanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    JLabel roomLabel = new JLabel("Seleccionar Habitación:");
    roomLabel.setFont(new Font("Arial", Font.BOLD, 16));

    roomComboBox = new JComboBox<>(RoomControl.getRooms().toArray(new Room[0]));
    roomComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

    JButton deleteButton = new JButton("Eliminar Habitación");
    deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
    deleteButton.setBackground(Color.RED);
    deleteButton.setForeground(Color.WHITE);

    deleteButton.addActionListener(e -> {
      Room selectedRoom = (Room) roomComboBox.getSelectedItem();
      if (selectedRoom != null) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta habitación?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          RoomControl.deleteRoom(selectedRoom);
          JOptionPane.showMessageDialog(this, "Habitación eliminada satisfactoriamente", "Operación Exitosa",
              JOptionPane.INFORMATION_MESSAGE);
          roomComboBox.removeItem(selectedRoom);
        }
      }
    });

    GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
    hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        .addComponent(roomLabel));
    hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(roomComboBox)
        .addComponent(deleteButton));
    layout.setHorizontalGroup(hGroup);

    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
    vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(roomLabel)
        .addComponent(roomComboBox));
    vGroup.addComponent(deleteButton);
    layout.setVerticalGroup(vGroup);

    add(deletePanel);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
