package rentalcar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

//enums for common, unchangable things.
enum Colors {Red,Orange,Yellow,Green,Blue,Indigo,Violet,White,Black,Gray};
enum FuelEconomy {VeryPoor,Poor,Average,Good,VeryGood,Excellent,ExcellentPlus};

public class RentalCar 
{
    //scanner, exit flags to carry the user's choice of exiting a menu
    static Scanner in = new Scanner (System.in);
    static boolean exitFlag = false;
    static boolean exitFlagMain = false;
    
    public static void main(String[] args) 
    {   
        //hardcoded objects in the absence of a database
        User.Users.add(new Admin("Muhammad Sumbul","0519826212",123));
        User.Users.add(new Renter("Khalid Kashmiri","0518120569",000));
        User.Users.add(new Renter("Khidir Karawita","0518565369",111));
        Car.Cars.add(new Sedan(FuelEconomy.Good, true, "Camry", "Toyota", "From the factory, straight through our doors", Colors.White, 200, LocalDate.of(2018, 6, 24)));
        Car.Cars.add(new SUV(7,5,FuelEconomy.Poor,"Yukon","GMC","Lightly used, Slight dent on driver's door",Colors.White,350,LocalDate.of(2009, 7, 12)));
        Car.Cars.add(new Pickup(6,820,FuelEconomy.VeryPoor,"TRX","Dodge","Able to move mountains, drives like a dream",Colors.Red,650,LocalDate.of(2022, 3, 01)));
        Car.Cars.add(new SuperCar(3,759,1,350,"Aventador","Lambourghini","Sparkling new, always a head-turner",Colors.Yellow,2000,LocalDate.of(2019, 12, 30)));
        Car.Cars.add(new Coupe(true,309,443,"911","Porsche","The PDK double transmission makes this cabriolet a joy to roam in",Colors.Black,1200,LocalDate.of(2022, 3, 9)));
        
        //infinite loop for smooth re-entry,unless the exit flag is set
        while (true)
        {
            mainMenu();
            if (exitFlagMain)             
                break;           
        }
    }
    
    public static void mainMenu()
    {
        System.out.println("""
                *************************************************************
                            Welcome to the Rental System
                           
                                Please enter:
                                1 for the admin menu
                                2 for the customer menu
                                0 to exit the program
                *************************************************************
                            """);
        
        switch (in.nextInt()) 
        {
            case 1:
                displayAdminMenu();
                break;
            case 2:
                displayCustomerMenu();
                break;
            case 0:
                exitFlagMain=true;
                break;
            default:
                System.out.println("Please enter a valid entry");
                break;
        }
    } 
    //self explanatory methods
    public static void displayAdminMenu()
    {
        System.out.println("""
                           ***********************************
                                 Please Sign in
                                 Enter your username
                                 Then Enter your password
                           ***********************************
                           """);
        if(adminLogin(in.nextInt(),in.nextInt()))
        {
            System.out.println("Welcome! "+User.Users.get(0).getName()+"!");
            while (true)
            {         
                System.out.println("""
                *************************************************************
                        Welcome! Please select an option from below
                                   
                                    1.Add a Car
                                    2.Remove a Car
                                    3.Change a Car's Information
                                    4.Display all cars
                                    0.Return to main menu
                *************************************************************
                """);
                switch (in.nextInt()) 
                {
                    case 1:
                        addCar();
                        break;
                    case 2:
                        removeCar();
                        break;
                    case 3:
                        editCar();
                        break;
                    case 4:
                        Car.displayAllCarsSimple();
                        break;
                    case 0:
                        exitFlag=true;
                        break;
                    default:
                        System.out.println("Please enter a valid entry");
                        continue;
                }
                if (exitFlag)
                    break;
            }
        }
        else
        {
            System.out.println("User not found");
        }
    }
    
    public static boolean adminLogin(int ID,int Password)
    {
        //iterates through every user until an admin is found
        //else login is denied 
        for (User User : User.Users) 
        {
            if (User.getID()==ID && User.getPassword()==Password && User instanceof Admin)
                return true;
        }
        return false;
    }
    
