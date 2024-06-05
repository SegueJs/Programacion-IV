package vista;
import javax.swing.*;
import controlador.RoomControl;
import modelo.Reservation;
import java.util.List;



public class ReservationHistoryFrame extends JFrame {
  private JTextArea historyArea;

  public ReservationHistoryFrame() {
    super("Historial de Reservas - MyHotel");

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    historyArea = new JTextArea(20, 40);
    historyArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(historyArea);

    panel.add(scrollPane);
    add(panel);

    loadReservationHistory();

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void loadReservationHistory() {
    List<Reservation> reservations = RoomControl.getReservations();
    if (reservations.isEmpty()) {
      historyArea.setText("No hay reservas realizadas.");
    } else {
      StringBuilder history = new StringBuilder();
      for (Reservation reservation : reservations) {
        history.append(reservation.toString()).append("\n");
      }
      historyArea.setText(history.toString());
    }
  }
}
