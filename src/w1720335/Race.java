/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author anastasiiamazur
 */

// a class to race data type
public class Race {

    public static String[] race_locations = {"London", "Paris", "Madrid", "Berlin", "Kyiv", "Rome", "Warsaw", "Vinnitsya"};
    String date;
    String name;
    HashMap<Formula1Driver, Integer> positions;

    // converts Race to String which can be saved in a file.
    public String toFile(){
        String roleStr = "";
        ArrayList<Formula1Driver> drivers = new ArrayList<>(positions.keySet());

        for(Formula1Driver driver: drivers) {
            if(driver != null) {
                roleStr += this.name + "," + this.date + "," + driver.team + "," + positions.get(driver) + "\n";
            }
        }
        return roleStr;
    }

    // static function returning probability of winning in % based on starting position.
    public static int getRaceWinProb(int startingPosition){
        if(startingPosition == 1) return 40;
        if(startingPosition == 2) return 30;
        if(startingPosition == 3 || startingPosition == 4) return 10;
        if(startingPosition >=5 && startingPosition <= 9) return 2;
        return 0;
    }
    
    // static function to generate a random race.
    public static Race generateRandomRace(){
        Race race = new Race();
        race.name = Race.race_locations[Util.randomInt(0, Race.race_locations.length-1)] + Util.randomInt(2512, 10000);
        race.date = Util.randomInt(1, 25) + "/" + Util.randomInt(1, 12) + "/2021";
        race.positions = new HashMap<>();
        return race;
    }

    // static function that generate random positions for list of drivers
    public static HashMap<Formula1Driver, Integer> generateRandomRacePositions(ArrayList<Formula1Driver> drivers, int start){
        boolean generated;
        int randomPosition = 0;
        // existing positions
        ArrayList<Integer> positions = new ArrayList<>();
        HashMap<Formula1Driver, Integer> scores = new HashMap<>();

        for(Formula1Driver driver : drivers){
            if(driver!= null) {
                generated = false;
                while (!generated) {
                    randomPosition = Util.randomInt(1, drivers.size());
                    if (!positions.contains(randomPosition)) {
                        generated = true;
                        positions.add(randomPosition);
                        scores.put(driver, randomPosition);
                    }
                }
            }
        }
        
        return scores;
    }

    public static HashMap<Formula1Driver, Integer> generateRandomRacePositions(ArrayList<Formula1Driver> drivers){
        return Race.generateRandomRacePositions(drivers, 1);
    }

    // static function that allocates points to drivers based on position
    public static void allocatePoints(HashMap<Formula1Driver, Integer> drivers){
        ArrayList<Formula1Driver> driversList = new ArrayList<>(drivers.keySet());
        for(Formula1Driver driver: driversList){
            if(driver!=null) {
                driver.allocatePoints(drivers.get(driver));
            }
        }
    }

}
