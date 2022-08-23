package Classes;

public class Car extends Vehicle
{
    //Declare additional attributes.
    private final int numberOfSeats;
    
    //Object constructor.
    public Car(String registration, String vehicleMake, int numberOfSeats) 
    {
        super(registration, vehicleMake);
        this.numberOfSeats = numberOfSeats;
    }

    //Accessor methods.
    public int getNumberOfSeats() 
    {
        return numberOfSeats;
    }
    
    //Other methods.
    @Override
    public int calculateBasicTripCost() 
    {
        int basicTripCost;
        
        if(numberOfSeats > 5)
        {
            return basicTripCost = 600;
        } 
        else
        {
            return basicTripCost = 500;
        }
    }
    
    @Override
    public String toString() 
    {
        return "Car{" + "numberOfSeats=" + numberOfSeats + '}';
    }
    
    //Testing for car objects.
    public static void main(String[] args)
    {
        System.out.println("Test for Car class.");
        Car test = new Car("AD55FF0", "Toyota", 5);
        
        System.out.println(test.getNumberOfSeats());
        System.out.println(test.getRegistration());
        System.out.println(test.getVehicleMake());
        System.out.println(test.calculateBasicTripCost());
        System.out.println("");
    }

}