    public static void addCar()
    {
        //datafields that apply to all cars
        String Model,Brand,Comment;
        float Price,TrunkSize; 
        LocalDate ProductionYear;
        Colors Color;
        FuelEconomy Eco;
        int horsePower;
        
        System.out.println("Enter the car's Model (Aka name, like a Camry or a Prius):");
        Model=in.next();
        System.out.println("Enter the Car's Brand:");
        Brand=in.next();
        in.nextLine();
        System.out.println("Please enter a comment on the car's condition:");
        Comment=in.nextLine();
        System.out.println("Please enter the car's color from the following list:");
        for (Colors color:Colors.values())
            {
                System.out.println(color);
            }
        Color=Colors.valueOf(in.next());
        
        System.out.println("Enter the car's price:");
        Price=in.nextFloat();
        System.out.println("Enter the car's production date in the following format(YYYY-MM-DDD):");
        ProductionYear=LocalDate.parse(in.next());
        
        System.out.println("""
                           *************************************************************
                                        Select a type of car to add
                           
                                                1.Sedan
                                                2.Coupe
                                                3.SUV
                                                4.Pickup
                                                5.Supercar
                           *************************************************************
                           """);
        //now each specific subclass attribute is declared and initialized
        //locally to finally create the appropriate object
        switch (in.nextInt()) 
        {
            case 1:
                System.out.println("Enter the car's Fuel Economy f:");
                for(FuelEconomy FuelEconomy:FuelEconomy.values())
                {
                    System.out.println(FuelEconomy.toString());
                }
                Eco=FuelEconomy.valueOf(in.next());
                System.out.println("Enter 1 if the car is hatchback or 0 if it's not:");
                boolean hatchback;
                if (in.nextInt() == 1)
                    hatchback=true;
                else
                    hatchback=false;             
                
                Car.Cars.add(new Sedan(Eco, hatchback, Model, Brand, Comment, Color, Price, ProductionYear));
                System.out.println("Done!");
                break;
            case 2:
                System.out.println("Enter 1 if the car is convertable or 0 if it's not:");
                boolean convertable;
                if (in.nextInt() == 1)
                    convertable=true;
                else
                    convertable=false; 
                System.out.println("Enter the car's max speed:");
                float maxSpeed=in.nextFloat();
                System.out.println("Enter the car's horse power:");
                horsePower=in.nextInt();
                Car.Cars.add(new Coupe(convertable, maxSpeed, horsePower, Model, Brand, Comment, Color, Price, ProductionYear));
                System.out.println("Done!");
                break;
            case 3:
                System.out.println("Enter the car's Fuel Economy:");
                for(FuelEconomy FuelEconomy:FuelEconomy.values())
                {
                    System.out.println(FuelEconomy.toString());
                }
                Eco=FuelEconomy.valueOf(in.next());
                System.out.println("Enter the car's trunk size:");
                TrunkSize=in.nextFloat();
                System.out.println("Enter the car's SeatsCount size:");
                int SeatCount=in.nextInt();
                Car.Cars.add(new SUV(SeatCount, TrunkSize, Eco, Model, Brand, Comment, Color, Price, ProductionYear));
                System.out.println("Done!");
                break;
            case 4:
                System.out.println("Enter the car's Fuel Economy:");
                for(FuelEconomy FuelEconomy:FuelEconomy.values())
                {
                    System.out.println(FuelEconomy.toString());
                }
                Eco=FuelEconomy.valueOf(in.next());
                System.out.println("Enter the car's trunk size:");
                TrunkSize=in.nextFloat();
                System.out.println("Enter the car's torque:");
                float Torque=in.nextFloat();
                Car.Cars.add(new Pickup(TrunkSize, Torque, Eco, Model, Brand, Comment, Color, Price, ProductionYear));
                System.out.println("Done!");
                break;
            case 5:                
                System.out.println("Enter the car's acceleration:");
                float Acceleratin=in.nextFloat();
                System.out.println("Enter the car's horse power:");
                horsePower=in.nextInt();
                System.out.println("Enter the car's top speed:");
                float TopSpeed=in.nextFloat();
                System.out.println("Enter the car's lap time:");
                float LapTime=in.nextFloat();
                Car.Cars.add(new SuperCar(Acceleratin, horsePower, LapTime, TopSpeed, Model, Brand, Comment, Color, Price, ProductionYear));
                System.out.println("Done!");
                break;
            default:
                System.out.println("Wrong input");
        }
    }
    
    public static void removeCar()
    {
        Car.displayAllCarsSimple();
        
        System.out.println("Enter the car's ID to remove it:");
        int carID=in.nextInt();
        for (Car Car:Car.Cars)
        {
            if (Car.getID()==carID)
            {
                Car.Cars.remove(Car);
                System.out.println("Done!");
                return;
            }
            
        }            
        System.out.println("Car not found!");

    }
    
