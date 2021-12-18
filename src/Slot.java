public class Slot {
    boolean occupied;
    Car car;
    
    public Slot() {
        this.occupied = false;
    }

    public Car parkCar(String regNum, String color) {
        this.car = new Car(regNum, color);
        this.occupied = true;
        return this.car;
    }

    public void leave() {
        this.car = null;
        this.occupied = false;
    }

    public Car getCar() { return this.car; }
}
