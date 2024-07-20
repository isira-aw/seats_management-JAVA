import java.io.*;
public class Ticket {
        private int row;
        private int seat;
        private double price;
        private Person  person;


        public Ticket(int row, int seat, double price, Person person) {
            this.row = row;
            this.seat = seat;
            this.price = price;
            this.person = person;
        }

    public Ticket() {

    }


    public void setRow(int row) {
            this.row = row;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setPerson(Person person) {this.person = person;}

        public int getRow() {
            return row;
        }

        public int getSeat() {
            return seat;
        }

        public double getPrice() {
            return price;
        }

        public Person getPerson() {
            return person;
        }

   
    }
}

