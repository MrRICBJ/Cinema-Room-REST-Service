package cinema.model;

import org.springframework.stereotype.Component;

@Component
public class Statistics {
    private int current_income;
    private int number_of_available_seats = 81;
    private int number_of_purchased_tickets;

    public Statistics() {}

    public void setCurrent_income(int current_income, int index) {
        this.current_income = index > 0 ? this.current_income + current_income : this.current_income - current_income;
    }

    public void setNumber_of_available_seats(int index) {
        this.number_of_available_seats = index > 0 ? this.number_of_available_seats + 1 : this.number_of_available_seats - 1;
    }

    public void setNumber_of_purchased_tickets(int index) {
        this.number_of_purchased_tickets = index > 0 ? this.number_of_purchased_tickets + 1 : this.number_of_purchased_tickets - 1;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }
}
