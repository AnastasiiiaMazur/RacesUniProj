/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;
import java.io.IOException;

/**
 *
 * @author anastasiiamazur
 */

// an interface which has methods to create, delete, change drivers and also display whole table, one particular driver and adding races
public interface ChampionshipManager {
    public Formula1Driver createDriver() throws IOException;
    
    public void deleteDriver();
    
    public void changeDriver();
    
    public void displayTable();
   
    public void addRace();
    
    public void displayDriver();
}
