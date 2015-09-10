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
public class avaible_position {
    ArrayList<String> location; 
    ArrayList<String> postion; 
    ArrayList<ArrayList<String>> av_staff;
    int day;
    
    public avaible_position(int day, ArrayList<String> location, ArrayList<String> postion, ArrayList<ArrayList<String>> av_staff){
        this.location=location;
        this.postion=postion;
        this.av_staff=av_staff;
        this.day=day;
    }
    
}
