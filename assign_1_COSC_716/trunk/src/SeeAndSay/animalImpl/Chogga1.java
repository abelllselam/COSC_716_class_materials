package SeeAndSay.animalImpl;

import SeeAndSay.Animal;
import SeeAndSay.Talker;


public class Chogga1 extends Animal implements Talker {

    private final String sound;

    public Chogga1(String animalName) {

        super(animalName);

        this.sound = "Baaa";

    }


    public String getSound() {

        return this.sound;
    }

}