    public static void editCar()
    {
        Car.displayAllCarsSimple();
        System.out.println("Enter a Car's ID to edit it");
        int CarID=in.nextInt();
        System.out.println("""
                           Do you want to edit a specific car's information or general car's information?
                           1. General Info
                           2. Specific Info
                           """);
        int InfoSelect=in.nextInt();
        for (Car Car:Car.Cars)
        {
            //same as before, the menu below displays all general info
            //that applies to all cars to be edited
            if(InfoSelect==1)
            {
                if (Car.getID()==CarID)
                {
                    System.out.println(Car.simpleToString());
                    System.out.println("""
                                       Select Info to edit
                                       1.Model
                                       2.Brand
                                       3.Comment
                                       4.Color
                                       5.Price
                                       6.Production Year
                                       """);
                    switch(in.nextInt())
                    {
                        case 1:
                            System.out.println("Enter in the new model:");
                            Car.setModel(in.next());
                            System.out.println("Done!");
                            break;
                        case 2:
                            System.out.println("Enter in the new Brand:");
                            Car.setBrand(in.next());
                            System.out.println("Done!");
                            break;
                        case 3:
                            in.nextLine();
                            System.out.println("Enter in the new Comment:");
                            Car.setComment(in.nextLine());
                            System.out.println("Done!");
                            break;
                        case 4:
                            System.out.println("Enter in the new color from the list below:");
                            for(Colors Color:Colors.values())
                            {
                                System.out.println(Color.toString());
                            }
                            Car.setColor(Colors.valueOf(in.next()));
                            System.out.println("Done!");
                            break;
                        case 5:
                            System.out.println("Enter in the new Price:");
                            Car.setPrice(in.nextFloat());
                            System.out.println("Done!");
                            break;
                        case 6:
                            System.out.println("Enter the new Production year in the following format (YYYY-MM-DD):");
                            Car.setProductionYear(LocalDate.parse(in.next()));
                            System.out.println("Done!");
                            break;
                    }
                }
            }
            //now the next menu asks the admin for the specific attribute to 
            //change it through casting the car to its appropriate type
            if(InfoSelect==2)
            {
                if (Car.getID()==CarID)
                {
                System.out.println("""
                                   Select a specific piece of info to change
                                   1.Seat count (SUV)
                                   2.Trunksize(SUV) or (Pickup) 
                                   3.Fuel Economy (SUV) or (Pickup) or (Sedan) 
                                   4.Convertible status (Coupe) 
                                   5.Top speed (Coupe) or (Supercar) 
                                   6.Horsepower (Coupe) or (Supercar) 
                                   7.Torque (Pickup)
                                   8.Hatchback status (Sedan) 
                                   9.0-60 time (Supercar) 
                                   10.Laptime (Supercar) 
                                   """);
                
                
                    switch(in.nextInt())
                    {
                        case 1:
                            if(Car instanceof SUV)
                            {
                            System.out.println("Enter in the new Seat count:");
                            ((SUV)Car).setSeatCount(in.nextInt());
                            System.out.println("Done!");
                            }
                            else
                                System.out.println("Wrong Type!");
                            break;
                            
                        case 2:
                            System.out.println("Enter the new trunksize");
                            if (Car instanceof SUV)
                            {
                                ((SUV)Car).setTrunkSize(in.nextInt());
                            }
                            else if (Car instanceof Pickup)
                            {
                                ((Pickup)Car).setTrunkSize(in.nextInt());
                            }
                            else
                                System.out.println("Wrong Type!");
                            break;
                            
                        case 3:
                            if(Car instanceof SUV)
                            {
                                System.out.println("Enter in the new Fuel Economy rating from the list below:");
                                for(FuelEconomy eco:FuelEconomy.values())
                                {
                                    System.out.println(eco.toString());
                                }
                                ((SUV)Car).setEconomy(FuelEconomy.valueOf(in.next()));
                                System.out.println("Done!");
                            }
                            else if(Car instanceof Sedan)
                            {
                                System.out.println("Enter in the new Fuel Economy rating from the list below:");
                                for(FuelEconomy eco:FuelEconomy.values())
                                {
                                    System.out.println(eco.toString());
                                }
                                ((Sedan)Car).setEco(FuelEconomy.valueOf(in.next()));
                                System.out.println("Done!");
                            }
                            else if(Car instanceof Pickup)
                            {
                                System.out.println("Enter in the new Fuel Economy rating from the list below:");
                                for(FuelEconomy eco:FuelEconomy.values())
                                {
                                    System.out.println(eco.toString());
                                }
                                ((Pickup)Car).setEconomy(FuelEconomy.valueOf(in.next()));
                                System.out.println("Done!");
                            }
                            else
                                System.out.println("Wrong Type!");
                            break;
                            
                        case 4:
                            if (Car instanceof Coupe)
                            {
                                System.out.println("Enter in the new convertible status(1 for true, 0 for false):");
                                int isconvertible=in.nextInt();
                                if (isconvertible==1)
                                    ((Coupe)Car).setConvertible(true);
                                else if (isconvertible==0)
                                    ((Coupe)Car).setConvertible(false);
                            }
                            else
                                    System.out.println("Wrong Type!");
                            break;
                            
                        case 5:
                            if(Car instanceof Coupe)
                            {
                                System.out.println("Enter in the new max speed:");
                                ((Coupe)Car).setMaxSpeed(in.nextFloat());
                                System.out.println("Done!");
                            }
                            else if(Car instanceof SuperCar)
                            {
                                System.out.println("Enter in the new max speed:");
                                ((SuperCar)Car).setTopSpeed(in.nextFloat());
                                System.out.println("Done!");
                            }
                            else
                                System.out.println("Wrong Type!");
                            break;
                            
                        case 6:
                            if(Car instanceof Coupe)
                            {
                                System.out.println("Enter in the new horsepower:");
                                ((Coupe)Car).setHorsePower((int)in.nextInt());
                                System.out.println("Done!");
                            }
                            else if(Car instanceof SuperCar)
                            {
                                System.out.println("Enter in the new horsepower:");
                                ((SuperCar)Car).setHorsePower(in.nextInt());
                                System.out.println("Done!");
                            }
                            break;
                            
                        case 7:
                            if (Car instanceof Pickup)
                            {
                                System.out.println("Enter in the new Torque:");
                                ((Pickup)Car).setTorque(in.nextFloat());
                                System.out.println("Done!");
                            }
                            else
                                System.out.println("Wrong Type!");
                            break;
                            
                        case 8:
                            if(Car instanceof Sedan){
                                
                                System.out.println("Enter in the new Hatchback status(1 for true, 0 for false):");
                                int ishatchback=in.nextInt();
                                if (ishatchback==1)
                                    ((Sedan)Car).setHatchBack(true);
                                else if (ishatchback==0)
                                    ((Sedan)Car).setHatchBack(false);
                                System.out.println("Done!");
                            }
                            else
                                System.out.println("Wrong Type!");
                            break;
                            
                        case 9:
                            if(Car instanceof SuperCar)
                            {
                                System.out.println("Enter in the new 0-60 time:");
                                ((SuperCar)Car).setAcceleration(in.nextFloat());
                                System.out.println("Done!");
                            }
                            else
                                System.out.println("Wrong Type!");
                            break;
                            
                        case 10:
                            if(Car instanceof SuperCar)
                            {
                                System.out.println("Enter in the new Laptime:");
                                ((SuperCar)Car).setLapTime(in.nextInt());
                                System.out.println("Done!");
                            }
                            else
                                System.out.println("Wrong Type!");
                            break;
                    }
                }            
            }        
        }
    }
        
