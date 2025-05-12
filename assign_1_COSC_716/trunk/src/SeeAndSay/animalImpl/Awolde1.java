//By Abel Woldemichael
package SeeAndSay.animalImpl;

import SeeAndSay.Animal;
import SeeAndSay.Talker;

public class Awolde1 extends Animal implements Talker {
    private final String sound;

    public Awolde1(String animalName) {
        super(animalName);
        this.sound = "meow meow!";
    }

    @Override
    public String getSound() {
        return sound;
    }
}