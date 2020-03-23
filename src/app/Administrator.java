package app;

import java.util.ArrayList;

class Administrator extends SystemUser implements Admin{
    /**
     * Administrator will handle adding and removing branches, branch employees and trasportation personnel.
     */
    private AutomationSystem system;
    private Login login;
    
    Administrator(String name, String surname, String id, String passwd, AutomationSystem system, Login login){
        super(name, surname, id, passwd, Authentication.ADMINISTRATOR);
        this.system = system;
        this.login = login;
    }

    public AutomationSystem getSystem(){
        return this.system;
    }
    
    public void setSystem(AutomationSystem system){
        this.system = system;
    }

    /**
     * addBranch method is used to add a new branch to the system.
     * @param name  Name of the branch.
     * @return      boolean
     */
    public boolean addBranch(String name){
        /*Branch id is given by the class itself, no need to check
         *if there is existing branch with this id.
         */
        if(system.getBranches().add(new Branch(name)) == true){
            System.out.println("A new branch has added to the system.");
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * addAdmin method is used to add a new admin to the system.
     * @param name      Name of the admin.
     * @param surname   Surname of the admin.
     * @param id        Id of the admin.
     * @param passwd    Password of the admin.
     * @return          boolean
     */
    public boolean addAdmin(String name, String surname, String id, String passwd){
        if(login.searchUserAll(id) != null){
            System.out.println("This id is already in use!");
            return false;
        }else{
            if(login.getAdministrators().add(new Administrator(name, surname, id, passwd, system, login)) == true){
                System.out.println("New admin has been added to the system.");
                return true;
            }else{
                System.out.println("New admin not added to the system");
                return false;
            }
        }
    }

    /**
     * addBranchEmployee method is used to add a new branch employee to the system.
     * @param name      Name of the branch employee.
     * @param surname   Surname of the branch employee.
     * @param id        Id of the branch employee.
     * @param passwd    Password of the branch employee.
     * @param branchId  Id of the branch that employee will work.
     * @return          boolean
     */
    public boolean addBranchEmployee(String name, String surname, String id, String passwd, int branchId){
        if(login.searchUserAll(id) != null){
            System.out.println("This id is already in use!");
            return false;
        }else{
            Branch workStation = system.searchBranch(branchId);
            
            if(workStation != null){
                if(login.getBranchEmployees().add(new BranchEmployee(name, surname, id, passwd, workStation, login)) == true){
                    System.out.println("New branch employee has been added to the system.");
                    return true;
                }else{
                    System.out.println("New branch employee not added to the system");
                    return false;
                }
            }else{
                System.out.println("Branch not found with this id");
                return false;
            }
        }
    }

    /**
     * addTransportationPersonnel method is used to add a new transportation personnel to the system.
     * @param name      Name of the transportation personnel.
     * @param surname   Surname of the transportation personnel.
     * @param id        Id of the transportation personnel.
     * @param passwd    Password of the transportation personnel.
     * @param branchId  Id of the branch that employee will work.
     * @return          boolean
     */
    public boolean addTransportationPersonnel(String name, String surname, String id, String passwd, int branchId){
        if(login.searchUser(login.getTransportationPersonnels(), id) != null){
            System.out.println("This id is already in use!");
            return false;
        }else{
            Branch workStation = system.searchBranch(branchId);

            if(workStation != null){
                if(login.getTransportationPersonnels().add(new TransportationPersonnel(name, surname, id, passwd, workStation)) == true){
                    System.out.println("New transportation personnel has been added to the system.");    
                    return true;
                }else{
                    System.out.println("New transportation personnel not added to the system");
                    return false;
                }
            }else{
                System.out.println("Workstation not found with this id");
                return false;
            }
        }
    }

    /**
     * removeBranch method is used to remove a branch from the system.
     * @param id  Id of the branch that will be removed from the system.
     * @return          boolean
     */
    public boolean removeBranch(int id){
        Branch branch = system.searchBranch(id);
        if(branch == null){
            System.out.println("Branch not found!");
            return false;
        }
        
        ArrayList<BranchEmployee> employees = login.getBranchEmployees();
        ArrayList<TransportationPersonnel> personnel = login.getTransportationPersonnels();
        try{
            for(int i = 0; i < employees.size(); i++){
                if(employees.get(i) != null && employees.get(i).getWorkStation() != null){
                    if(employees.get(i).getWorkStation().getId() == id){
                        employees.remove(i);
                    }
                }
            }
            
            for(int i = 0; i < personnel.size(); i++){
                if(personnel.get(i) != null && personnel.get(i).getWorkStation() != null){
                    if(personnel.get(i).getWorkStation().getId() == id){
                        personnel.remove(i);
                    }
                }
            }

            system.getBranches().remove(branch);
            System.out.println("The Branch and all branch employees registered to that branch have been removed.");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error! Branch not found.");
            return false;
        }
    }

    /**
     * removeAdmin method is used to remove an administrator from the system.
     * @param id    Id of the admin that will be removed from the system.
     * @return      boolean
     */
    public boolean removeAdmin(String id){
        try{
            if(login.getLoggedInUser().getId().compareTo(id) == 0){
                System.out.println("You cannot remove yourself from the system!");
                return false;
            }

            login.getAdministrators().remove(login.searchUserIndex(login.getAdministrators(), id));
            System.out.println("The Admin has been removed.");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error! Admin not found.");
            return false;
        }
        catch(Exception e){
            System.out.println("Something went wrong!");
            return false;
        }
    }

    /**
     * removeBranchEmployee method is used to remove an employee from the system.
     * @param id    Id of the employee that will be removed from the system.
     * @return      boolean
     */
    public boolean removeBranchEmployee(String id){
        try{
            login.getBranchEmployees().remove(login.searchUserIndex(login.getBranchEmployees(), id));
            System.out.println("The Branch employee has been removed.");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error! Branch employee not found.");
            return false;
        }
    }
    
    /**
     * removeTransportationPersonnel method is used to remove a personnel from the system.
     * @param id    Id of the personnel that will be removed from the system.
     * @return      boolean
     */
    public boolean removeTransportationPersonnel(String id){
        try{
            login.getTransportationPersonnels().remove(login.searchUserIndex(login.getTransportationPersonnels(), id));
            System.out.println("The Transportation personnel has been removed.");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error! Transportation personnel not found.");
            return false;
        }
    }
}