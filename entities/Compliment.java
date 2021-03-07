/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Random;
import javax.ejb.Stateless;

/**
 *
 * @author hadiajalil
 */
@Stateless
public class Compliment {

    public String hellobean(String name) {
        System.out.println("demo.HelloHadia.bean" + name);
        return "You are the worst " + name;
    }
    
    public String getNickName(String name) {
        Random rn = new Random();
        return "name" + rn.nextInt(20) + 1;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
