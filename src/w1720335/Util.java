/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author anastasiiamazur
 */

// a class to hold some utility functions
public class Util {

    public static int randomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    public static Formula1Driver getRandomWinner(HashMap<Formula1Driver, Integer> startPos){
        DriverWinNumbers winRange;
        int driverWinProbability;
        ArrayList<DriverWinNumbers> winRanges = new ArrayList<>();
        ArrayList<Formula1Driver> drivers = new ArrayList<>(startPos.keySet());
        int maxRange = 0;

        // generate win ratios for drivers
        for(Formula1Driver driver: drivers){
            driverWinProbability = Race.getRaceWinProb(startPos.get(driver));
            if(driverWinProbability != 0) {
                winRange = new DriverWinNumbers();
                winRange.driver = driver;
                winRange.min = maxRange + 1;
                maxRange += driverWinProbability;
                winRange.max = maxRange;
                winRanges.add(winRange);
            }
        }
        // generate the winning number
        int winnerNum = Util.randomInt(1, maxRange);

        // find Winner
        for(DriverWinNumbers driverWinRange: winRanges){
            if(driverWinRange.min >= winnerNum && driverWinRange.max >= winnerNum){
                return driverWinRange.driver;
            }
        }
        return null;
    }
}

class DriverWinNumbers{
    int min;
    int max;
    Formula1Driver driver;
}
