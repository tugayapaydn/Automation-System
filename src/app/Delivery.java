package app;

enum cargoStatus{PREPARING, ON_THE_WAY, DELIVERED}

class Delivery{    
    /**
     * Delivery class stores information of deliveries.
     */
    private String trackingNumber;
    private String notes;
    private String senderNameSurname;
    private String receiverNameSurname;
    private cargoStatus status;

    /**
     * By default, delivery status will be "PREPARING".
     * The other fields will be set by the branch employees.
     * @param trackingNumber      Tracking number of the delivery(package).
     * @param senderNameSurname   Sender information of the delivery
     * @param receiverNameSurname Receiver information of the delivery
     */
    Delivery(String trackingNumber, String senderNameSurname, String receiverNameSurname){
      this.trackingNumber       = trackingNumber;
      this.notes                = null;
      this.senderNameSurname    = senderNameSurname;
      this.receiverNameSurname  = receiverNameSurname;
      this.status               = cargoStatus.PREPARING;
    }
  
    @Override
    public String toString() {
      return String.format("Tracking Number: %-10s Status: %-10s Info: %-10s Sender: %-10s Receiver: %-10s"
                          , trackingNumber, status, notes, senderNameSurname, receiverNameSurname);
    }
    
    public String getTrackingNumber(){ 
      return this.trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber){
      this.trackingNumber = trackingNumber;
    }
    public cargoStatus getStatus(){
      return this.status;
    }
    public void setStatus(cargoStatus status){
      this.status = status;
      System.out.println(this.toString());
    }
    public String getInfo(){
      return this.notes;
    }
    public void setInfo(String note){
      this.notes = note;
      System.out.println(this.toString());
    }
    public String getSenderNameSurname(){
      return this.senderNameSurname;
    }
    public String getReceiverNameSurname(){
      return this.receiverNameSurname;
    }
  }