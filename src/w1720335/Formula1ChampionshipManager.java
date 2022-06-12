/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author anastasiiamazur
 */

// a class for managing formula 1 drivers and races
public class Formula1ChampionshipManager implements ChampionshipManager{
   
    Formula1ChampionshipData data;

    Formula1ChampionshipManager() throws IOException {
        data = new Formula1ChampionshipData();
    }
    
    @Override
    public Formula1Driver createDriver(){
        Formula1Driver driver = new Formula1Driver();

        System.out.println("--------------------------------------------");
        System.out.println("Type a name of a driver: ");

        Scanner sc = new Scanner(System.in);
        String input_name = sc.nextLine();
        boolean teamSelected = false;
        String input_team = "";
        while(!teamSelected) {
            System.out.println("Type a team of a driver: ");
            input_team = sc.nextLine();
            if(this.data.getTeams().contains(input_team)){
                System.out.println("There is a driver already associated with this team.");
                System.out.println("Please type another one.");
            }else{
                teamSelected = true;
            }
        }


        driver.name = input_name;
        driver.team = input_team;
        driver.current_points = 0;
        driver.first_pos_num = 0;
        driver.second_pos_num = 0;
        driver.third_pos_num = 0;

        this.data.addDriver(driver);
        return driver;
    }
    
    // a method that deletes chosen drivers
    @Override
    public void deleteDriver(){
        this.data.deleteDriver(selectDriver());
    }

    // a method that changes a driver for a team
    @Override
    public void changeDriver(){
        Formula1Driver oldDriver = selectDriver();
        String name;
        System.out.println("Please type in the name of the new Driver (not currently in table): ");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        Formula1Driver newDriver = new Formula1Driver();
        newDriver.name = name;
        this.data.changeDriver(oldDriver, newDriver);
    }
    
    // this method displays the whole table with all data
    @Override
    public void displayTable(){
        if(this.data.numberOfDrivers() == 0){
            System.out.println("There are currently no drivers.");
        }else {
            System.out.println("--------------------------------------------");
            this.data.sortDrivers();
            for (int i = 0; i < this.data.numberOfDrivers(); i++) {
                System.out.println(this.data.getDriver(i).toString());

            }
        }
    }

    
    // this method displays information about one chosen driver
    @Override
    public void displayDriver(){
        if(this.data.numberOfDrivers() == 0){
            System.out.println("There are currently no drivers.");
        }else {
            Formula1Driver driver = selectDriver();
            System.out.println(driver.toString());
        }
    }

    // this method helps to select driver for deleteDriver, changeDriver and dasplayDriver methond
    public Formula1Driver selectDriver(){
        if(this.data.drivers.size() == 0){
            return null;
        }
        HashMap<Integer, Formula1Driver> drivers = new HashMap<>();
        for(int i = 0; i < this.data.numberOfDrivers(); i++){
            drivers.put(i+1, this.data.getDriver(i));
            System.out.println("index: " + (i+1) + " | Name: " + this.data.getDriver(i).name + " | Team: "+this.data.getDriver(i).team);
        }
        boolean selectedDriver = false;
        ArrayList<Integer> indexes = new ArrayList<>(drivers.keySet());
        int selectedIndex = 0;
        while(!selectedDriver) {
            System.out.println("--------------------------------------------");
            System.out.println("Please select the index of the driver: ");
            Scanner sc = new Scanner(System.in);
            try {
                selectedIndex = Integer.parseInt(sc.nextLine());
                if(!indexes.contains(selectedIndex)){
                    System.out.println("The Driver with such index doesn't exits");
                }else{
                    selectedDriver = true;
                }
            }catch (Exception e){
                System.out.println("Please type in a number: ");
            }

        }
        return drivers.get(selectedIndex);
    }

    // this method allows user to create a new race
    public void addRace() {
        Race race = new Race();
        System.out.println("--------------------------------------------");
        System.out.println("Type a location of a race: ");
        Scanner sc = new Scanner(System.in);
        String location = sc.nextLine();
        System.out.println("Type a date of a race: ");
        String date = sc.nextLine();
        race.name = location;
        race.date = date;
        race.positions = new HashMap<>();
        ArrayList<Integer> positions = new ArrayList<>();
        int position;
        boolean gotPosition;
        for(int i = 0; i < this.data.numberOfDrivers(); i++){
            gotPosition = false;
            while(!gotPosition) {
                System.out.println("--------------------------------------------");
                System.out.println("Please type in the place of Driver: " + this.data.getDriver(i).name + " Team: " + this.data.getDriver(i).team);
                sc = new Scanner(System.in);
                position = Integer.parseInt(sc.nextLine());
                if(position>this.data.numberOfDrivers() || position < 1){
                    System.out.println("There are " + this.data.numberOfDrivers() + " drivers in the race.");
                    System.out.println("Driver " + this.data.getDriver(i).name + " can't be in position " + position);
                    System.out.println("Please Try again");
                }else if(positions.contains(position)){
                    System.out.println("There is already a driver at position " + position);
                    System.out.println("Please Try again");
                }
                else{
                    race.positions.put(this.data.getDriver(i), position);
                    this.data.getDriver(i).allocatePoints(position);
                    gotPosition = true;
                    positions.add(position);
                }
                System.out.println("--------------------------------------------");
            }
        }
        this.data.races.add(race);
    }

    // this method display the choden race with all participants and their statistics
    public void displayRace(){
        System.out.println("--------------------------------------------");
        if(this.data.races.size() != 0) {
            boolean raceFound = false;
            Race race = null;
            while (!raceFound) {
                System.out.println("Please select the Race");
                System.out.println("--------------------------------------------");
                for (int i = 0; i < this.data.races.size(); i++) {
                    System.out.println(this.data.races.get(i).name);
                }
                System.out.println("--------------------------------------------");
                Scanner sc = new Scanner(System.in);
                String user_input = sc.nextLine();
                race = this.data.getRace(user_input);
                if (race == null) {
                    System.out.println("Was not able to find race with name: " + user_input);
                } else {
                    raceFound = true;
                }

            }
            ArrayList<Formula1Driver> drivers = new ArrayList<Formula1Driver>(race.positions.keySet());
            Formula1Driver driver;
            System.out.println(race.name + ", " + race.date);
            for (int i = 0; i < race.positions.size(); i++) {
                driver = drivers.get(i);
                System.out.println("Name: " + driver.name + ", Team: " + driver.team + ", Position: " + race.positions.get(driver));
            }
        }else{
            System.out.println("Sorry no races currently stored.");
        }
    }
}

