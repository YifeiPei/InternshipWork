/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roster;
import java.io.*;
import java.util.*;
/**
 *
 * @author shahua
 */
public class individual {
    //the information of each employee
    String name;
    String location;
    ArrayList<String> individual_perfer_position;
    ArrayList<String> individual_perfer_location;
    String[] workingday;//0 is off, 1 is base day, 2 is available day
    //individual_prefer prefer;
    individual_availble baseday;
    individual_availble nonbaseday;
    
    public individual(String name, String location,String[] Workingday, individual_availble baseday, individual_availble nonbaseday){
        this.name=name;
        this.location=location;
       // this.prefer=prefer;
        this.workingday=Workingday;
        this.baseday=baseday;
        this.nonbaseday=nonbaseday;
    }
    
    public String get_name(){
        return name;
    }
    
    public String get_location(){
        return location;
    }
    
    public ArrayList<String> get_individual_perfer_position(){
        return individual_perfer_position;
    }
    
    public ArrayList<String> get_individual_perfer_location(){
        return individual_perfer_location;
    }
    
    
    public String[] get_workingday(){
        return workingday;
    }
    
    
}
