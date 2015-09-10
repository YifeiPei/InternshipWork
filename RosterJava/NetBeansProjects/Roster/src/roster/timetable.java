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
public class timetable {
    //the position and people which are already filled
    ArrayList<roster_form> roster;
    
    public timetable(){
        //ArrayList<String>[] roster=new ArrayList<String>[];
    }
    
    public void add(String position, String name){
        //roster_form r= new roster_form(position, name);
        roster.add(new roster_form(position, name));
    }
    
    public ArrayList<roster_form> get_timetable(){
        return roster;
    }
}
