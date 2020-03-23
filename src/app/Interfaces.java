package app;

interface Admin{
    boolean addBranch(String name);
    boolean addAdmin(String name, String surname, String id, String passwd);
    boolean addBranchEmployee(String name, String surname, String id, String passwd, int branchId);
    boolean addTransportationPersonnel(String name, String surname, String id, String passwd, int branchId);
    boolean removeBranch(int id);
    boolean removeAdmin(String id);
    boolean removeBranchEmployee(String id);
    boolean removeTransportationPersonnel(String id);
}

interface Employee{
    boolean addDelivery(String trackingNumber, String senderNameSurname, String receiverNameSurname);
    boolean setInformation(String trackingNumber, String info);
    boolean removeInformation(String trackingNumber);
    boolean setDeliveryStatus(String trackingNumber, cargoStatus status);
    boolean addCustomer(String name, String surname, String trackingNumber);
    boolean removeCustomer(String id);
}

interface Personnel{
    boolean setDeliveryStatusToDelivered(String trackingNumber);
}

interface CustomerInt{
    void displayReceiverInfo();
    void displaySenderInfo();
    void displayCurrentStatusOfDelivery();
}