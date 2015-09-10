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
public class pairing {
    //the position should be orgnised, which is an 2D arraylistfor[days][locations]
    int day;
    String location;
    ArrayList<String> avai_position;
    
    public pairing(int day, String location, ArrayList<String> avai_position){
        this.avai_position=avai_position;
        this.day=day;
        this.location=location;
    }
    
    public ArrayList<String> get_avai_position(){
        return avai_position;
    }
    
    public String get_location(){
        return location;
    }
    
    public int get_day(){
        return day;
    }
}
