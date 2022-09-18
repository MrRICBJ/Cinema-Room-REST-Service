package cinema.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Cinema{
    private final int total_rows = 9;
    private final int total_columns = 9;

    private final ArrayList<Seat> available_seats = new ArrayList<>();

    public ArrayList<Seat> getAvailable_seats() {
        return available_seats;
    }

    public int getTotal_rows() {
        return total_rows;
    }
    public int getTotal_columns() {
        return total_columns;
    }

    public Cinema() {
        fillSeat(this.available_seats);
    }

    public void fillSeat(ArrayList<Seat> map) {
        for (int i = 1; i <= this.total_rows; i++) {
            for (int j = 1; j <= this.total_columns; j++) {
                map.add(new Seat(i, j));
            }
        }
    }

}
