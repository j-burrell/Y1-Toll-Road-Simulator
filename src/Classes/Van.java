package Classes;

public class Van extends Vehicle
{
    //Declare additional attributes.
    private final int payload;

    //Object Constructor
    public Van(String registration, String vehicleMake, int payload) 
    {
        super(registration, vehicleMake);
        this.payload = payload;
    }

    //Accessor methods.
    public int getPayload() 
    {
        return payload;
    }
    
    //Other methods.
    @Override
    public int calculateBasicTripCost() 
    {
        int basicTripCost;

        if(payload <= 600)
        {
            return basicTripCost = 500;
        }
        else if(payload <= 800 && payload > 600)
        {
            return basicTripCost = 750;
        }
        else
        {
            return basicTripCost = 1000;
        }
    }
    
    @Override
    public String toString() 
    {
        return "Van{" + "payload=" + payload + '}';
    }
    
    //Testing for van objects.
    public static void main(String[] args)
    {
        System.out.println("Test for Van class.");
        Van test = new Van("AD55FF0", "Toyota", 650);
        
        System.out.println(test.getPayload());
        System.out.println(test.getRegistration());
        System.out.println(test.getVehicleMake());
        System.out.println(test.calculateBasicTripCost());
        System.out.println("");
    }

}
