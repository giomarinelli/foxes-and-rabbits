import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Eagle extends Animal{

    private static final int BREEDING_AGE = 15;
    private static final int MAX_AGE = 250;
    private static final double BREEDING_PROBABILITY = 0.08;
    private static final int MAX_LITTER_SIZE = 5;
    private static final int EAGLE_FOOD_VALUE = 25;

    private static final Random rand = Randomizer.getRandom();

    private int direction = 1;
    private int swoopCountdown = 9;
    private int hunger = 0;

    private boolean isSwooped = false;

    public Eagle(boolean randomAge, Field field, Location location){
        super(field, location);
    }

    @Override
    public void act(List<Animal> newAnimals) {
        super.incrementAge();
        hunger++;

        if (isSwooped){
            setLocation(new Location(0, getLocation().getCol()));
            isSwooped = false;
        }

        if(isAlive()) {
            giveBirth(newAnimals);
            swoopCountdown--;
            if (swoopCountdown <= 0){
                //Do swoop
                Location foodLoc = findFood();

                if (foodLoc == null){
                    swoopCountdown = 9;
                    return;
                }
                else{
                    setLocation(foodLoc);
                    isSwooped = true;
                }

                swoopCountdown = 9;
            }
            else{
                Location newLoc = new Location(0, getLocation().getCol() + direction);
                if (newLoc.getCol() >= getField().getWidth() || newLoc.getCol() < 0){
                    direction *= -1;
                    newLoc = new Location(0, getLocation().getCol() + direction + direction);
                }
                setLocation(newLoc);
            }
        }
        else{
            setDead();
        }
    }

    private Location findFood()
    {
        int currentCol = getLocation().getCol();
        Field field = getField();
        LinkedList<Location> cells = new LinkedList<Location>();
        for (int i = 1; i < 46; i++){
            cells.addLast(new Location(i, currentCol));
        }

        Iterator<Location> it = cells.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                ((Animal)animal).setDead();
                this.hunger = 0;
                return where;
            }
        }
        return null;
    }

    // private void giveBirth(List<Animal> newAnimals){
    //     if (getAge() >= BREEDING_AGE){
    //         if (rand.nextDouble() <= BREEDING_PROBABILITY){
    //             int numOfYoung = rand.nextInt(MAX_LITTER_SIZE + 1);
    //             for (int i = 0; i < numOfYoung; i++){
    //                 int col = getLocation().getCol() + (rand.nextInt(5) - 3);
    //                 col = Math.max(Math.min(col, getField().getWidth() - 1), 0);
    //                 newAnimals.add(new Eagle(getField(), col));
    //             }
    //         }
    //     }
    // }

    public Animal makeAnimal(boolean randAge, Field field, Location location){
        Animal eagle = new Eagle(randAge, field, location);
        return eagle;
    }

    @Override
    protected int getBreedingAge() {
        return BREEDING_AGE;
    }

    @Override
    protected int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    @Override
    protected int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }
    
    @Override
    protected boolean isAlive(){
        return super.isAlive() && super.getAge() <= MAX_AGE && this.hunger <= EAGLE_FOOD_VALUE;
    }

    @Override
    public double getCreationProbability()
    {
        return 0.04;
    }
}
