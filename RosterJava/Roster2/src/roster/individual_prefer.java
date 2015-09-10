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
public class individual_prefer {
    ArrayList<String> location;
    ArrayList<String> position;
    ArrayList<Integer> day_off;
    
    public individual_prefer(ArrayList<String> location, ArrayList<String> position, ArrayList<Integer> day_off){
        this.location=location;
        this.position=position;
        this.day_off=day_off;
    }
    
    public ArrayList<String> get_location(){
        return location;
    }
    
    public ArrayList<String> get_position(){
        return position;
    }
    
    public ArrayList<Integer> get_day_off(){
        return day_off;
    }
}
