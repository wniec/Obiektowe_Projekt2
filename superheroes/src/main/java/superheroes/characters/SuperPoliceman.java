package superheroes.characters;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import superheroes.Problem;
import superheroes.ProblemType;

public class SuperPoliceman extends AbstractSuperhero{
    public SuperPoliceman(){
        this.isBusy=false;
        this.maxDistance=4;
        this.availableDistance=4;
        this.helicopterDays=0;
    }
    public Image getImage(){
        return new Image("file:src/main/resources/superheroes/policeman.png");
    }

    @Override
    public int getTime(Problem problem) {
        ProblemType problemType=problem.type;
        switch(problemType){
            case CriminalMystery -> {return problem.getDaysToComplete()-3;}
            case TechnicalProblem -> {return problem.getDaysToComplete()+2;}
            case SuperVillain -> {return problem.getDaysToComplete()-1;}
            default -> {return problem.getDaysToComplete();}
        }
    }
    public String toString(){
        return "SELECTED HERO: SUPERPOLICEMAN";
    }
    public Label characteristics(){
        return new Label("\nSolves Technical issues 2 days longer\n\nSolves criminal mystery3 days faster\n\nDefeats super-villain 1 day faster");
    }
}
