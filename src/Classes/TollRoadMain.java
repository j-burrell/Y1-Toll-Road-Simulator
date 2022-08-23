package Classes;

import java.io.*;
import java.util.*;

public class TollRoadMain 
{
    public static void main(String[] args)
    {
        TollRoadMain tollroadmain = new TollRoadMain();
        TollRoad tollroad = tollroadmain.initialiseTollRoadFromFile();
        tollroadmain.simulateFromFile(tollroad);
        
        System.out.println("");
        System.out.println("The total money made for the simulation is: " + tollroad.getMoneyMade());
    }
    
    public TollRoad initialiseTollRoadFromFile()
    {
        //Create new tollroad object.
        TollRoad tollroad = new TollRoad();
        
        try
        {
            //Declare file to be read in from and make a scanner.
            File dataFile = new File("customerData.txt");
            Scanner sc = new Scanner(dataFile).useDelimiter("#");
            
            while(sc.hasNext())
            {
                //Gets each set of account info and splits it at each comma, storing it in accountInfoParts.
                String accountInfo = sc.next();
                String[] accountInfoParts = accountInfo.split(",");
                
                //Assigns each piece of account info to a variable with a relavent name.
                String vehicleType = accountInfoParts[0];
                String regNumber = accountInfoParts[1];
                String firstName = accountInfoParts[2];
                String lastName = accountInfoParts[3];
                String vehicleMake = accountInfoParts[4];
                
                //Converts these 2 into integers as scanner reads them in as a String.
                int vehicleDetail = Integer.parseInt(accountInfoParts[5]);
                int startingBalance = Integer.parseInt(accountInfoParts[6]);
                                
                String discountType = accountInfoParts[7];
                
                //Declare new vehicle outside of switch.
                Vehicle customerVehicle;
                
                if(null == vehicleType)
                {
                    customerVehicle = new Truck(regNumber, vehicleMake, vehicleDetail);
                }
                else switch (vehicleType) {
                    case "Car":
                        customerVehicle = new Car(regNumber, vehicleMake, vehicleDetail);
                        break;
                    case "Van":
                        customerVehicle = new Van(regNumber, vehicleMake, vehicleDetail);
                        break;
                    default:
                        customerVehicle = new Truck(regNumber, vehicleMake, vehicleDetail);
                        break;
                }
                               
                //Create the customer account.
                CustomerAccount account = new CustomerAccount(firstName, lastName, customerVehicle, startingBalance);
                
                //Activate the correct discount for each account.
                if("STAFF".equals(discountType))
                {
                    account.activateStaffDiscount();
                }
                else if("FRIENDS_AND_FAMILY".equals(discountType))
                {
                    account.activateFriendsAndFamilyDiscount();
                }

                tollroad.addCustomer(account);
            }
        } 
        catch (FileNotFoundException ex) //If file is missing.
        {
            System.out.println("Cannot create the toll road, customer data file not found!");
        }
        return tollroad;      
    }

    public void simulateFromFile(TollRoad tollroad) 
    {
        try
        {
            //Declare file to be read from and create a scanner to do so. Seperate scans by each $.
            File transactionsFile = new File("transactions.txt");
            Scanner sc = new Scanner(transactionsFile).useDelimiter("[$]");
            
            while(sc.hasNext())
            {
                
                //Get next set of transaction details and split them into it's constituent parts.
                String transactionData = sc.next();
                String[] transactionDataParts = transactionData.split(",");
                
                //Store parts in relevant variables.
                String method = transactionDataParts[0];
                String registration = transactionDataParts[1];
                
                //Check which method it needs to call and then call the correct one.
                if("addFunds".equals(method))
                {
                    try
                    {
                        int amount = Integer.parseInt(transactionDataParts[2]);
                        tollroad.findCustomer(registration).addFunds(amount);  
                        System.out.println(registration + ": " + amount + " added successfully");
                    }
                    catch(CustomerNotFoundException e)
                    {
                        System.out.println(registration + ": addFunds failed. CustomerAccount does not exist");
                    }
                }
                else if("makeTrip".equals(method))
                {
                    try
                    {
                        tollroad.chargeCustomer(registration);
                        System.out.println(registration + ": Trip completed successfully");
                    }
                    catch(CustomerNotFoundException e)
                    {
                        System.out.println(registration + ": makeTrip failed. CustomerAccount does not exist");
                    } 
                    catch (InsufficientAccountBalanceException e) 
                    {
                        System.out.println(registration + ": makeTrip failed. Insufficient funds");
                    }
                }      
            }

        }
        catch(FileNotFoundException e) //If the transaction file is missing.
        {
            System.out.println("Cannot complete transactions, transactions file not found.");
        }
    }
}

