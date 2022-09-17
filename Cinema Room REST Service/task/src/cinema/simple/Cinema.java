package cinema.simple;

import java.util.ArrayList;

public class Cinema {
    private int total_rows, total_columns;

    private ArrayList<Seat> available_seats = new ArrayList<>();


    private void fillSeat (ArrayList<Seat> map) {
        for (int i = 1; i <= this.total_rows; i++) {
            for (int j = 1; j <= this.total_columns; j++) {
                map.add(new Seat(i, j));
            }
        }
    }

    public ArrayList<Seat> getAvailable_seats() {
        return available_seats;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        fillSeat(this.available_seats);
    }
    public Cinema(){}
}
