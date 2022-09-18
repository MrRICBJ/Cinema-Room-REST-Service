package cinema.model;

import org.springframework.stereotype.Component;

@Component
public class Seat {
    private int row;
    private int column;
    private int price;

    public int getPrice() {
        return price;
    }

    public int getRow() {
        return row;
    }

    public void setPrice() {
        this.price = this.row <= 4 ? 10 : 8;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }

    public Seat() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }
}