    public static void displayCustomerMenu()
    {
        exitFlag = false;
        
        while (true)
        {
           System.out.println("""
            **********************************************************************
            Are you a Returning customer? if so, login else, create a new account!

                                        1.Login
                                  2.Create new account
                                  0.Return to main menu
            **********************************************************************
                               """);
//           renter below should've been just a declaration
//           it won't be read until after a valid user logs in due to
//           our implementation, but it raises exceptions in line 586 or
//           so,(Renter might not have been initialized)
//           yet, the IDE can't understand that.
//           thanks, netbeans
            Renter renter= ((Renter)User.Users.get(1));

            switch (in.nextInt()) 
            {
                case 1:
                    System.out.println("Enter your ID and then your password");
                    int ID=in.nextInt();
                    if (customerLogin(ID,in.nextInt()))
                    {
                        System.out.println("Welcome! "+User.Users.get(ID).getName()+"!");
                        for(User user:User.Users)
                        {
                            if (user.getID()==ID)
                                renter=(Renter)user;
                        }
                        while (true)
                        {
                            System.out.println("""
                            *************************************************************
                                        Please select an option from below

                                                1.Rent a car
                                                2.Return a car
                                            0.Return to main menu
                            *************************************************************
                            """);
                            switch (in.nextInt()) 
                            {
                                case 1:
                                    System.out.println("Please enter a car's ID to select from the list below:");
                                    Car.displayAllCarsVerbose();
                                    int Selection=in.nextInt();
                                    for(Car Car:Car.Cars)
                                        {
                                            if(Car.getID()==Selection)
                                            {
                                                if(Car instanceof SUV)
                                                    makeRentTransaction(renter,(SUV)Car);
                                                if(Car instanceof Coupe)
                                                    makeRentTransaction(renter,(Coupe)Car);
                                                if(Car instanceof Sedan)
                                                    makeRentTransaction(renter,(Sedan)Car);
                                                if(Car instanceof Pickup)
                                                    makeRentTransaction(renter,(Pickup)Car);
                                                if(Car instanceof SuperCar)
                                                    makeRentTransaction(renter,(SuperCar)Car);
                                            }
                                        }
                                    break;
                                case 2:
                                    makeReturnTransaction(renter);
                                    break;
                                case 0:
                                    exitFlag=true;
                                    break;
                                default:
                                    System.out.println("Please enter a valid entry");
                                    continue;
                            }
                            if(exitFlag)
                            {
                                break;  
                            }                            
                        }
                    }
                    else
                    {
                        System.out.println("User not found");
                        break;
                    }
                    break;
                case 2:
                    customerRegister();
                    break;
                case 0:
                    exitFlag=true;
                    break;
                default:
                    System.out.println("Please enter a valid entry");
                    continue;
            } 
            if (exitFlag)        
                break;        
        }
        
    }
    
