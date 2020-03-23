package app;

import java.util.ArrayList;

class Branch{
    /**
     * Branch class stores deliveries and branch informations.
     */
    private static int branchIdentification = 1; //Id to be given a new branch, also used for indexing.
    private int id;
    private String name;
    private ArrayList<Delivery> deliveries;
  
    Branch(String name){
      this.id = branchIdentification;
      this.deliveries = new ArrayList<Delivery>();
      this.name = name;
      
      branchIdentification++;
    }

    @Override
    public String toString() {
      return "Branch Id: " + this.id + " - " + "\tName: " + this.name;
    }
    public int getBranchIdentification(){
      return branchIdentification;
    }
    public void setBranchIdentification(int id){
        branchIdentification = id;
    } 
    public int getId(){ 
      return this.id; 
    }
    public void setId(int id){ 
      this.id = id; 
    }
    public String getName(){ 
      return this.name; 
    }
    public void setName(String name){ 
      this.name = name; 
    }
    public void setDeliveries(ArrayList<Delivery> deliveries){
      this.deliveries = deliveries;
    }
    public ArrayList<Delivery> getDeliveries(){
      return this.deliveries;
    }

    /**
     * findDelivery method is used to find index of a specific package(delivery) in the system.
     * @param trackingNumber  The tracking number of the package(delivery).
     * @return                int
     */
    public int findDeliveryIndex(String trackingNumber){
      for(int i=0; i<deliveries.size(); i++){
        if(this.deliveries.get(i) != null){
          if(this.deliveries.get(i).getTrackingNumber().compareTo(trackingNumber) == 0){
            //If delivery is found then return the index of the delivery.
            return i; 
          }
        }
      }
      return -1;
    }

    /**
     * findDelivery method is used to find a specific delivery in the system. 
     * @param trackingNumber  Tracking number of the delivery
     * @return                boolean
     */
    public Delivery findDelivery(String trackingNumber){
      for(int i=0; i<deliveries.size(); i++){
        if(this.deliveries.get(i) != null){
          if(this.deliveries.get(i).getTrackingNumber().compareTo(trackingNumber) == 0){
            //If delivery is found then return the index of the delivery.
            return this.deliveries.get(i); 
          }
        }
      }
      return null;
    }

    /**
     * listDeliveries method displays recorded deliveries in the system.
     */
    public void listDeliveries(){
      System.out.println("------------DELIVERY LIST------------");
      this.deliveries.forEach(dlry ->System.out.println(dlry.toString()));
      System.out.println("-------------------------------------");
    }
}