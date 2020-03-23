package app;

class Customer extends SystemUser implements CustomerInt{
    /**
     * Customers will display sender and receiver information
     * and current status of the delivery(package).
     */
    Delivery delivery;
    //Customer id is the tracking number of the delivery
    Customer(String name, String surname, String id, Delivery delivery){
        super(name, surname, id, null, Authentication.CUSTOMER);
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return String.format("Id: %-10s Name: %-10s Surname: %-10s Role: %-10s"
            , this.getId(), this.getName(), this.getSurname(), this.getName(), this.getRole());
    }

    public Delivery getDelivery(){
        return this.delivery;
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
    }

    /**
     * Displays receiver information of the delivery
     */
    public void displayReceiverInfo(){
        System.out.println(this.delivery.getReceiverNameSurname());
    }

    /**
     * Displays sender information of the delivery
     */
    public void displaySenderInfo(){
        System.out.println(this.delivery.getSenderNameSurname());
    }

    /**
     * Displays current status of the delivery
     */
    public void displayCurrentStatusOfDelivery(){
        System.out.println(this.delivery.getStatus());
    }
}