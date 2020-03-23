package app;

import java.util.ArrayList;

public class AutomationSystem{
    /** 
    * AutomationSystem class stores branches and methods related to those branches.
    */
    private ArrayList<Branch> branches;
    
    AutomationSystem(){
      this.branches = new ArrayList<Branch>(); 
    }
    public ArrayList<Branch> getBranches(){
      return this.branches;
    }
    public void setBranches(ArrayList<Branch> branches){
      this.branches = branches;
    }

    /**
     * searchBranch method is used to find the index of a specific branch in the system.
     * @param branchId  The id of the branch to be found
     * @return          int
     */
    public Branch searchBranch(int branchId){
      for(int i = 0; i < this.branches.size(); i++){
        if(this.branches.get(i) != null){
          if(this.branches.get(i).getId() == branchId){
            return this.branches.get(i);
          }
        }
      }
      return null;
    }

    /**
     * searchBranchIndex method is used to find a specific branch in the system.
     * @param branchId  The id of the branch
     * @return          int
     */
    public int searchBranchIndex(int branchId){ 
      for(int i = 0; i < this.branches.size(); i++){
        if(this.branches.get(i) != null){
          if(this.branches.get(i).getId() == branchId){
            return i;
          }
        }
      }
      return -1;
    }

    /**
     * listBranches method is used to list
     * all recorded branches with informations
     */
    public void listBranches(){
      System.out.println("------------ BRANCH LIST -------------");
      this.branches.forEach(br -> System.out.println(br.toString()));
      System.out.println("--------------------------------------");
    }
}