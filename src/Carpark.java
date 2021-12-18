import java.util.ArrayList;

public class Carpark {
    ArrayList<Slot> slots = new ArrayList<Slot>();
    ArrayList<Integer> unoccupiedSlotNumbers = new ArrayList<Integer>();
    
    public Carpark(int slots) {
        int slotNumber = 0;
        while (slotNumber < slots) {
            Slot slot = new Slot();
            this.slots.add(slot);
            unoccupiedSlotNumbers.add(slotNumber);
            slotNumber++;
        }
        System.out.println("Created a parking lot with " + slotNumber + " slots");
    }

    public void enter(String regNum, String color) {
        if (unoccupiedSlotNumbers.size() == 0) {
            System.out.println("Sorry, parking lot is full");
            return;
        }

        Integer slotNumberAssigned = unoccupiedSlotNumbers.remove(0);
        slots.get(slotNumberAssigned).parkCar(regNum, color);
        System.out.println("Allocated slot number: " + (slotNumberAssigned + 1));
    }

    public void exit(Integer slotNum) {
        slots.get(slotNum - 1).leave();

        if (unoccupiedSlotNumbers.size() == 0) unoccupiedSlotNumbers.add(0, slotNum - 1);
        else this.slotNewUnoccupied(slotNum - 1, 0, unoccupiedSlotNumbers.size()-1);
        
        System.out.println("Slot number " + slotNum + " is free");
    }

    private void slotNewUnoccupied(int slotNum, int startIndex, int endIndex) {
        int midIndex = (int) Math.floor(startIndex + endIndex / 2);
        
        if (unoccupiedSlotNumbers.get(midIndex) > slotNum) {
            if (endIndex - startIndex < 2) unoccupiedSlotNumbers.add(midIndex, slotNum);
            else slotNewUnoccupied(slotNum, startIndex, midIndex);
        }
        else if (unoccupiedSlotNumbers.get(midIndex) < slotNum) {
            if (endIndex == startIndex) unoccupiedSlotNumbers.add(midIndex+1, slotNum);
            else slotNewUnoccupied(slotNum, midIndex + 1, endIndex);
        }
    }

    public void printStatus() {
        System.out.println("Slot No. Registration No Colour");
        int slotNumber = 1;
        for(Slot slot : slots) {
            if (!slot.occupied) {
                slotNumber++;
                continue;
            }
            System.out.println(slotNumber++);
            System.out.println(slot.getCar().getRegNum());
            System.out.println(slot.getCar().getColor());
        }
    }

    public void findCarsWithColors(boolean regNum, String color) {
        boolean found = false;
        int slotNumber = 1;
        for(Slot slot : slots) {
            if (!slot.occupied) {
                slotNumber++;
                continue;
            }
            if (slot.getCar().getColor().equalsIgnoreCase(color)) {
                if (found) System.out.print(", ");
                if (regNum) System.out.print(slot.getCar().getRegNum());
                else System.out.print(slotNumber);
                found = true;
            }
            slotNumber++;
        }
        if (!found) System.out.print("Not found");
        System.out.println();
    }

    public void findSlotNumberWithRegNum(String regNum) {
        boolean found = false;
        int slotNumber = 1;
        for(Slot slot : slots) {
            if (!slot.occupied) {
                slotNumber++;
                continue;
            }
            if (slot.getCar().getRegNum().equalsIgnoreCase(regNum)) {
                System.out.print(slotNumber);
                found = true;
                break;
            }
            slotNumber++;
        }
        if (!found) System.out.print("Not found");
        System.out.println();
    }
}