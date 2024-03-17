import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SampleAnimal extends Animal {
    
   
    
    
    private static final int BREEDING_AGE = 0;
    
    private static final int MAX_AGE = 0; //
    
    private static final double BREEDING_PROBABILITY = 0;
   
    private static final int MAX_LITTER_SIZE = 1;

    private int willMove = 3;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    public SampleAnimal(Field field, Location location){
        super(field, location);
    }

    @Override
    public void act(List<Animal> newAnimals) {
        if (getLocation().getRow() < 79){
            setLocation(new Location(getLocation().getRow() + 1, getLocation().getCol()));
        }
        else{
            setLocation(new Location(0,getLocation().getCol()));
        }

        
    }

    @Override
    protected int getBreedingAge() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBreedingAge'");
    }

    @Override
    protected int getMaxAge() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxAge'");
    }

    @Override
    protected double getBreedingProbability() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBreedingProbability'");
    }

    @Override
    protected int getMaxLitterSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxLitterSize'");
    }

    
    
    

}
