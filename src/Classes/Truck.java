package Classes;

public class Truck extends Vehicle
{
    //Declare additonal attributes.
    private final int numTrailers;
    
    //Object constructor.
    public Truck(String registration, String vehicleMake, int numTrailers) 
    {
        super(registration, vehicleMake);
        this.numTrailers = numTrailers;
    }

    //Accessor methods.
    public int getNumTrailers() 
    {
        return numTrailers;
    }
    
    //Other methods.
    @Override
    public int calculateBasicTripCost() 
    {
        int basicTripCost;
        
        if(numTrailers >= 2)
        {
           return basicTripCost = 1500; 
        }
        else
        {
            return basicTripCost = 1250;
        }
    }

    @Override
    public String toString() 
    {
        return "Truck{" + "numTrailers=" + numTrailers + '}';
    }
    
    
    
    //Testing for truck objects.
    public static void main(String[] args)
    {
        System.out.println("Test for Truck class.");
        Truck test = new Truck("AD55FF0", "Toyota", 2);
        
        System.out.println(test.getNumTrailers());
        System.out.println(test.getRegistration());
        System.out.println(test.getVehicleMake());
        System.out.println(test.calculateBasicTripCost());
        System.out.println("");
    }
}
