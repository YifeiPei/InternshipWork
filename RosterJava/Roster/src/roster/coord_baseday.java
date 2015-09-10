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
public class coord_baseday {
    ArrayList<String> position;
    
    public coord_baseday(ArrayList<String> positions){
        this.position=positions;
    }
    
    public ArrayList<String> get(){
        return position;
    }
    
   public void addd(String str){
       this.position.add(str);
       //return position;
   }
        

}
