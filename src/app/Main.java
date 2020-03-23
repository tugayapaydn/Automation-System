package app;

class Main{
    public static void main(String[] args){
        /**
         * In order to start the testing process use this method:
            Main mainSystem = new Main();
            mainSystem.automationSystemTest();
         */

        Menu menu = new Menu();
        menu.mainMenu();
        //Main main = new Main();
        //main.automationSystemTest();
    }
    
    
    public void automationSystemTest(){
        /**This is our test environment
         * In this method all kinds of users are registered to the system
         * and their functionalities are tested.
         */
        AutomationSystem system = new AutomationSystem();
        Login login = new Login();

        System.out.println("By default, an administrator added to the system(Id: admin, password: admin)");
        login.getAdministrators().add(new Administrator("admin", "admin", "admin", "admin", system,login));
        login.listUsers();
        //------------------------------------------------------
        System.out.println("ADMINISTRATOR TEST");
        Administrator admin = login.getAdministrators().get(0);
        login.setLoggedInUser(admin);
        System.out.println("Logged in as admin");

        System.out.println("\n--2 new branches are being added to the system...");
        admin.addBranch("Kartal/Yakacik");
        admin.addBranch("Pendik/Kurtköy");
        
        System.out.println("\n--A new admin with existing id is being added to the system...");
        admin.addAdmin("admin-test", "admin-test", "admin", "admin-test");

        System.out.println("\n--New personnels are being added to the system...");
        admin.addBranchEmployee("employee", "employee", "employee", "employee", 1);
        admin.addTransportationPersonnel("personnel", "personnel", "personnel", "personnel", 1);
        login.listUsers();

        System.out.println("Testing removeBranch method...");
        System.out.println("Removing a branch will remove every Branch employee working in that branch!");
        System.out.println("Branch (id:1) has been removed");
        admin.removeBranch(1);
        system.listBranches();
        login.listUsers();

        System.out.println("--Removing non existing users with id '5'");
        admin.removeAdmin("5");
        admin.removeBranchEmployee("5");
        admin.removeTransportationPersonnel("5");

        System.out.println("\n--Removing logged in user \"admin\"");
        admin.removeAdmin("admin");
        login.listUsers();
        //------------------------------------------------------
        //------------------------------------------------------
        System.out.println("BRANCH EMPLOYEE TEST");
        
        System.out.println("\n--Adding a branch employee to a nonexisting work station(branch)");
        admin.addBranchEmployee("employee", "employee", "employee", "passwd", 1);
        
        System.out.println("\n--Adding a branch employee with id \"employee\" to the system for testing");
            admin.addBranchEmployee("employee", "employee", "employee", "passwd", 2);
            BranchEmployee employee = (BranchEmployee)login.searchUser(login.getBranchEmployees(), "employee");
        System.out.println("\n--Logged in as \"employee\"");
            login.setLoggedInUser(employee);

            system.listBranches();
            login.listUsers();

        System.out.println("\n--Testing addCustomer method...");
            employee.addCustomer("customer", "customer", "12345");

        System.out.println("\n--Testing addDelivery method...");
            employee.addDelivery("12345", "sender", "receiver");

        System.out.println("\n--Testing addCustomer method with added delivery...");
            employee.addCustomer("customer", "customer", "12345");

        System.out.println("\n--Testing setInformation method with added delivery(\"Delivery is ready\")...");
            employee.setInformation("12345", "Delivery is ready");
        
        System.out.println("\n--Testing removeInformation method with added delivery...");
            employee.removeInformation("12345");  
        
        System.out.println("\n--Testing removeInformation method with added delivery...");
            employee.setDeliveryStatus("12345", cargoStatus.ON_THE_WAY);  
        
        System.out.println("\n--Testing removeCustomer method...");
            employee.removeCustomer("12345");
            login.listUsers();
        //------------------------------------------------------
        //------------------------------------------------------    
        System.out.println("TRANSPORTATION PERSONNEL TEST");
        System.out.println("\n--Adding a transportation personnel with id \"personnel\" to the system for testing");
            admin.addTransportationPersonnel("personnel", "personnel", "personnel", "personnel", 2);
        TransportationPersonnel personnel = (TransportationPersonnel)login.searchUser(login.getTransportationPersonnels(), "personnel"); 
            login.setLoggedInUser(personnel);
        
        System.out.println("\n--Testing setDeliveryStatusToDelivered method...");
            personnel.setDeliveryStatusToDelivered("12345");
        System.out.println();
        //------------------------------------------------------
        //------------------------------------------------------ 
        System.out.println("CUSTOMER TEST");
        System.out.println("\n--Adding a customer with id \"customer\" to the system for testing");
            login.setLoggedInUser(employee);
            employee.addCustomer("customer", "customer", "12345");    
        Customer customer = (Customer)login.searchUser(login.getCustomers(), "12345");

        System.out.println("\n--Testing Customer methods with the delivery:");
            System.out.println(system.searchBranch(2).findDelivery("12345").toString());
        
        System.out.println("\n--Testing displayCurrentStatusOfDelivery method");
            customer.displayCurrentStatusOfDelivery();
        System.out.println("\n--Testing displayReceiverInfo method");
            customer.displayReceiverInfo();
        System.out.println("\n--Testing displaySenderInfo method");    
            customer.displaySenderInfo();
        //------------------------------------------------------
        //------------------------------------------------------ 
        
    }
}