package app;

import java.util.Scanner;

/**
 * @author Tugay Apaydın 1801042081
 */

public class Menu{
    private Login login;
    AutomationSystem system;

    Menu(){
        login = new Login();
        system = new AutomationSystem();
        
        /**
         * By default, an administrator (with id:admin, passwd: admin) will be added to the system.
         * An administrator can only be added to the system by another administrator.
         */
        System.out.println("By default, an administrator added to the system(Id: admin, password: admin)");
        login.getAdministrators().add(new Administrator("admin", "admin", "admin", "admin", system,login));
    }

    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        String id;
        String passwd;
        char choice;

        do{
            System.out.println("1.Login");
            System.out.println("0.Exit");
            
            System.out.print("Option: ");
            choice = scanner.next().charAt(0);

            switch(choice){
                case '1':
                    login.listUsers();
                    System.out.print("Id: ");
                        id = scanner.next();
                    System.out.print("Passwd: ");
                        passwd = scanner.next();
                    
                    if(login.userLogin(id, passwd) == true){
                        switch(login.getLoggedInUser().getRole()){
                            case ADMINISTRATOR:
                                adminMenu();
                                break;
                            case BRANCH_EMPLOYEE:
                                branchEmployeeMenu();
                                break;
                            case TRANSPORTATION_PERSONNEL:
                                transportationPersonnelMenu();
                                break;
                            case CUSTOMER:
                                customerMenu();
                                break;
                        }  
                    }
                    break;
                case '0':
                    System.exit(0);
                    break;

                default:
                    System.out.println("No such option.");
                    break;
            }
            login.setLoggedInUser(null);
            }
            while(choice != '0');
    }

    public void adminMenu(){
        Scanner scanner = new Scanner(System.in);
        Administrator loggedInAdmin = (Administrator)login.getLoggedInUser();
        
        String name;
        String surname;
        String id;
        String passwd;
        int branchId;
        char choice;

        do{
            System.out.println("\n***Administator Panel***");
            System.out.println("1.Add Branch");
            System.out.println("2.Remove Branch");
            System.out.println("3.Add Administrator");
            System.out.println("4.Add Branch Employee");
            System.out.println("5.Add Transportation Personnel");
            System.out.println("6.Remove Administrator");
            System.out.println("7.Remove Branch Employee");
            System.out.println("8.Remove Transportation Personnel");
            System.out.println("0.Logout (Back to Main Menu)");
            
            System.out.print("Option: ");
            choice = scanner.next().charAt(0);
            
            switch(choice){
                case '1':
                    System.out.println("New Branch:");
                    System.out.print("Branch Name: ");
                        name = scanner.next();
                    if(loggedInAdmin.addBranch(name) == true){
                        system.listBranches();
                    }
                    break;
                
                case '2':
                    System.out.println("Remove Branch:");
                    system.listBranches();
                    System.out.print("Branch id: ");
                        branchId = scanner.nextInt();
                    
                    if(loggedInAdmin.removeBranch(branchId) == true){
                        system.listBranches();
                    }
                    break;
                
                case '3':
                    System.out.println("New Administrator:");
                    System.out.print("Name: ");
                        name = scanner.next();
                    System.out.print("Surname: ");
                        surname = scanner.next();
                    System.out.print("Id: ");
                        id = scanner.next();
                    System.out.print("Password: ");
                        passwd = scanner.next();

                    if(loggedInAdmin.addAdmin(name, surname, id, passwd) == true){
                        login.listUsers(Authentication.ADMINISTRATOR);
                    }
                    break;

                case '4':
                    system.listBranches();    
                    System.out.println("New Branch Employee:");
                    System.out.print("Id of the branch that employee will work: ");
                        branchId = scanner.nextInt();
                    System.out.print("Name: ");
                        name = scanner.next();
                    System.out.print("Surname: ");
                        surname = scanner.next();
                    System.out.print("Id: ");
                        id = scanner.next();
                    System.out.print("Password: ");
                        passwd = scanner.next();
                    
                    if(loggedInAdmin.addBranchEmployee(name, surname, id, passwd, branchId) == true){
                        login.listUsers(Authentication.BRANCH_EMPLOYEE);
                    }
                    break;

                case '5':
                    system.listBranches();
                    System.out.println("New Transportation Personnel:");
                    System.out.print("Id of the branch that employee will work: ");
                        branchId = scanner.nextInt();
                    System.out.print("Name: ");
                        name = scanner.next();
                    System.out.print("Surname: ");
                        surname = scanner.next();
                    System.out.print("Id: ");
                        id = scanner.next();
                    System.out.print("Password: ");
                        passwd = scanner.next();
                    
                    if(loggedInAdmin.addTransportationPersonnel(name, surname, id, passwd, branchId) == true){
                        login.listUsers(Authentication.TRANSPORTATION_PERSONNEL);
                    }
                    break;

                case '6':
                    login.listUsers(Authentication.ADMINISTRATOR);
                    System.out.println("Remove Administrator:");
                    System.out.print("Id: ");
                        id = scanner.next();
                    if(loggedInAdmin.removeAdmin(id) == true){
                        login.listUsers(Authentication.ADMINISTRATOR);
                    }
                    break;

                case '7':
                    login.listUsers(Authentication.BRANCH_EMPLOYEE);
                    System.out.println("Remove Branch Employee:");
                    System.out.print("Id: ");
                        id = scanner.next();
                    if(loggedInAdmin.removeBranchEmployee(id) == true){
                        login.listUsers(Authentication.BRANCH_EMPLOYEE);
                    }
                    break;

                case '8':
                    login.listUsers(Authentication.TRANSPORTATION_PERSONNEL);
                    System.out.println("Remove Transportation Personnel:");
                    System.out.print("Id: ");
                        id = scanner.next();
                    if(loggedInAdmin.removeTransportationPersonnel(id) == true){
                        login.listUsers(Authentication.TRANSPORTATION_PERSONNEL);
                    }
                    break;

                case '0':
                    //Exit from the menu
                    break;
                
                default:
                    System.out.println("No such option.");
                    break;
            }
        }while(choice != '0');
    }
    
    public void branchEmployeeMenu(){
        Scanner scanner = new Scanner(System.in);
        BranchEmployee loggedInEmployee = (BranchEmployee)login.getLoggedInUser();

        String name;
        String surname;
        String id;
        char choice;
        int choice2;
        
        do{
            System.out.println("\n***Branch Employee Panel***");
            System.out.println("1.Add Customer");
            System.out.println("2.Remove Customer");
            System.out.println("3.Add Delivery");
            System.out.println("4.Set Delivery Status");
            System.out.println("5.Add Delivery Information");
            System.out.println("6.Remove Delivery Information");
            System.out.println("0.Back to Main Menu");
            
            System.out.print("Option: ");
            choice = scanner.next().charAt(0);

            switch(choice){
                case '1':
                    System.out.println("New Customer:");
                    System.out.println("WARNING! YOU CANT ADD ANY NEW CUSTOMER UNLESS THERE IS A DELIVERY BOUND TO THE CUSTOMER TRACKING NUMBER");
                    System.out.println("CUSTOMER AND DELIVERY TRACKING NUMBERS WILL BE THE SAME");
                    System.out.println("CUSTOMERS WILL CONNECT TO THE SYSTEM BY USING DELIVERY TRACKING NUMBER");

                    System.out.print("Name: ");
                        name = scanner.next();
                    System.out.print("Surname: ");
                        surname = scanner.next();
                    System.out.print("TrackingNumber: ");
                        id = scanner.next();
        
                    if(loggedInEmployee.addCustomer(name, surname, id) == true){
                        login.listUsers(Authentication.CUSTOMER);
                    }
                    break;

                case '2':
                    System.out.print("Tracking Number: ");
                        id = scanner.next();
                    if(loggedInEmployee.removeCustomer(id) == true){
                        login.listUsers(Authentication.CUSTOMER);
                    }
                    break;

                case '3':
                    System.out.println("New Delivery:");
                    System.out.print("Tracking Number: ");
                        id = scanner.next();
                    System.out.print("Sender Name: ");
                        name = scanner.next();
                    System.out.print("Sender Surname: ");
                        name += " " + scanner.next();
                    System.out.print("Receiver Name: ");
                        surname = scanner.next();
                    System.out.print("Receiver Surname: ");
                        surname += " " + scanner.next();
                    
                    loggedInEmployee.addDelivery(id, name, surname);
                    break;

                case '4':
                    System.out.print("Tracking Number: ");
                    id = scanner.next();
                    
                    System.out.println("Select Status: ");
                    System.out.println("1.PREPARING");
                    System.out.println("2.ON_THE_WAY");
                    System.out.println("3.DELIVERED");
                    
                    System.out.print("Option: ");
                    choice2 = scanner.next().charAt(0);

                    switch(choice2){
                        case '1':
                            loggedInEmployee.setDeliveryStatus(id, cargoStatus.PREPARING);
                            break;

                        case '2':
                            loggedInEmployee.setDeliveryStatus(id, cargoStatus.ON_THE_WAY);
                            break;

                        case '3':
                            loggedInEmployee.setDeliveryStatus(id, cargoStatus.DELIVERED);
                            break;

                        default:
                            System.out.println("Undefined option!");
                            break;
                    }
                    break;

                case '5':
                    System.out.print("Tracking Number Of the Delivery: ");
                        id = scanner.next();
                        surname = scanner.nextLine();
                    System.out.print("Info: ");
                        name = scanner.nextLine();
                    loggedInEmployee.setInformation(id, name);
                    break;
                
                case '6':
                    System.out.print("Tracking Number Of the Delivery: ");
                    id = scanner.next();

                    loggedInEmployee.removeInformation(id);
                    break;

                case '0':
                    //Exit from the menu
                    break;

                default:
                    System.out.println("Unexpected Choice, Please type the right value.");
                    break;
            }
        }while(choice != '0');
    }

    public void transportationPersonnelMenu(){
        Scanner scanner = new Scanner(System.in);
        TransportationPersonnel loggedInPersonnel = (TransportationPersonnel)login.getLoggedInUser();
        
        String trackingNumber;
        char choice;

        do{
            System.out.println("\n***Transportation Personnel Panel***");
            System.out.println("1.Update Delivery Status");
            System.out.println("0.Back to Main Menu");
            
            System.out.print("Option: ");
            choice = scanner.next().charAt(0);

            switch(choice){
                case '1':
                    System.out.print("Delivery Tracking Number: ");
                    trackingNumber = scanner.next();

                    loggedInPersonnel.setDeliveryStatusToDelivered(trackingNumber);
                    break;
                
                case '0':
                    //Back to main menu
                    break;

                default:
                    System.out.println("No such option.");
                    break;
            }
        }while(choice != '0');
    }
    
    public void customerMenu(){
        Scanner scanner = new Scanner(System.in);
        Customer loggedInCustomer = (Customer)login.getLoggedInUser();
        char choice;

        do{
            System.out.println("\n***Customer Pannel***");
            System.out.println("1.Current Status of the Delivery");
            System.out.println("2.Receiver information of the Delivery");
            System.out.println("3.Sender information of the Delivery");
            System.out.println("0.Back to Main Menu");

            System.out.print("Option: ");
            choice = scanner.next().charAt(0);

            switch(choice){
                case '1':
                    loggedInCustomer.displayCurrentStatusOfDelivery();
                    break;

                case '2':
                    loggedInCustomer.displayReceiverInfo();
                    break;

                case '3':
                    loggedInCustomer.displaySenderInfo();
                    break;
                
                default:
                    System.out.println("Unexpected Choice, Please type the right value.");
                    break;
            }
        }while(choice != '0');
    }
}
