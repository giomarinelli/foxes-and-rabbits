/*
* Driver.java
* @author Gio Marinelli, Joe Forte
* @version 03/14/2024
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    private static List<Animal> animalList = new ArrayList<Animal>();

    public static void main(String[] args) {
        makeAnimalList();
        Simulator mySim = new Simulator(animalList);

        char choice;
        Scanner sc = new Scanner(System.in);
        do {
            choice = getOption(sc);
            takeAction(choice, mySim);
        } while (choice != 'X');
        sc.close();
    }

    private static void makeAnimalList() {
        Field tempf = new Field(40, 40);
        Location templ = new Location(1, 1);

        Animal eagle = new Eagle(true, tempf, templ);
        Animal fox = new Fox(true, tempf, templ);
        Animal rabbit = new Rabbit(true, tempf, templ);

        animalList.add(eagle);
        animalList.add(fox);
        animalList.add(rabbit);
    }

    private static char getOption(Scanner sc) {
        System.out.println("Enter a menu option");
        System.out.println(" R. Reset the simulation");
        System.out.println(" 1. Simulate one step");
        System.out.println(" 2. Simulate two steps");
        System.out.println(" 3. Simulate three steps");
        System.out.println(" 4. Simulate forty steps");
        System.out.println(" 5. Simulate fifty steps");
        System.out.println(" 0. Simulate 100 steps");
        System.out.println(" L. Run long simulation (4000 steps)");
        System.out.println(" X. Exit simulator");
        return sc.next().toUpperCase().charAt(0);
    }

    private static void takeAction(char choice, Simulator mySim) {
        switch (choice) {
            case ('R'):
                mySim.reset();
                break;
            case ('3'):
                mySim.simulateOneStep();
            case ('2'):
                mySim.simulateOneStep();
            case ('1'):
                mySim.simulateOneStep();
                break;
            case ('5'):
                mySim.simulate(10);
            case ('4'):
                mySim.simulate(40);
                break;
            case ('0'):
                mySim.simulate(100);
                break;
            case ('L'):
                mySim.runLongSimulation();
                break;
            case ('X'):
                System.out.println("Goodbye.");
                break;
        }
    }
}
