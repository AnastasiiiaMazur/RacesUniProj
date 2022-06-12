/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;

/**
 *
 * @author anastasiiamazur
 */

// a class for the Formula1Driver type
public class Formula1Driver extends Driver implements Comparable<Formula1Driver>{
    String raceDate;
    int first_pos_num;
    int second_pos_num;
    int third_pos_num;
    int current_points;
    int num_of_races;

    Formula1Driver() {
        this.first_pos_num = 0;
        this.second_pos_num = 0;
        this.third_pos_num = 0;
        this.current_points = 0;
        this.num_of_races = 0;
    }

    Formula1Driver(int first_pos_num, int second_pos_num, int third_pos_num, int current_points, int num_of_races) {
        this.first_pos_num = first_pos_num;
        this.second_pos_num = second_pos_num;
        this.third_pos_num = third_pos_num;
        this.current_points = current_points;
        this.num_of_races = num_of_races;
    }

    // this method allocates points to driver based on postion
    public void allocatePoints(int position){
        if(position == 1){
            this.first_pos_num++;
        }
        if(position == 2){
            this.second_pos_num++;
        }
        if(position == 3){
            this.third_pos_num++;
        }
        this.current_points += Driver.get_points(position);
    }

    // string representation of Driver
    public String toString() {
        String i = "Driver: " + this.name + 
                   ", team: " + this.team +
                   ", current points: " + this.current_points + 
                   ", 1st pos: " + this.first_pos_num + 
                   ", 2nd pos: " + this.second_pos_num + 
                   ", 3d pos: " + this.third_pos_num;
        return i;
    }
    
    // this method is used to save data to the file
    public String toFile(){
        String i = this.name + 
             "," + this.team +
             "," + this.current_points + 
             "," + this.first_pos_num + 
             "," + this.second_pos_num + 
             "," + this.third_pos_num + 
             "," + this.raceDate;
        return i;
    }

    // this method is used to sort drivers by the number of their points
    @Override
    public int compareTo(Formula1Driver driver) {
        return new Integer(driver.current_points).compareTo(new Integer(this.current_points));
    }
}

