package superheroes.characters;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import superheroes.*;

import java.util.ArrayList;

public abstract class AbstractSuperhero {
    public abstract int getTime(Problem problem);
    public Problem problem;
    public abstract Image getImage();
    protected int maxDistance;
    protected int availableDistance;
    protected boolean isBusy;
    protected boolean hasHelicopter;
    protected Vector2D position;
    protected int helicopterDays;
    public boolean cannotGoTo(Vector2D position,CityMap map) {
        AbstractStaticObject object;
        if(position.follows(new Vector2D(0,0))&&position.precedes(new Vector2D(15,15))&&!map.river.isRiver(position))
        {
            object = map.objectHere(position);
            if(( object instanceof Obstacle) && ((Obstacle)object).isSevere)
                return true;
            ArrayList<AbstractSuperhero> heroesHere=map.heroesAt(position);
            for(AbstractSuperhero heroHere:heroesHere){
                if(heroHere.isBusy())
                    return true;
            }
            return false;
        }
        return true;
    }
    public int getMaxDistance(){
        return this.maxDistance;
    }
    @Override
    public abstract String toString();

    public int getAvailableDistance() {
        if (isBusy)
            return 0;
        return availableDistance;
    }

    public boolean HasHelicopter() {
        return hasHelicopter;
    }
    public void tick(){
        availableDistance=maxDistance;
        helicopterDays--;
    }
    public void setPosition(Vector2D v){this.position=v;}
    public Vector2D getPosition(){return this.position;}

    public void move(Vector2D v,int steps,CityMap map) {
        Problem problem =map.problemAt(v);
        if(problem!=null){
            this.isBusy=true;
            this.problem=problem;
            problem.setHero(this);
        }
        this.position=v;
        this.availableDistance-=steps;
        map.heroMoved(this);
    }
    public Scene getInfoScene(){
        Label heroLabel= new Label(this.toString());
        HBox heroBox = new HBox(heroLabel);
        heroBox.setAlignment(Pos.CENTER_LEFT);
        Label problemLabel = new Label("current problem:\t");
        HBox problem = new HBox(problemLabel);
        problem.setAlignment(Pos.CENTER_LEFT);
        if (this.isBusy){
            ImageView problemView = new ImageView(this.problem.getImage());
            problemView.setFitWidth(40);
            problemView.setFitHeight(40);
            problem.getChildren().add(problemView);
        }
        Label availableMovesLabel = new Label("available moves:\t");
        HBox availableMoves =new HBox(availableMovesLabel);
        availableMoves.setAlignment(Pos.CENTER_LEFT);
        Image bolt= new Image("file:src/main/resources/bolt.png");
        ImageView movesView;
        for(int i =0; i< this.getAvailableDistance();i++){
            movesView=new ImageView(bolt);
            availableMoves.getChildren().add(movesView);
            movesView.setFitWidth(40);
            movesView.setFitHeight(40);
        }
        Label maxMovesLabel = new Label("max moves:\t");
        HBox maxMoves =new HBox(maxMovesLabel);
        availableMoves.setAlignment(Pos.CENTER_LEFT);
        for(int i =0; i< this.getMaxDistance();i++){
            movesView=new ImageView(bolt);
            maxMoves.getChildren().add(movesView);
            movesView.setFitWidth(40);
            movesView.setFitHeight(40);
        }
        heroLabel.setMinHeight(40);
        problem.setMinHeight(40);
        availableMoves.setMinHeight(40);
        maxMoves.setMinHeight(40);
        VBox vBox= new VBox(heroLabel,problem,availableMoves,maxMoves);
        return new Scene(vBox,320,300);
    }
    //public abstract VBox getCharacteristics();
    public void free(){this.isBusy=false;}
    public boolean isBusy(){return this.isBusy;}
}
