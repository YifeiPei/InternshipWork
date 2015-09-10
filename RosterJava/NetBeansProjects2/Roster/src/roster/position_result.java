/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roster;

import java.util.ArrayList;

/**
 *
 * @author shahua
 */
public class position_result {
    String position;
    String location;
    String staff;
    
    public position_result(String position,String location,String staff){
        this.location=location;
        this.position=position;
        this.staff=staff;
        
    }

    position_result(ArrayList<String> s, String string, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
