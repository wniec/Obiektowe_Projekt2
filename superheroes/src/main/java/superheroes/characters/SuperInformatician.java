package superheroes.characters;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import superheroes.Problem;
import superheroes.ProblemType;

public class SuperInformatician extends AbstractSuperhero {
    public SuperInformatician(){
        this.isBusy=false;
        this.maxDistance=3;
        this.availableDistance=3;
        this.helicopterDays=0;
    }
    public Image getImage(){
        return new Image("file:src/main/resources/superheroes/hacker.png");
    }
    @Override
    public int getTime(Problem problem) {
        ProblemType problemType=problem.type;
      switch(problemType){
          case TechnicalProblem -> {return 1;}
          case CriminalMystery -> {return problem.getDaysToComplete()-1;}
          case Fire -> {return problem.getDaysToComplete()+1;}
          case SuperVillain -> {return problem.getDaysToComplete()+2;}
          default -> {return problem.getDaysToComplete();}
      }
    }
    public String toString(){
        return "SELECTED HERO: SUPERINFORMATICIAN";
    }
    public Label characteristics(){
        return new Label("\nSolves Technical issues in 1 day\n\nSolves criminal mystery 1 day faster\n\nExtinguishes fire 1 day longer\n\nDefeats super-villain 2 days longer");
    }
}
