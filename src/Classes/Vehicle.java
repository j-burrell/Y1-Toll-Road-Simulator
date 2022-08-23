package Classes;

public abstract class Vehicle   
{

    //Declare attributes.
    private final String registration;
    private final String vehicleMake;
    
    //Object constructor.
    public Vehicle(String registration, String vehicleMake)
    {
        this.registration = registration;
        this.vehicleMake = vehicleMake;
    }
    
    //Accessor methods
    public String getRegistration() 
    {
        return registration;
    }
    
    public String getVehicleMake() 
    {
        return vehicleMake;
    }
    
    //Other methods.
    public abstract int calculateBasicTripCost();

    @Override
    public String toString() 
    {
        return "Vehicle{" + "registration=" + registration + ", vehicleMake=" + vehicleMake + '}';
    }
   
}
