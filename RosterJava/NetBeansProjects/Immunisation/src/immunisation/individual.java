/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunisation;
import java.util.*;
/**
 *
 * @author yifpei
 */
public class individual {
    
    String name;
    String category;
    Boolean isagency;
    Boolean isranger;
    String email;
    
    public individual () {
        this.name = "";
        this.category = "";
        this.isagency = false;
        this.isranger = false;
        email = "";
}
    public individual (String name, String category, Boolean isagency, Boolean isranger, String email) {
        this.name = name;
        this.category = category;
        this.isagency = isagency;
        this.isranger = isranger;
        this.email = email;
    }
    
    public String getName (){
        return this.name;
    }
    
    public String getCategory (){
        return this.category;
    }
    
    public String getEmail (){
        return this.email;
    }
    
    public Boolean getAgency (){
        return this.isagency;
    }
    public Boolean getRanger (){
        return this.isranger;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setCategory (String category) {
        this.category = category;
    }
    
    public void setEmail (String email) {
        this.email = email;
    }
    
    public void setAgency (Boolean isagency) {
        this.isagency = isagency;
    }
    
    public void setRanger (Boolean isranger) {
        this.isranger = isranger;
    }
    
    public void setAgency (String isagency) {
        if (isagency.equals("true")){
            this.isagency = true;
        } else if (isagency.equals("false")){
            this.isagency = false;
        } else {
            System.out.println("wrong value in Agency");
        }
    }
    
    public void setRanger (String isranger) {
        if (isranger.equals("true")){
            this.isranger = true;
        } else if (isranger.equals("false")){
            this.isranger = false;
        } else {
            System.out.println("wrong value in Ranger");
        }
    }
}