    public static boolean customerLogin(int ID,int Password)
    {
        for (User User : User.Users) 
        {
            if (User.getID()==ID && User.getPassword()==Password&&User instanceof Renter)
                return true;
        }
        return false;
    }
    
    public static void customerRegister()
    {
        System.out.println("Enter your password(it can only consist of numbers):");
        int password=in.nextInt();
        in.nextLine();
        System.out.println("Please enter your full name:");
        String Name=in.nextLine();
        System.out.println("Please enter your phone number:");
        String PhoneNumber=in.next();
        User.Users.add(new Renter(Name, PhoneNumber, password));
        System.out.println("your ID is: " + User.Users.get(User.Users.size()-1).getID());
        System.out.println("Now you can use your account to log in!");
    }
    
    public static boolean makeRentTransaction(Renter Renter,Car Car)
    {  
       while(true)
       {
            System.out.println("Please enter your preferred return date in the following format(YYYY/MM/DD): ");
            LocalDate DueDate= LocalDate.parse(in.next());
            if (DueDate.isBefore(LocalDate.now()))
            {
                System.out.println("Enter a valid date");
                continue;
            }
            else
            {
                Transaction.Transactions.add(new Transaction(LocalDate.now(),DueDate,Renter,Car));
                System.out.println("\n" + Transaction.Transactions.get(Transaction.Transactions.size()-1).toString());
                System.out.println("Done!");
                return true;
            }
       }
    }
    
    public static void makeReturnTransaction(Renter Renter)
    {
        boolean foundtransactionflag=true;
        for (Transaction Transaction:Transaction.Transactions)
        {
            if (Transaction.getRenter()==Renter)
            {
                System.out.println(Transaction.toString());
                System.out.println("""
                                   Would you like to close this transaction?
                                   1.Yes
                                   Any other number:No
                                   """);
                if(in.nextInt()==1)
                {
                    Transaction.setCar(null);
                    System.out.println("Done!");
                }
                foundtransactionflag=false;                
            }
        }
        
        if (foundtransactionflag)
            System.out.println("No record found.");
    }
}


abstract class Car 
{
    //information that applies to all cars
    private int ID;
    private String Model,Brand,Comment;
    private Colors Color;
    private float Price;
    private LocalDate ProductionYear;
    public static int CarCount;
    //arraylist which stores all cars after their creation 
    public static ArrayList<Car> Cars= new ArrayList<>();

    //empty constructor that forwards generic car info towards the full constructor
    public Car() {this("","","",null,0,LocalDate.EPOCH);}

    //full constructor that contains the ID initialization step
    public Car( String Model, String Brand, String Comment, Colors Color,float Price, LocalDate ProductionYear) 
    {
        ID=CarCount;
        this.Model = Model;
        this.Brand = Brand;
        this.Comment = Comment;
        this.Color = Color;
        this.Price = Price;
        this.ProductionYear = ProductionYear;
        CarCount++;
    }

