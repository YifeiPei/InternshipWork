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
public class crew {
    //the crew of cc
    ArrayList<String> name;
    
    public crew(ArrayList<String> name){
        this.name=name;
    }
    
    public ArrayList<String> get_name(){
        return name;
    }
}
