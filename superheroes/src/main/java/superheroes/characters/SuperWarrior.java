package superheroes.characters;

import javafx.scene.image.Image;
import superheroes.Problem;
import superheroes.ProblemType;

public class SuperWarrior extends  AbstractSuperhero {
    public SuperWarrior(){
        this.isBusy=false;
        this.maxDistance=6;
        this.availableDistance=6;
        this.hasHelicopter=false;
    }
    public Image getImage(){
        return new Image("file:src/main/resources/superheroes/warrior.png");
    }
    @Override
    public int getTime(Problem problem) {
        ProblemType problemType=problem.type;
        switch(problemType){
            case CriminalMystery -> {return problem.getDaysToComplete()+2;}
            case SuperVillain -> {return 2;}
            default -> {return problem.getDaysToComplete();}
        }
    }
}
