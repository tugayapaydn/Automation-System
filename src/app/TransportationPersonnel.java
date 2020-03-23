package app;

class TransportationPersonnel extends SystemUser implements Personnel{
    private Branch workStation;
        
    TransportationPersonnel(String name, String surname, String id, String passwd, Branch workStation){
        super(name, surname, id, passwd, Authentication.TRANSPORTATION_PERSONNEL);
        this.workStation = workStation;
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

    /**
     * setDeliveryStatusDelivered method is used to change package status to "DELIVERED".
     * @param trackingNumber    Tracking number of the delivery
     * @return                  boolean
     */
    public boolean setDeliveryStatusToDelivered(String trackingNumber){
        try{
            workStation.getDeliveries().get(workStation.findDeliveryIndex(trackingNumber)).setStatus(cargoStatus.DELIVERED);   
            System.out.println("Package status has set to delivered.");
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Delivery not found.");
            return false;
        }
    }
}