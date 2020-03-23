package app;

import java.util.ArrayList;

class BranchEmployee extends SystemUser implements Employee{
    
    private Login login;
    private Branch workStation;
    
    BranchEmployee(String name, String surname, String id, String passwd, Branch workStation, Login login){
        super(name, surname, id, passwd, Authentication.BRANCH_EMPLOYEE);
        this.workStation = workStation;
        this.login = login;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("Workstation: %-10s", this.getWorkStation().getName());
    }
    
    public Branch getWorkStation(){
        return this.workStation;
    }

    public void setWorkStation(Branch workStation){
        this.workStation = workStation;
    }

    public Login getLogin(){
        return this.login;
    }

    public void setLogin(Login login){
        this.login = login;
    }

    /**
     * addDelivery method is used to add a delivery to the branch.
     * @param   trackingNumber      Tracking number of the delivery
     * @param   senderNameSurname   Name of the delivery sender
     * @param   receiverNameSurname Surname of the delivery sender
     * @return                      boolean
     */
    public boolean addDelivery(String trackingNumber, String senderNameSurname, String receiverNameSurname){
        Delivery delivery = new Delivery(trackingNumber, senderNameSurname, receiverNameSurname);
        try{
            if(workStation.getDeliveries().add(delivery) == true){
                System.out.println("New delivery has been added to the system.");
                System.out.println(delivery.toString());
                return true;
            }else{
                return false;
            }
        }
        catch(Exception e){
            System.out.println("New delivery not added to the system.");
            return false;
        }
    }

    /**
     * setInformation method is used to add an information to a package(delivery).
     * @param trackingNumber    Tracking number of the delivery.
     * @param info              Information(Notes) of the delivery
     * @return                  boolean
     */
    public boolean setInformation(String trackingNumber, String info){
        try{
            workStation.getDeliveries().get(workStation.findDeliveryIndex(trackingNumber)).setInfo(info); 
            System.out.println("Information has been added to the delivery");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Delivery not found!");
            return false;
        }
    }

    /**
     * removeInformation method is used to remove an information from a package.
     * @param trackingNumber    Tracking number of the delivery
     * @return                  boolean
     */
    public boolean removeInformation(String trackingNumber){
        try{
            workStation.getDeliveries().get(workStation.findDeliveryIndex(trackingNumber)).setInfo(null); 
            System.out.println("Information has been removed from the delivery");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Delivery not found!");
            return false;
        }
    }

    /**
     * setDeliveryStatus is used to set new cargo status to a package
     * @param trackingNumber    Tracking number of the delivery
     * @param status            Delivery status to be set
     * @return                  boolean
     */
    public boolean setDeliveryStatus(String trackingNumber, cargoStatus status){
        try{
            workStation.getDeliveries().get(workStation.findDeliveryIndex(trackingNumber)).setStatus(status); 
            System.out.println("Delivery status has been set to the delivery");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Delivery not found!");
            return false;
        }
    }

    /**
     * addCustomer method is used to add customers to the system
     * @param name              Name of the customer
     * @param surname           Surname of the customer
     * @param trackingNumber    Id of the customer (Also tracking number of the delivery that will be assigned to the customer)
     * @return
     */
    public boolean addCustomer(String name, String surname, String trackingNumber){
        Delivery delivery = workStation.findDelivery(trackingNumber);
        
        if(delivery != null){ 
            if(login.getCustomers().add(new Customer(name, surname, trackingNumber, delivery)) == true){
                System.out.println("New customer has been added to the system.");
                return true;
            }else{
                System.out.println("New customer not added to the system");
                return false;
            }
        }else{
            System.out.println("Before adding a customer to the system, there should be a recorded delivery that will be belong to that customer.");
            System.out.println("Customer id must be same with delivery tracking number");
            return false;
        }
    }
    
    /**
     * removeCustomer method is used to remove customers from the system.
     * @param id    Id of the customer.
     * @return      boolean
     */
    public boolean removeCustomer(String id){
        ArrayList<Customer> customers = login.getCustomers();
        try{
            customers.remove(login.searchUserIndex(customers, id));
            System.out.println("The customer has removed from the system.");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("The customer not found in the system.");
            return false;
        }
    }
}