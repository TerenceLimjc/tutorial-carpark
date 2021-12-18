import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        String[] inputStrings = nextLine.split(" ");

        if (!inputStrings[0].equalsIgnoreCase("create_parking_lot")) {
            System.out.println("Incorrect input");
            return;
        }
        
        Carpark cp = new Carpark(Integer.parseInt(inputStrings[1]));
        
        while (sc.hasNextLine()) {
            nextLine = sc.nextLine();
            inputStrings = nextLine.split(" ");

            switch (inputStrings[0]) {
                case "park":
                    cp.enter(inputStrings[1], inputStrings[2]);
                    break;
                case "leave":
                    cp.exit(Integer.parseInt(inputStrings[1]));
                    break;
                case "status":
                    cp.printStatus();
                    break;
                case "registration_numbers_for_cars_with_colour":
                    cp.findCarsWithColors(true, inputStrings[1]);
                    break;
                case "slot_numbers_for_cars_with_colour":
                    cp.findCarsWithColors(false, inputStrings[1]);
                    break;
                case "slot_number_for_registration_number":
                    cp.findSlotNumberWithRegNum(inputStrings[1]);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Incorrect input");
                    return; 
            }
        }


    }
}
