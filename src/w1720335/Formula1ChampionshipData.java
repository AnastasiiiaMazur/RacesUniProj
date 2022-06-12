/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author anastasiiamazur
 */

// the class Formula1ChampionshipData is for working with file (upload and receive data from it)
public class Formula1ChampionshipData {

    ArrayList<Formula1Driver> drivers;
    ArrayList<Race> races;

    Formula1ChampionshipData() throws IOException {
        this.loadDrivers();
        this.loadRaces();
    }

    public void loadRaces() throws IOException{
        this.races = new ArrayList<>();
        HashMap<String, Race> races = new HashMap<>();
        ArrayList<String> racesNames = new ArrayList<>();
        HashMap<Formula1Driver, Integer> position;
        Race race;
        Formula1Driver driver;

        File racesFile = new File("races.txt");
        if(!racesFile.isFile()){
            racesFile.createNewFile();
        }
        try (Scanner myReader = new Scanner(racesFile)) {
            while (myReader.hasNextLine()) {
                String info = myReader.nextLine();
                if(info != ""){
                    String[] parts = info.split(",");
                    if(racesNames.contains(parts[0])){
                        race = this.getRace(parts[0]);
                    }
                    else{
                        race = new Race();
                        race.name = parts[0];
                        race.date = parts[1];
                        racesNames.add(parts[0]);
                        race.positions = new HashMap<>();
                        this.races.add(race);
                    }
                    driver = this.getDriver(parts[2]);
                    race.positions.put(driver, Integer.parseInt(parts[3]));
                }
            }
        }
    }

    public void loadDrivers() throws IOException {
        drivers = new ArrayList<>();
        File driversFile = new File("drivers.txt");
        if(!driversFile.isFile()){
            driversFile.createNewFile();
        }
        try (Scanner myReader = new Scanner(driversFile)) {
            while (myReader.hasNextLine()) {
                String info = myReader.nextLine();
                if(info != ""){
                    String[] parts = info.split(",");
                    String names = parts[0];
                    String teams = parts[1];
                    String currentPoint = parts[2];
                    String first_pos = parts[3];
                    String second_pos = parts[4];
                    String third_pos = parts[5];
                    String raceDate = parts[6];
                    int currentPoints = Integer.parseInt(currentPoint);
                    int firstPos = Integer.parseInt(first_pos);
                    int secondPos = Integer.parseInt(second_pos);
                    int thirdPos = Integer.parseInt(third_pos);
                    Formula1Driver driver = new Formula1Driver();
                    driver.name = names;
                    driver.team = teams;
                    driver.current_points = currentPoints;
                    driver.first_pos_num = firstPos;
                    driver.second_pos_num = secondPos;
                    driver.third_pos_num = thirdPos;
                    driver.raceDate = raceDate;
                    this.drivers.add(driver);
                }
            }
        }
    }

    public int numberOfDrivers(){
        return this.drivers.size();
    }

    public void addDriver(Formula1Driver driver){
        this.drivers.add(driver);
    }

    public Formula1Driver getDriver(int i){
        return this.drivers.get(i);
    }

    public Formula1Driver getDriver(String team){
        for(int i = 0; i<this.numberOfDrivers(); i++){
            if(drivers.get(i).team.equals(team)){
                return drivers.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> getTeams(){
        ArrayList<String> teams = new ArrayList<>();
        for(Formula1Driver driver: this.drivers){
            teams.add(driver.team);
        }
        return teams;
    }

    public void deleteDriver(Driver driver){
        this.drivers.remove(driver);
    }
   
    public void changeDriver(Driver oldDriver, Formula1Driver newDriver){
        newDriver.team = oldDriver.team;
        if(!this.drivers.contains(newDriver)){
            this.drivers.add(newDriver);
        }
        this.drivers.remove(oldDriver);
    }

    public void sortDrivers(){
        Collections.sort(this.drivers);
    }

    public Race getRace(String name){
        for(int i = 0; i<this.races.size(); i++){
            if(races.get(i).name.equals(name)){
                return this.races.get(i);
            }
        }
        return null;
    }

    public void addToFile() throws IOException{
        // Save Drivers Information
        FileWriter writer = new FileWriter("drivers.txt", false);
        for(int i = 0; i < this.numberOfDrivers(); i++){
            writer.write(this.getDriver(i).toFile() + "\n");
        }
        writer.close();
        
        // Save Races Information
        writer = new FileWriter("races.txt", false);
        for(Race race: this.races){
            writer.write(race.toFile());
        }
        writer.close();
    }
}
