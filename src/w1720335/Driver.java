/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;
import java.util.*;
/**
 *
 * @author anastasiiamazur
 */

// abstract class Driver that has attributes name and team, and aslo a method that counts how many point driver has, accordind to the position
abstract class Driver{
    String name;
    String team;

    public static int get_points(int position){
        int points[] = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        if (position >= 1 && position <= 10) {
            return points[position - 1];
        }
        else {
            return 0;
        }
    }
  
    
}
