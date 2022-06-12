/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;
import java.io.IOException;
import java.util.*;
import java.io.FileNotFoundException;

/**
 *
 * @author anastasiiamazur
 */
// class that is responsible for console menu which allows users to choose what they want to do
public class ConsoleMenu {

    boolean running;

    ConsoleMenu() {
        this.running = true;
    }

    public void start() throws FileNotFoundException, IOException, Exception {
        Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
        while(this.running){
            System.out.println("--------------------------------------------");
            System.out.println("Choose an option:\n 1. Create driver \n 2. Change driver \n 3. Delete driver \n 4. Display table \n 5. Display driver \n 6. Add race \n 7. Display race \n 8. OpenGUI \n 9. Exit");
            System.out.println("--------------------------------------------");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if ("1".equals(input)) {
                manager.createDriver();
            } else if ("2".equals(input)) {
                manager.changeDriver();
            } else if ("3".equals(input)) {
                manager.deleteDriver();
            } else if ("4".equals(input)) {
                manager.displayTable();
            } else if ("5".equals(input)) {
                manager.displayDriver();
            } else if ("6".equals(input)) {
                manager.addRace();
            } else if ("7".equals(input)) {
                manager.displayRace();
            } else if ("8".equals(input)) {
                new GUIMenu(manager.data);
            } else {
                exit();
                manager.data.addToFile();
            }
        }

    }

    private void exit() {
        this.running = false;
    }

}