    //standard setters and getters
    public int getID() {
        return ID;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public Colors getColor() {
        return Color;
    }

    public void setColor(Colors Color) {
        this.Color = Color;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public LocalDate getProductionYear() {
        return ProductionYear;
    }

    public void setProductionYear(LocalDate ProductionYear) {
        this.ProductionYear = ProductionYear;
    }

    public static int getCarCount() {
        return CarCount;
    }

    // displays all cars in a verbose manner to try to sell it
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID ").append(ID);
        sb.append(", It's a ").append(Model);
        sb.append(", Made by ").append(Brand);
        sb.append(", of the Color ").append(Color);
        sb.append(", and it is priced at ").append(Price);
        sb.append(" SAR, Made in ").append(ProductionYear.getYear());
        sb.append(",\n\t\tand it has the following comments written on it: ").append(Comment);       
        return sb.toString();        
    }   
    
    // for displayAllCarsSimple()
    public String simpleToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID ").append(ID);
        sb.append(" ").append(Color).append(" ").append(ProductionYear) ;
        sb.append(" ").append(Brand);
        sb.append(" ").append(Model);
        sb.append(". Costs ").append(Price).append(" SAR. With the following comments: ").append(Comment);
        return sb.toString();
    }
    //prints all cars simpley without other jargon
    public static void displayAllCarsSimple()
    {
        for (Car Car:Cars)
        {
            if (Car instanceof SUV)
                System.out.println(((SUV)Car).simpleToString());
            if (Car instanceof Coupe)
                System.out.println(((Coupe)Car).simpleToString());
            if (Car instanceof Pickup)
                System.out.println(((Pickup)Car).simpleToString());
            if (Car instanceof Sedan)
                System.out.println(((Sedan)Car).simpleToString());
            if (Car instanceof SuperCar)
                System.out.println(((SuperCar)Car).simpleToString());     
        }
    }
    //prints all available cars in a more presentable way, fit for the consumer
    public static void displayAllCarsVerbose()
    {
        for (Car Car:Cars)
        {
            boolean alreadyrentedflag=false;
            for(Transaction Transaction:Transaction.Transactions)
            {
                if(Transaction.getCar()==Car)
                    alreadyrentedflag=true;
            }
            if(alreadyrentedflag)
                continue;
            else
            {
                if (Car instanceof SUV)
                    System.out.println(((SUV)Car).toString()+"\n");
                if (Car instanceof Coupe)
                    System.out.println(((Coupe)Car).toString()+"\n");
                if (Car instanceof Pickup)
                    System.out.println(((Pickup)Car).toString()+"\n");
                if (Car instanceof Sedan)
                    System.out.println(((Sedan)Car).toString()+"\n");
                if (Car instanceof SuperCar)
                    System.out.println(((SuperCar)Car).toString()+"\n");
            }
        }
    }
}

    


class SUV extends Car
{
    //SUV specific datafields
    private int SeatCount;
    private float TrunkSize;
    private FuelEconomy Economy;

    //empty constructor that forwards empty info towards the other constructor
    //that also forwards information to the superclass car that applies the ID
    //to the object
    public SUV() {this(0, 0, null, "", "", "", null, 0, LocalDate.EPOCH); }

    public SUV(int SeatCount, float TrunkSize, FuelEconomy Economy, String Model, String Brand, String Comment, Colors Color, float Price, LocalDate ProductionYear) {
        super(Model, Brand, Comment, Color, Price, ProductionYear);
        this.SeatCount = SeatCount;
        this.TrunkSize = TrunkSize;
        this.Economy = Economy;
    }
    
    //setters and getters, standard stuff
    public int getSeatCount() {
        return SeatCount;
    }

    public void setSeatCount(int SeatCount) {
        this.SeatCount = SeatCount;
    }

    public float getTrunkSize() {
        return TrunkSize;
    }

    public void setTrunkSize(float TrunkSize) {
        this.TrunkSize = TrunkSize;
    }

    public FuelEconomy getEconomy() {
        return Economy;
    }

    public void setEconomy(FuelEconomy Economy) {
        this.Economy = Economy;
    }    

    //a verbose tostring method that calls the superclass's method to access
    //its datafields and add them to the string builder
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(". As an (SUV)");
        sb.append(", It has a Seat Count of ").append(SeatCount);        
        sb.append(", and a trunk size of ").append(TrunkSize);
        sb.append(". It has a fuel economy rating of ").append(Economy);
        sb.append('}');
        return sb.toString();
    }
    //simpler tostring for debugging and admin purposes
    @Override
    public String simpleToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.simpleToString());
        sb.append(" (SUV) Extra Info: Seatcount: ").append(SeatCount);
        sb.append(", Trunk size: ").append(TrunkSize).append(", Fuel Economy: ").append(Economy);
        return sb.toString();
    }
}

class Coupe extends Car
{
    //Coupe specific datafields
    //coupes can be convertibles or hardtops
    //they're mostly sports-oriented thus their maximum speed and horsepower
    //matter tremendously to the user
    private boolean Convertible;
    private float MaxSpeed;
    private int HorsePower;
    
    //constructor that forwards empty info to the other constructor
    //that calls the super one, standard so far
    public Coupe() {this(false,0,0,"","","",null,0,LocalDate.EPOCH);}

    public Coupe(boolean Convertible, float MaxSpeed, int HorsePower, String Model, String Brand, String Comment, Colors Color, float Price, LocalDate ProductionYear) {
        super(Model, Brand, Comment, Color, Price, ProductionYear);
        this.Convertible = Convertible;
        this.MaxSpeed = MaxSpeed;
        this.HorsePower = HorsePower;
    }
    
