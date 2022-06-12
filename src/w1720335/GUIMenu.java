/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1720335;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author anastasiiamazur
 */

// class for the GUI
public class GUIMenu{

    int size = 650;

    JFrame frame;
    JPanel window;
    JPanel mainMenu, displayTable, randomRace, randomRacePos;
    CardLayout layout;

    Formula1ChampionshipData data;
    boolean randomRacePosFlag;

    GUIMenu(Formula1ChampionshipData data) throws IOException {
        // initialise data manager and flags
        this.data = data;
        randomRacePosFlag = true;

        // initialise JFrame
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("Formula 1 - Championship Manager");
        this.frame.setSize(new Dimension(size, size));
        this.frame.setLocationRelativeTo(null);

        // creates container panel with cardlayout
        this.layout = new CardLayout();
        this.window = new JPanel(this.layout);

        // initialise panels
        this.displayTable = displayTable();
        this.mainMenu = mainMenu();

        // add views to the container panel
        this.window.add(displayTable, "displayTable");
        this.window.add(mainMenu, "mainMenu");

        // show main menu initially
        this.layout.show(window, "mainMenu");

        // shows JFrame & add container panel to frame
        this.frame.add(window);
        this.frame.setVisible(true);
    }

    // creates a JTable of Formula 1 Drivers
    public JTable createDriversTable(ArrayList<Formula1Driver> drivers){
        String[] columnNames = {"Name", "Team", "Current Points","# of 1 pos", "# of 2 pos", "# of 3 pos"};
        Object[][] data = new Object[drivers.size()+1][6];
        for(int i = 0; i<drivers.size(); i++){
            Object[] row = new Object[6];
            row[0] = drivers.get(i).name;
            row[1] = drivers.get(i).team;
            row[2] = drivers.get(i).current_points;
            row[3] = drivers.get(i).first_pos_num;
            row[4] = drivers.get(i).second_pos_num;
            row[5] = drivers.get(i).third_pos_num;
            data[i] = row;
        }
        JTable driversTable = new JTable(data, columnNames);
        driversTable.setFillsViewportHeight(true);
        return driversTable;
    }

