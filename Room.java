import java.io.Serializable;
public class Room implements Serializable {

    private int roomNumber;
    private String category;
    private double price;
    private boolean booked;
    private String customerName;
    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.booked = false;
        this.customerName = "";
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public String getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }
    public boolean isBooked() {
        return booked;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void bookRoom(String customerName) {
        this.booked = true;
        this.customerName = customerName;
    }
    public void cancelBooking() {
        this.booked = false;
        this.customerName = "";
    }
    @Override
    public String toString() {
        return roomNumber + "," +
               category + "," +
               price + "," +
               booked + "," +
               customerName;
    }
}
