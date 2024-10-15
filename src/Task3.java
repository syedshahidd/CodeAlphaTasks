import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Room {
    private final String roomType;
    private boolean isAvailable;
    private final double price;

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.isAvailable = true;  // Rooms are available by default
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return price;
    }
}

class Reservation {
    private final String guestName;
    private final Room room;
    private final int stayDuration;
    private final double totalCost;

    public Reservation(String guestName, Room room, int stayDuration) {
        this.guestName = guestName;
        this.room = room;
        this.stayDuration = stayDuration;
        this.totalCost = room.getPrice() * stayDuration;
    }

    public String getReservationDetails() {
        return "Guest Name: " + guestName + "\nRoom Type: " + room.getRoomType() +
                "\nDuration: " + stayDuration + " nights\nTotal Cost: $" + totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }
}

public class Task3 extends JFrame {
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    private final JTextArea textArea;
    private final JTextField guestNameField;
    private final JTextField stayDurationField;
    private final JComboBox<String> roomTypeComboBox;

    public Task3() {
        // Initialize the GUI components
        setTitle("Hotel Reservation System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize rooms
        initializeRooms();

        // GUI Components
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        add(new JScrollPane(textArea));

        guestNameField = new JTextField(20);
        stayDurationField = new JTextField(5);
        roomTypeComboBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});

        add(new JLabel("Guest Name:"));
        add(guestNameField);
        add(new JLabel("Room Type:"));
        add(roomTypeComboBox);
        add(new JLabel("Stay Duration (nights):"));
        add(stayDurationField);

        JButton searchButton = new JButton("Search Rooms");
        JButton reserveButton = new JButton("Make Reservation");
        JButton viewButton = new JButton("View Bookings");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRooms();
            }
        });

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeReservation();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBookings();
            }
        });

        add(searchButton);
        add(reserveButton);
        add(viewButton);
    }

    private void initializeRooms() {
        rooms.add(new Room("Single", 100));
        rooms.add(new Room("Double", 150));
        rooms.add(new Room("Suite", 300));
    }

    private void searchRooms() {
        textArea.setText("Available Rooms:\n");
        boolean roomsAvailable = false;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                textArea.append(room.getRoomType() + " - $" + room.getPrice() + "\n");
                roomsAvailable = true;
            }
        }
        if (!roomsAvailable) {
            textArea.append("No rooms available.\n");
        }
    }

    private void makeReservation() throws NumberFormatException, IllegalArgumentException {
        String guestName = guestNameField.getText().trim();
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        int stayDuration;

        stayDuration = Integer.parseInt(stayDurationField.getText());
        if (stayDuration <= 0) {
            throw new IllegalArgumentException("Stay duration must be greater than 0.");
        }

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(this, "Room not available or invalid room type.");
            return;
        }

        Reservation reservation = new Reservation(guestName, selectedRoom, stayDuration);
        selectedRoom.setAvailable(false);
        reservations.add(reservation);
        textArea.append("Reservation successful!\n");
        textArea.append(reservation.getReservationDetails() + "\n");
    }

    private void viewBookings() {
        textArea.setText("Booking Details:\n");
        if (reservations.isEmpty()) {
            textArea.append("No bookings found.\n");
        } else {
            for (Reservation reservation : reservations) {
                textArea.append(reservation.getReservationDetails() + "\n\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Task3 system = new Task3();
            system.setVisible(true);
        });
    }
}