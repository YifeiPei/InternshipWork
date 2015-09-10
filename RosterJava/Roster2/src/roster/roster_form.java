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
public class roster_form {
    //the datastructure of timetable
    String name;
    String position;
    
    public roster_form(String name, String position){
        this.name=name;
        this.position=position;
    }
    
    public String get_name(){
        return name;
    }
    
    public String get_position(){
        return position;
    }
}
