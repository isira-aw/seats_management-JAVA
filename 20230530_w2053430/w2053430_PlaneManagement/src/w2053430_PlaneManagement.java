import java.util.Scanner;
public class w2053430_PlaneManagement {
    static int[][] seatArray = new int[4][14];
    static boolean QuitTorF = true;
    static String[][] ticketArray = new String[4][14];
    private static double total;

    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application\n");
        do {
            mainProcess();
        } while ( QuitTorF );
    }
    public static void mainProcess(){
        String[] startQuestions = {"   1) Buy a seat",
                "   2) Cancel a seat",
                "   3) Find first available seat",
                "   4) Show seating plan",
                "   5) Print tickets information and total sales",
                "   6) Search ticket",
                "   0) Quit"
        };
        System.out.println("""
                *******************************************************
                *                    MENU OPTIONS                     *
                *******************************************************""");
        for (String temp : startQuestions) {
            System.out.println(temp);
        }
        System.out.println("*******************************************************\nPlease select an option :");

        int startNumber;
        startNumber = getStartNumber();
        switch (startNumber) {
            case 1:
                Scanner input = new Scanner(System.in);
                System.out.print("Enter name    :");    // getting person information
                String nameInput = input.nextLine();
                System.out.print("Enter surname :");
                String surnameInput = input.nextLine();
                System.out.print("Enter email   :");
                String emailInput = input.nextLine();

                int rowNumber = getRowNumber();
                int seatNumber = getSeatNumber();
                if ( backSeat(rowNumber, seatNumber)  ){  // is this condition true give a msg and rerun code
                    break;
                } else if (buy_seat(rowNumber, seatNumber)) {
//                    buy_seat(rowNumber, seatNumber);       // because buy_seat was run one time in else if condition
                    Person personO = new Person(nameInput ,surnameInput ,emailInput); // call person class
                    double price = getSeatPrice(seatNumber);                          // check the value for ech seat
                    Ticket ticketO = new Ticket( rowNumber , seatNumber , price , personO );
                    total=total+price;
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
                    String TicketInformation ="1) Seat Number : "+rowN_letter+" "+(seatNumber+1)+
                            "\n2) Name        : "+personO.getName()+          // getter use to build one string
                            "\n3) Surname     : "+ personO.getSurname()+
                            "\n4) Email       : "+personO.getEmail()+
                            "\n5) Price       : £"+ticketO.getPrice();
                    ticketArray[rowNumber][seatNumber] = TicketInformation;
                    Ticket.mainTXTprint(rowNumber ,seatNumber ,TicketInformation);
                    break;
                } else {
                    break;
                }
            case 2:
                System.out.println("Cancellation of booking");
                int rowNumber2 = getRowNumber();
                int seatNumber2 = getSeatNumber();
                if ( backSeat(rowNumber2, seatNumber2)){
                    break;
                } else if(ticketArray[rowNumber2][seatNumber2] != null){
                    cancel_seat(rowNumber2, seatNumber2);
                    double reducePrice = getSeatPrice( seatNumber2 );
                    total = total - reducePrice ;
                    break;
                } else {
                    System.out.println("Free seat ..");
                    break;
                }
            case 3:
                find_first_available();
                break;
            case 4:
                show_seating_plan();
                break;
            case 5:
                for (int i =0 ; i < ticketArray.length ; i ++){
                    for( int j=0 ; j < ticketArray[i].length ; j++){
                        if (ticketArray[i][j] != null ){
                            System.out.print(ticketArray[i][j]);
                            System.out.println("\n**********************************\n");
                        }
                    }
                }
                System.out.println("Total Amount Is = £"+ total);
                break;
            case 6:
                int rowNumber3 = getRowNumber();
                int seatNumber3 = getSeatNumber();
                if(backSeat( rowNumber3 ,seatNumber3)){
                    break;
                } else {
                    search_ticket( rowNumber3 , seatNumber3 );
                }
                break;
            case 0:
                QuitTorF = Quit();
                break;
            default:
                System.out.println("WRONG INPUT>>>>>>>>>");
                break;
        }
    }
    public static boolean backSeat(int i , int j){
        if ((i == 1 && j == 12) | (i == 1 && j == 13) | (i == 2 && j == 12) | (i == 2 && j == 13)){
            System.out.println("Not seat found");
            return true;
        } else {
            return false;
        }
    }
    public static int getStartNumber() {
        Scanner inputNumber = new Scanner(System.in);
        while(true){
            try {
                int startN = inputNumber.nextInt();
                if (0 <= startN && startN <= 6){
                    return startN;
                }else {
                    System.out.println("Wrong Input...");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Wrong Input...");
                inputNumber.nextLine();
            }
        }
    }
    //getting seat number
    public static int getRowNumber() {
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Enter a row letter and a seat number ");
                System.out.print("Row letter (A B C D) :");
                String rowLetter = input.nextLine();
                int rowNumber = 0;
                String lowerRowLetter = rowLetter.toLowerCase();
                switch(lowerRowLetter){
                    case "a":
                        rowNumber=0;
                        break;
                    case "b":
                        rowNumber=1;
                        break;
                    case "c":
                        rowNumber=2;
                        break;
                    case "d":
                        rowNumber=3;
                        break;
                    default:
                        System.out.println("Wrong Letter...");
                        continue;
                }
                return rowNumber;
            } catch (Exception e) {
                System.out.println("Wrong Input....");
            }
        }
    }
    //getting seat Number
    public static int getSeatNumber() {
        Scanner input = new Scanner(System.in);
        while(true){
            try {
                System.out.print("Seat number (1 - 14) :");
                int seatNumber = input.nextInt();

                if(seatNumber <= 14 && seatNumber >= 1 ){
                    return seatNumber-1;
                }else {
                    System.out.println("Wrong number....");
                }
            } catch (Exception e) {
                System.out.println("Wrong  Input...");
                input.next();
            }
        }
    }
    //  buy_seat
    public static boolean buy_seat(int rowNumber, int seatNumber) {
        if (seatArray[rowNumber][seatNumber] == 0) {
            System.out.println("Free seat.Booked for you");
            seatArray[rowNumber][seatNumber] = 1;
            return true;
       } else {
            System.out.println("NOT available");
            return false;
        }
    }
    //cancel_seat
    public static void cancel_seat(int rowNumber, int seatNumber) {
        if (seatArray[rowNumber][seatNumber] == 0) {
            System.out.println("It is now Free seat");
        } else {
            System.out.println("Cancel seat booking");
            seatArray[rowNumber][seatNumber] = 0;
            ticketArray[rowNumber][seatNumber] = null;
        }
    }
    //find first available
    public static void find_first_available() {
        L1:for (int i = 0 ; i < seatArray.length; i++) {
            for (int j = 0; j < seatArray[i].length; j++) {
                seatArray[1][12] = 1;
                seatArray[1][13] = 1;
                seatArray[2][12] = 1;
                seatArray[2][13] = 1;
                if (seatArray[i][j] == 0) {
                    System.out.print("Free seat : ");
                    String I;
                    if (i == 0) {
                        I = "A";
                    } else if (i == 1) {
                        I = "B";
                    } else if (i == 2) {
                        I = "C";
                    } else {
                        I = "D";
                    }
                    System.out.print(I + " " + (j + 1));
                    System.out.println(" ");
                    break L1; // L1 labeled for loop help to break full loop
                }
                System.out.println();
            }
        }
    }
    //show seating plan
    public static void show_seating_plan(){
       for (int i=0 ; i < seatArray.length ; i++){
           for (int j=0 ; j < seatArray[i].length ;j++ ) {
                if((i==1 && j==12) || (i==1 && j==13)|| (i==2 && j==12)|| (i==2 && j==13)){
                    System.out.print("   ");
                }else if (seatArray[i][j] == 0 ){
                    System.out.print(" O ");
                } else  {
                    System.out.print(" X ");
                }
           }
        System.out.println();
    }
}
    public static boolean Quit(){return false;}
    public static double getSeatPrice(int seatNumber) {
        seatNumber++;
        if (seatNumber >= 1 && seatNumber <= 5 ) {
            return 200;
        } else if (seatNumber >= 6 && seatNumber <= 9) {
            return 150;
        } else if (seatNumber >= 10 && seatNumber <= 14) {
            return 180;
        }else {
            return 0;
        }
    }
    public static void  search_ticket(int row , int seat) {
        if (seatArray[row][seat] == 0){
            System.out.println("This seat is available.");
        } else {
            System.out.println(ticketArray[row][seat]);
        }
    }

}

