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
public class crew_avai {
    //the crew of avai time without base day or casual
      ArrayList<String> name;
    
    public crew_avai(ArrayList<String> name){
        this.name=name;
    }
    
    public ArrayList<String> get_name(){
        return name;
    }
}
