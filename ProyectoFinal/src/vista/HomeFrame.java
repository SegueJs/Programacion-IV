package vista;
import javax.swing.*;



public class HomeFrame extends JFrame {
  public HomeFrame() {
    super("Inicio de sesión - MyHotel");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null);

    JMenuBar menuBar = new JMenuBar();

    JMenu bookingsMenu = new JMenu("Reservas");
    JMenuItem makeBookingItem = new JMenuItem("Realizar reserva");
    JMenuItem modifyBookingItem = new JMenuItem("Modificar reserva");
    JMenuItem cancelBookingItem = new JMenuItem("Cancelar reserva");
    JMenuItem bookingsHistoryItem = new JMenuItem("Historial de reservas");

    makeBookingItem.addActionListener(e -> {
      new MakeReservationFrame();
    });

    modifyBookingItem.addActionListener(e -> {
      new EditReservationFrame();
    });

    cancelBookingItem.addActionListener(e -> {
      new CancelReservationFrame();
    });

    bookingsHistoryItem.addActionListener(e -> {
      new ReservationHistoryFrame();
    });

    bookingsMenu.add(makeBookingItem);
    bookingsMenu.add(modifyBookingItem);
    bookingsMenu.add(cancelBookingItem);
    bookingsMenu.add(bookingsHistoryItem);

    JMenu roomMenu = new JMenu("Habitaciones");
    JMenuItem viewRoomItem = new JMenuItem("Ver detalles de habitación");
    JMenuItem searchRoomItem = new JMenuItem("Buscar Habitación");

    viewRoomItem.addActionListener(e -> {
      new SeeRoomFrame();
    });

    searchRoomItem.addActionListener(e -> {
      new SearchRoomFrame();
    });

    roomMenu.add(viewRoomItem);
    roomMenu.add(searchRoomItem);

    JMenuItem logoutItem = new JMenuItem("Cerrar sesión");
    logoutItem.addActionListener(e -> {
      dispose();
      new LoginFrame();
    });

    menuBar.add(bookingsMenu);
    menuBar.add(roomMenu);
    menuBar.add(logoutItem);

    setJMenuBar(menuBar);

    setVisible(true);
  }
}
