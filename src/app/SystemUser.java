package app;

enum Authentication{ADMINISTRATOR, BRANCH_EMPLOYEE, TRANSPORTATION_PERSONNEL, CUSTOMER}

public abstract class SystemUser{
    
    private String name;
    private String surname;
    private String id;
    private String passwd;
    private Authentication role;
    
    /**
     * System user class defines all user informations of the system
     * Customer user id will be same with the package tracking number belongs to the customer.
     * @param name      Name of the user
     * @param surname   Surname of the user
     * @param id        Id of the user (or Tracking number of the customer)
     * @param passwd    Password of the user
     * @param role      Authentication(role) of the user
     */
    SystemUser(String name, String surname, String id, String passwd, Authentication role){
        this.name   = name; 
        this.surname= surname;
        this.id     = id;
        this.passwd = passwd;
        this.role   = role;
    }
    
    @Override
    public String toString() {
        return String.format("Id: %-10s Password: %-10s Name: %-10s Surname: %-10s Role: %-10s"
            , this.getId(), this.getPasswd(), this.getName(), this.getSurname(), this.getRole());
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getPasswd(){
        return this.passwd;
    }
    public void setPasswd(String passwd){
        this.passwd = passwd;
    }
    public Authentication getRole(){
        return this.role;
    }
    public void setRole(Authentication role){
        this.role = role;
    }
}