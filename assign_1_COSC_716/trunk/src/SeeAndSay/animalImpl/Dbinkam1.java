package SeeAndSay.animalImpl;

import SeeAndSay.Animal;
import SeeAndSay.Talker;


public class Dbinkam1 extends Animal implements Talker {
    private boolean flying = true;
    private String sound;

    public Dbinkam1(String animalName) {
        super(animalName);
        this.sound = "dbinkam1.wav";
    }

    public String getSound() {
        return this.sound;
    }

}
