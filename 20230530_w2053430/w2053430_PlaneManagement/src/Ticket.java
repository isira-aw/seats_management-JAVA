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

    public static void mainTXTprint(int rowNumber , int seatN ,String Information) {
            String rowN_letter;
        if ( rowNumber == 0 ){
            rowN_letter = "A";
        } else if ( rowNumber == 1 ) {
            rowN_letter = "B";
        } else if (rowNumber == 2 ) {
            rowN_letter = "C";
        } else {
            rowN_letter ="D";
        }

        String txt ="TicketFiles\\"+rowN_letter+(seatN+1)+".txt";
        try {
            File saveFileName = new File(txt);
            System.out.println("File created : " + saveFileName.getName());

            FileWriter saveFile = new FileWriter(saveFileName);
            saveFile.write(Information);
            saveFile.close();

        } catch (Exception e){
            System.out.println("Error..");
        }
    }
}