    //setters and getters
    public boolean isConvertible() {
        return Convertible;
    }

    public void setConvertible(boolean Convertible) {
        this.Convertible = Convertible;
    }

    public float getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(float MaxSpeed) {
        this.MaxSpeed = MaxSpeed;
    }

    public int getHorsePower() {
        return HorsePower;
    }

    public void setHorsePower(int HorsePower) {
        this.HorsePower = HorsePower;
    }

    //verbose tostring aimed to impress(?) the consumer
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(". As a sporty");
        if(Convertible)
            sb.append(" Convertible (Coupe)");
        else
            sb.append(" Hard-Top (Coupe)");
        sb.append(", it has a wheel horsepower of ").append(HorsePower);
        sb.append(", which allows it to hit a top speed of ").append(MaxSpeed).append("kilometers per hour");
        return sb.toString();
    }
    //simpler tostring aimed to show the important info without the other
    //verbose stuff
    @Override
    public String simpleToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.simpleToString());
        sb.append(" (Coupe) Extra Info: Convertible: ");
         if (Convertible)
             sb.append("Yes");
         else
             sb.append("No");
        sb.append(", Horsepower: ").append(HorsePower).append("HP, Max speed: ").append(MaxSpeed).append("Km/H}");
        return sb.toString();
    }
}

class Pickup extends Car
{
    //pickup specific datafields
    //pickups are used for heavy-duty stuff
    private float TrunkSize,Torque;
    private FuelEconomy Economy;

    public Pickup() {this(0,0,null, "", "", "", null, 0, LocalDate.EPOCH);}

    public Pickup(float TrunkSize, float Torque, FuelEconomy Economy, String Model, String Brand, String Comment, Colors Color, float Price, LocalDate ProductionYear) {
        super(Model, Brand, Comment, Color, Price, ProductionYear);
        this.TrunkSize = TrunkSize;
        this.Torque = Torque;
        this.Economy = Economy;
    }
    
    public float getTrunkSize() {
        return TrunkSize;
    }

    public void setTrunkSize(float TrunkSize) {
        this.TrunkSize = TrunkSize;
    }

    public float getTorque() {
        return Torque;
    }

    public void setTorque(float Torque) {
        this.Torque = Torque;
    }

    public FuelEconomy getEconomy() {
        return Economy;
    }

    public void setEconomy(FuelEconomy Economy) {
        this.Economy = Economy;
    }
    
    //verbose tostring that tries to make the car more presentable
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(". As a (Pickup)");
        sb.append(", it has a truck bed with a capacity of ").append(TrunkSize);
        sb.append(", Cubic Meters, and a torque rating of ").append(Torque).append(" Newton-meters");
        sb.append(". It has a fuel economy rating of ").append(Economy);
        sb.append('}');
        return sb.toString();
    }
    //simple tostring method that simplifies the previous method
    //mainely for debugging and simplifying admin functions
    @Override
    public String simpleToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.simpleToString());
        sb.append(" (Pickup) Extra Info: Torque: ").append(Torque);
        sb.append(", Truck bed size: ").append(TrunkSize).append(", Fuel Economy: ").append(Economy);
        return sb.toString();
    }
    
}

class Sedan extends Car 
{
    private FuelEconomy Eco;
    private boolean HatchBack;

    //Constructors.

    public Sedan() {this(null, false, "", "", "", null, 0, LocalDate.EPOCH);
    }

    public Sedan(FuelEconomy Eco, boolean HatchBack, String Model, String Brand, String Comment, Colors Color, float Price, LocalDate ProductionYear) {
        super(Model, Brand, Comment, Color, Price, ProductionYear);
        this.Eco = Eco;
        this.HatchBack = HatchBack;
    }
    
    
    //Accessors and Modifiers.
    public FuelEconomy getEco() {
        return Eco;
    }

    public void setEco(FuelEconomy Eco) {
        this.Eco = Eco;
    }

    public boolean isHatchBack() {
        return HatchBack;
    }

    public void setHatchBack(boolean HatchBack) {
        this.HatchBack = HatchBack;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(". As a ");
        if (HatchBack)
            sb.append("(Hatchback)");
        else
            sb.append("Normal, Everyday (Sedan)");
        sb.append(". It has a fuel economy rating of ").append(Eco);
        sb.append('}');
        return sb.toString();
    }
    @Override
    public String simpleToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.simpleToString());
        sb.append(" (Sedan) Extra Info: Hatchback: ");
         if (HatchBack)
             sb.append("Yes");
         else
             sb.append("No");
        sb.append(", Fuel Economy Rating: ").append(Eco).append("}");
        return sb.toString();
    }
   
}

