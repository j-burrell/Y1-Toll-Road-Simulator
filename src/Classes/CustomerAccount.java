package Classes;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerAccount implements Comparable<CustomerAccount>
{
    //Enum for type of discount.
    private enum DiscountType
    {
        NONE,
        STAFF,
        FRIENDS_AND_FAMILY
    }
    
    //Declare attributes.
    private final String firstName;
    private final String lastName;
    private final Vehicle customerVehicle;
    private int accountBalance;
    private DiscountType customerDiscount = DiscountType.NONE;
    
    //Object constructor.
    public CustomerAccount(String firstName, String lastName, Vehicle customerVehicle, int accountBalance) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerVehicle = customerVehicle;
        this.accountBalance = accountBalance;
    }
    
    //Accessor methods.

    public String getFirstName() 
    {
        return firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }
    
    public Vehicle getCustomerVehicle() 
    {
        return customerVehicle;
    }

    public int getAccountBalance() 
    {
        return accountBalance;
    }

    public DiscountType getCustomerDiscount() 
    {
        return customerDiscount;
    }
    
    //Other methods.
    public void activateStaffDiscount()
    {
        customerDiscount = DiscountType.STAFF;
    }
    
    public void activateFriendsAndFamilyDiscount()
    {
        if(customerDiscount == DiscountType.STAFF)
        {
            System.out.println("Staff discount is currently active!");
        }
        else
        {
            customerDiscount = DiscountType.FRIENDS_AND_FAMILY;
        }
    }
    
    public void deactivateDiscount()
    {
        customerDiscount = DiscountType.NONE;
    }
    
    public void addFunds(int amount)
    {
        accountBalance = accountBalance + amount;
    }
    
    public int makeTrip() throws InsufficientAccountBalanceException
    {
        int tripCost = this.customerVehicle.calculateBasicTripCost();
        
        //Discount first.
        if(customerDiscount == DiscountType.STAFF)
        {
            int discount = tripCost / 2;
            tripCost = tripCost - discount;
        }
        else if(customerDiscount == DiscountType.FRIENDS_AND_FAMILY)
        {
            int discount = tripCost / 10;
            tripCost = tripCost - discount;
        }
        
        //Then check if the account has enough.
        if(accountBalance >= tripCost)
        {
            accountBalance = accountBalance - tripCost;
        }
        else//The account doesnt have the funds so throw an exception which will be caught in TollRoadMain try-catch.
        {
            throw new InsufficientAccountBalanceException();
        }
        return tripCost;
    }

    //CompareToMethod
    @Override
    public int compareTo(CustomerAccount o) 
    {
        return this.customerVehicle.getRegistration().compareTo(o.customerVehicle.getRegistration());
    }
    
    //Testing for customer account objects.
    public static void main(String[] args)
    {
        System.out.println("Test for CustomerAccount class.");
        Car test = new Car("AD55FF0", "Toyota", 2);
        CustomerAccount testAccount = new CustomerAccount("James", "Burrell", test, 5000);
        
        System.out.println(testAccount.getFirstName());
        System.out.println(testAccount.getLastName());
        System.out.println(testAccount.getCustomerVehicle());
        System.out.println(testAccount.getAccountBalance());
        System.out.println(testAccount.getCustomerDiscount());
        
        testAccount.activateStaffDiscount();
        System.out.println(testAccount.getCustomerDiscount());
        testAccount.activateFriendsAndFamilyDiscount();
        System.out.println(testAccount.getCustomerDiscount());
        testAccount.deactivateDiscount();
        System.out.println(testAccount.getCustomerDiscount());
        testAccount.addFunds(4000);
        System.out.println(testAccount.getAccountBalance());
        
        try 
        {
            System.out.println(testAccount.makeTrip());
        } catch (InsufficientAccountBalanceException ex) 
        {
            System.out.println("Insufficient funds.");
        }
        
    }
    
}
