/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roster;
import java.util.*;
/**
 *
 * @author shahua
 */
public class individual_availble {
    //String name;
    ArrayList<String> location;
    //ArrayList<ArrayList<Integer>> days;
    ArrayList<ArrayList<String>> position;
    
    public individual_availble(ArrayList<String> location, ArrayList<ArrayList<String>>  position){
        //this.days=days;
        this.location=location;
        //this.name=name;
        this.position=position;
        //position.get(0).get(9);
    }
    
   // public String get_name(){
    //    return name;
   // }
    
    public ArrayList<String> get_location(){
        return location;
    }
    
    public ArrayList<ArrayList<String>>  get_position(){
        return position;
    }
    
   
    
    public void add_location(String location){
        this.location.add(location);
    }
    
    public void add_position(String position, int index){
        this.position.get(index).add(position);
    }
    
    
}
