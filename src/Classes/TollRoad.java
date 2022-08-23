package Classes;

import java.util.ArrayList;

public class TollRoad 
{
    //Declare attributes.
    private ArrayList<CustomerAccount> accountArray = new ArrayList<>();
    private int moneyMade;
    
    //Object constructor.
    public TollRoad() 
    {       
        this.accountArray = new ArrayList<>();
        this.moneyMade = 0;
    }
    
    //Accessor methods.
    public ArrayList<CustomerAccount> getAccountArray() 
    {
        return accountArray;
    }

    public int getMoneyMade() 
    {
        return moneyMade;
    }
    
    //Other methods.
    public void addCustomer(CustomerAccount acc)
    {
        accountArray.add(acc);
    }
    
    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException
    {   
        //For loop through accountArray to find if there is a matching account.
        for (CustomerAccount account : accountArray) 
        {
            if(account.getCustomerVehicle().getRegistration().equals(regNum))
            {
                return account;
            }
        }
        //If the account is not found throw the relevant exception.
        throw new CustomerNotFoundException();
    }
    
    public void chargeCustomer(String registrationNumber) throws CustomerNotFoundException, InsufficientAccountBalanceException
    {
        CustomerAccount acc = findCustomer(registrationNumber);
        int tripMoney = acc.makeTrip();
        moneyMade = moneyMade + tripMoney;
    }
}
