package app;

import java.util.ArrayList;

class Login{
    /**
     * Inside login class, users will be registered or logged into the system.
     * Registeration will be handled by administrators of the system.
     * All users will be stored in that class
     */
    private ArrayList<Administrator> admin;
    private ArrayList<BranchEmployee> branchEmployee;
    private ArrayList<TransportationPersonnel> transportation_personnel;
    private ArrayList<Customer> customer;
    private SystemUser loggedInUser;

    Login(){
        this.admin  = new ArrayList<Administrator>();
        this.branchEmployee = new ArrayList<BranchEmployee>();
        this.transportation_personnel = new ArrayList<TransportationPersonnel>();
        this.customer = new ArrayList<Customer>();

        this.loggedInUser = null; //No logged in user by default.
    }

    public ArrayList<Administrator> getAdministrators(){
        return this.admin;
    }
    public ArrayList<BranchEmployee> getBranchEmployees(){
        return this.branchEmployee;
    }
    public ArrayList<TransportationPersonnel> getTransportationPersonnels(){
        return this.transportation_personnel;
    }
    public ArrayList<Customer> getCustomers(){
        return this.customer;
    }
    public SystemUser getLoggedInUser(){
        return this.loggedInUser;
    }
    public void setLoggedInUser(SystemUser user){
        this.loggedInUser = user;
    }

    /**
     * userLogin method is used to login to the system.
     * If loginned id and password is matched to an account
     * user will be loggined in to system.
     * @return boolean
     */
    public boolean userLogin(String id, String passwd){
        SystemUser user = searchUserAll(id);
        
        if(user == null){
            System.out.println("User not found");
            return false;
        }
        else{
            if(user.getRole() == Authentication.CUSTOMER){
                System.out.println("Login Successful");
                setLoggedInUser(user);
                return true;    
            }
            else{
                if(user.getPasswd().compareTo(passwd) == 0){
                    System.out.println("Login Successful");
                    
                    setLoggedInUser(user);
                    return true;
                }
                else{
                    System.out.println("Wrong Password!");
                    return false;
                }
            }
        }
    }
    /**
     * searchUserIndex method is used to find index of an user in the specific array
     * according to users role.
     * @param id    Id of the user to be found in the "users" array.
     * @return      int
     */
    public int searchUserIndex(ArrayList<? extends SystemUser> users, String id){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i) != null){
                if(users.get(i).getId().compareTo(id) == 0){
                    return i;
                }
            }
        }
        return -1;
    }

    public SystemUser searchUser(ArrayList<? extends SystemUser> users, String id){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i) != null){
                if(users.get(i).getId().compareTo(id) == 0){
                    return users.get(i);
                }
            }
        }
        return null;
    }

    public SystemUser searchUserAll(String id){
        SystemUser admin = searchUser(this.admin, id);
        SystemUser employee = searchUser(this.branchEmployee, id);
        SystemUser personnel = searchUser(this.transportation_personnel, id);
        SystemUser customer = searchUser(this.customer, id);

        if(admin != null){
            return admin;
        }else if(employee != null){
            return employee;
        }else if(personnel != null){
            return personnel;
        }else if(customer != null){
            return customer;
        }else{
            return null;
        }
    }

    /**
     * listUsers method is used to list demanded users registered to the system.
     */
    public void listUsers(Authentication role){
        System.out.println("\n--------- REGISTERED " + role + " LIST " + "---------");
        switch(role){
            case ADMINISTRATOR:
                this.admin.forEach(admin -> System.out.println(admin.toString()));
               break;

            case BRANCH_EMPLOYEE:
                this.branchEmployee.forEach(brEmp -> System.out.println(brEmp.toString()));
                break;

            case TRANSPORTATION_PERSONNEL:
                this.transportation_personnel.forEach(personnel -> System.out.println(personnel.toString()));
                break;
            
                case CUSTOMER:
                this.customer.forEach(customer -> System.out.println(customer.toString()));
                break;
        }
        System.out.println("-------------------------------------------------\n");
    }

    public void listUsers(){
        System.out.println("\n--------- REGISTERED USER LIST ---------");
        this.admin.forEach(admin -> {
            if(admin != null){
                System.out.println(admin.toString());
            }
        });
        this.branchEmployee.forEach(brEmp -> 
        {
            if(brEmp != null){
                System.out.println(brEmp.toString());
            }
        });
        this.transportation_personnel.forEach(personnel -> 
        {
            if(personnel != null){
                System.out.println(personnel.toString());
            }
        });
        this.customer.forEach(customer -> {
            if(customer != null){
                System.out.println(customer.toString());
            }
        });
        System.out.println("-----------------------------------------\n");
    }
}