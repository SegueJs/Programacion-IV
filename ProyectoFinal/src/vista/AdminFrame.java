package vista;

import javax.swing.*;


public class AdminFrame extends JFrame {
  public AdminFrame() {
    super("Administrador - MyHotel");

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null);

    createMenu();

    setVisible(true);
  }

  private void createMenu() {
    JMenuBar menuBar = new JMenuBar();

    JMenu adminMenu = new JMenu("Administración");

    JMenuItem checkAvailabilityItem = new JMenuItem("Verificar Disponibilidad");
    JMenuItem addRoomItem = new JMenuItem("Agregar Habitación al Inventario");
    JMenuItem editRoomItem = new JMenuItem("Editar Habitación en el Inventario");
    JMenuItem deleteRoomItem = new JMenuItem("Eliminar Habitación del Inventario");

    checkAvailabilityItem.addActionListener(e -> new RoomAvailableFrame());
    addRoomItem.addActionListener(e -> new AddRoomFrame());
    editRoomItem.addActionListener(e -> new EditRoomFrame());
    deleteRoomItem.addActionListener(e -> new DeleteRoomFrame());

    adminMenu.add(checkAvailabilityItem);
    adminMenu.add(addRoomItem);
    adminMenu.add(editRoomItem);
    adminMenu.add(deleteRoomItem);

    JMenuItem logoutItem = new JMenuItem("Cerrar sesión");
    logoutItem.addActionListener(e -> {
      dispose();
      new LoginFrame();
    });

    menuBar.add(adminMenu);
    menuBar.add(logoutItem);

    setJMenuBar(menuBar);
  }
}
