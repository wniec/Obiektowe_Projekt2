package superheroes;


import javafx.scene.image.Image;
import superheroes.characters.AbstractSuperhero;

public class Problem {
    public final ProblemType type;
    private final Engine engine;
    public final Vector2D position;
    private AbstractSuperhero hero;
    private boolean isDealtWith;
    private int daysToEnd;
    private final int daysToComplete;
    private int daysSpent;
    public Problem(ProblemType type,Engine engine,CityMap map) {
        this.type = type;
        this.isDealtWith=false;
        this.daysToComplete = type.getBaseTime();
        this.position=map.getProblemPosition();
        this.engine=engine;
        this.daysSpent=0;
        switch (this.type) {
            case SuperVillain, Blackmailing -> daysToEnd = daysToComplete * 2;
            default -> daysToEnd = daysToComplete * 3;
        }
    }
    public void tick(){
        if(isDealtWith)
            daysSpent++;
        if(daysToComplete==daysSpent)
        {
            this.success();
            this.hero.problemEnded();
            this.remove();
            return;
        }
        daysToEnd--;
        if(daysToEnd==0)
        {
            this.fail();
            if (this.hero!=null){
                this.hero.problemEnded();
            }
            this.remove();
        }
    }
    private void remove(){
        this.engine.removeProblem(this);
    }
    public void abandon(){
        this.daysSpent=0;
        this.hero=null;
    }

    public void fail(){
        if(this.type!=ProblemType.Blackmailing){
            if(this.position.equals(engine.map.townHall.position)){
                engine.removeHeart();
                engine.removeHeart();
            }
            else if(this.position.equals(engine.map.heroesCentre.position))
                engine.gameOver();
            else
                engine.removeHeart();
        }
        if (this.hero!=null)
            hero.free();
    }
    public void setHero(AbstractSuperhero hero){
        this.isDealtWith=true;
        this.hero=hero;
    }
    public void success(){
        if (this.type == ProblemType.Blackmailing) {
            engine.addHeart();
        }
        this.hero.free();
    }
    public Image getImage(){
        switch(this.type){
            case Blackmailing ->{return new Image("file:src/main/resources/problems/blackmailing.png");}
            case Fire ->{return new Image("file:src/main/resources/problems/fire.png");}
            case CriminalMystery ->{return new Image("file:src/main/resources/problems/criminal.png");}
            case TechnicalProblem ->{return new Image("file:src/main/resources/problems/technical.png");}
            case SuperVillain ->{return new Image("file:src/main/resources/problems/supervillain.png");}
        }
        return null;
    }
    public int getDaysToEnd(){
        return daysToEnd;
    }
    public int getDaysSpent(){
        return daysSpent;
    }

    public int getDaysToComplete() {
        return daysToComplete;
    }
}