class SuperCar extends Car 
{
    //Acceleration is measured as 0-100 km/h. 
    private float Acceleration;
    private int HorsePower;
    private float lapTime;
    private float TopSpeed;

    //Constructors.
    SuperCar() {this (0, 0, 0, 0, "", "", "", null, 0, LocalDate.EPOCH);};

    public SuperCar(float Acceleration, int HorsePower, float lapTime, float TopSpeed, String Model, String Brand, String Comment, Colors Color, float Price, LocalDate ProductionYear) {
        super(Model, Brand, Comment, Color, Price, ProductionYear);
        this.Acceleration = Acceleration;
        this.HorsePower = HorsePower;
        this.lapTime = lapTime;
        this.TopSpeed = TopSpeed;
    }

    //Accessors and Modifiers for SuperCar class.
    public float getAcceleration() {
        return Acceleration;
    }

    public void setAcceleration(float Acceleration) {
        this.Acceleration = Acceleration;
    }

    public int getHorsePower() {
        return HorsePower;
    }

    public void setHorsePower(int HorsePower) {
        this.HorsePower = HorsePower;
    }

    public float getLapTime() {
        return lapTime;
    }

    public void setLapTime(float lapTime) {
        this.lapTime = lapTime;
    }

    public float getTopSpeed() {
        return TopSpeed;
    }

    public void setTopSpeed(float TopSpeed) {
        this.TopSpeed = TopSpeed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(". As a (Supercar)");
        sb.append(", It boasts an impressive laptime of ").append(lapTime);
        sb.append(" at our test track, all thanks to its horsepower rating ").append(HorsePower);
        sb.append(" and its acceleration, which allows it to go from 0 to 60 in ").append(Acceleration);
        sb.append(" seconds.}");
        return sb.toString();
    }
    @Override
    public String simpleToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.simpleToString());
        sb.append(" (Supercar) Extra Info: Test Track Laptime: ").append(lapTime);
        sb.append(", Horsepower: ").append(HorsePower).append("HP, Acceleration: ");
        sb.append(Acceleration).append('}');
        return sb.toString();
    }
}

abstract class User
{
    private String Name;
    private String PhoneNumber;
    private int ID;
    private int password;
    public static int startID=0;
    public static ArrayList<User> Users = new ArrayList<>();

    public User() {this("","",0);}

    public User(String Name, String PhoneNumber,int password) {
        ID=startID;
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.password=password;
        startID++;
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("Name=").append(Name);
        sb.append(", PhoneNumber=").append(PhoneNumber);
        sb.append(", ID=").append(ID);
        sb.append('}');
        return sb.toString();
    }    
}

class Renter extends User
{
    
    public Renter() {super();}

    public Renter( String Name, String PhoneNumber,int password) {
        super(Name, PhoneNumber,password);
    }
}

class Admin extends User
{
    public Admin(){super();}

    public Admin(String Name, String PhoneNumber,int password) {
        super(Name, PhoneNumber,password);
    }    
}

class Transaction
{
    //integral transaction datamembers
    private int ID;
    private LocalDate Date,DueDate;
    private Renter Renter;
    private Car Car;
    public static int TransactionCount;
    public static ArrayList<Transaction> Transactions = new ArrayList<>();
    
    //standard empty constructor that forwards the default values to the transaction
    //constructor to initialize the ID
    public Transaction() {this(LocalDate.EPOCH, LocalDate.EPOCH, null, null);}

    //full constructor with automatic ID assignment
    public Transaction(LocalDate Date, LocalDate DueDate, Renter Renter, Car Car) {
        ID =TransactionCount;
        this.Date = Date;
        this.DueDate = DueDate;
        this.Renter = Renter;
        this.Car = Car;
        TransactionCount++;
    }

    //standard setters and getters
    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate DueDate) {
        this.DueDate = DueDate;
    }

    public Renter getRenter() {
        return Renter;
    }

    public void setRenter(Renter Renter) {
        this.Renter = Renter;
    }

    public Car getCar() {
        return Car;
    }

    public void setCar(Car Car) {
        this.Car = Car;
    }

    public static int getTransactionCount() {
        return TransactionCount;
    }

    //standard tostring,the transaction doesn't need a fancy method
    //to get its point across.
    @Override
    public String toString() {
        return "Transaction{" + "ID=" + ID + ", Date=" + Date + ", DueDate=" + DueDate + ", Renter=" + Renter + ",\n Car=" + Car.simpleToString() + "}\n";
    } 
}