    //initialiser for randomRacePos view
    public void initialiseRandomRacePos(Race race, HashMap<Formula1Driver, Integer> startingPos){
        JLabel raceName = new JLabel("Race Name: "+race.name);
        JLabel raceDate = new JLabel("Race Date: "+race.date);
        String[] columnNames = {"Player", "Team", "Start Pos", "Pos", "Points"};
        Object[][] data = new Object[this.data.numberOfDrivers()+1][5];
        Formula1Driver driver;
        for(int i = 0; i<this.data.numberOfDrivers(); i++){
            driver = this.data.getDriver(i);
            Object[] row = new Object[5];
            row[0] = driver.name;
            row[1] = driver.team;
            row[2] = startingPos.get(driver);
            row[3] = race.positions.get(driver);
            row[4] = driver.current_points;
            data[i] = row;
        }

        JTable raceTable = new JTable(data, columnNames);
        raceTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(raceTable);
        raceTable.setAutoscrolls(false);

        this.randomRacePos = initialisePanel();
        this.randomRacePos.add(raceName);
        this.randomRacePos.add(raceDate);
        this.randomRacePos.add(scrollPane);

        JButton menu = new JButton("Back to menu");
        menu.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                this.menu.layout.show(this.menu.window, "mainMenu");
            }
        });

        this.randomRacePos.add(menu);
    }

    // initialiser method for RandomRace view
    public void initialiseRandomRace(Race race){
        JLabel raceName = new JLabel("Race Name: "+race.name);
        JLabel raceDate = new JLabel("Race Date: "+race.date);
        String[] columnNames = {"Player", "Team", "Pos", "Points"};

        Object[][] data = new Object[this.data.drivers.size()][4];
        int i = 0;
        for( Formula1Driver driver: this.data.drivers){
            Object[] row = new Object[4];
            row[0] = driver.name;
            row[1] = driver.team;
            row[2] = race.positions.get(driver);
            row[3] = driver.current_points;
            data[i] = row;
            i++;
        }
        JTable raceTable = new JTable(data, columnNames);
        raceTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(raceTable);
        raceTable.setAutoscrolls(false);

        this.randomRace = initialisePanel();
        this.randomRace.add(raceName);
        this.randomRace.add(raceDate);;
        this.randomRace.add(scrollPane);

        JButton menu = new JButton("Back to menu");
        menu.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                this.menu.layout.show(this.menu.window, "mainMenu");
            }
        });

        this.randomRace.add(menu);
    }

    //initialising display table view
    public JPanel displayTable(){
        this.displayTable = initialisePanel();
        JTable driversTable = this.createDriversTable(this.data.drivers);
        JScrollPane scrollPane = new JScrollPane(driversTable);
        this.displayTable.add(scrollPane);

        // adding buttons
        JButton ascSort = new JButton("Sort Ascending");
        ascSort.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(this.menu.data.drivers);
                this.menu.window.remove(this.menu.displayTable);
                this.menu.displayTable = null;
                this.menu.displayTable();
                this.menu.window.add(displayTable, "displayTable");
                this.menu.layout.show(this.menu.window, "displayTable");
            }
        });
        this.displayTable.add(ascSort);
        JButton dsc = new JButton("Sort Descending");
        dsc.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(this.menu.data.drivers);
                Collections.reverse(this.menu.data.drivers);
                this.menu.window.remove(this.menu.displayTable);
                this.menu.displayTable = null;
                this.menu.displayTable();
                this.menu.window.add(displayTable, "displayTable");
                this.menu.layout.show(this.menu.window, "displayTable");
            }
        });
        this.displayTable.add(dsc);
        JButton menu = new JButton("Back to menu");
        menu.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                this.menu.layout.show(this.menu.window, "mainMenu");
            }
        });

        this.displayTable.add(menu);
        return this.displayTable;
    }

    //initialising mainMenu view
    public JPanel mainMenu(){
        JPanel mainMenu = initialisePanel();
        JButton displayTable, randomRace, exit, randomRacePos;
        
        // creates menu buttons
        displayTable = menuButton(mainMenu,"Display Table");
        randomRace = menuButton(mainMenu,"Random Race");
        randomRacePos = menuButton(mainMenu, "randomRacePos");
        exit = menuButton(mainMenu,"Exit");

        displayTable.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                this.menu.layout.show(this.menu.window, "displayTable");
            }
        });
        randomRace.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Race race = Race.generateRandomRace();
                race.positions = Race.generateRandomRacePositions(this.menu.data.drivers);
                this.menu.data.races.add(race);
                Race.allocatePoints(race.positions);

                if(this.menu.randomRace != null && this.menu.randomRace.getParent().equals(this.menu.window)){
                    this.menu.window.remove(this.menu.randomRace);
                }
                initialiseRandomRace(race);
                this.menu.window.add(this.menu.randomRace, "randomRace");
                this.menu.layout.show(this.menu.window, "randomRace");
            }
        });
        randomRacePos.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Race race = Race.generateRandomRace();
                ArrayList<Formula1Driver> drivers;
                HashMap<Formula1Driver, Integer> startingPos = Race.generateRandomRacePositions(this.menu.data.drivers);
                Formula1Driver winner = Util.getRandomWinner(startingPos);
                HashMap<Formula1Driver, Integer> endPos = Race.generateRandomRacePositions(this.menu.data.drivers, 2);
                endPos.put(winner, 1);
                race.positions = endPos;
                this.menu.data.races.add(race);
                Race.allocatePoints(endPos);

                if(this.menu.randomRacePos != null && this.menu.randomRacePos.getParent().equals(this.menu.window)){
                    this.menu.window.remove(this.menu.randomRacePos);
                }
                this.menu.initialiseRandomRacePos(race, startingPos);
                this.menu.window.add(this.menu.randomRacePos, "randomRacePos");
                this.menu.layout.show(this.menu.window, "randomRacePos");

            }
        });
        exit.addActionListener(new GUIActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    this.menu.data.addToFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.menu.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        return mainMenu;
    }

    // get new default panel
    public JPanel initialisePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        return panel;
    }

    //get new default button
    public JButton initialiseButton(String buttonText){
        JButton button = new JButton(buttonText);
        Dimension size = new Dimension(200, 50);
        button.setPreferredSize(size);
        return button;
    }

    public JButton menuButton(JPanel menu, String name) {
        JButton button = initialiseButton(name);
        JPanel panel = new JPanel();
        panel.add(button);
        menu.add(panel);
        return button;
    }

}

abstract class GUIActionListener implements ActionListener{
    GUIMenu menu;
    GUIActionListener(GUIMenu menu){
        this.menu = menu;
    }
}
