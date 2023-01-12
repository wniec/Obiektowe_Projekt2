package superheroes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import superheroes.characters.AbstractSuperhero;

import java.util.*;

public class CityMap {
    public final SpecialBuilding townHall;
    public final SpecialBuilding heroesCentre;
    public final River river;
    private boolean isRendered;
    private boolean choosingHelicopter;
    private HashMap<Vector2D,Integer> render=new HashMap<>();
    private final ArrayList<AbstractStaticObject> staticObjects = new ArrayList<>();
    private ArrayList<AbstractSuperhero> heroes = new ArrayList<>();
    private final TreeSet<Vector2D> problemPositions = new TreeSet<>(new CompareVector());
    private final HashMap<Vector2D,Problem> activeProblems = new HashMap<>();

    public CityMap() {
        this.river = new River();
        choosingHelicopter=false;
        this.isRendered = false;
        Obstacle o;
        for (int i = 0; i < 8; i++) {
            o=new Obstacle(false,this);
            placeObject(o);
        }
        for (int i = 0; i < 16; i++) {
            o=new Obstacle(true,this);
            placeObject(o);
        }
        this.townHall=new SpecialBuilding(true,this);
        this.heroesCentre=new SpecialBuilding(false,this);
        AbstractSuperhero.setCentrePosition(heroesCentre.getPosition());
    }

    public AbstractStaticObject objectHere(Vector2D position) {
        for (AbstractStaticObject staticObject : staticObjects) {
            if (staticObject.getPosition().equals(position) )
                return staticObject;
        }
        if (townHall!=null&&townHall.position.equals(position))
            return townHall;
        if (heroesCentre!=null&&heroesCentre.position.equals(position))
            return heroesCentre;

        return null;
    }
    public Image getBackgroundImage(Vector2D position){
        String path;
        if(this.render.containsKey(position)||choosingHelicopter)
            path="file:src/main/resources/chosen/";
        else
            path="file:src/main/resources/";
        if(river.isBridge(position))
            return new Image(path+"bridge.png");
        else if(river.isRiver(position))
            return new Image(path+"river.png");
        else{
            AbstractStaticObject aso = objectHere(position);
            if((aso instanceof Obstacle)&&((Obstacle)aso).isSevere)
                return new Image(path+"log.png");
            else if((aso instanceof Obstacle)&&!((Obstacle)aso).isSevere)
                return new Image(path+"swamp.png");
            else if((aso instanceof SpecialBuilding)&&((SpecialBuilding)aso).isTownHall)
                return new Image(path+"townHall.png");
            else if((aso instanceof SpecialBuilding)&&!((SpecialBuilding)aso).isTownHall)
                return new Image(path+"superheroesCentre.png");
            else
                return new Image(path+"empty.png");
        }


    }
    public StackPane getStackPane(Vector2D position){
        Image background = getBackgroundImage(position);
        ArrayList<AbstractSuperhero> heroes = heroesAt(position);
        Problem problem = problemAt(position);
        ImageView backgroundView=new ImageView();
        backgroundView.setFitWidth(40);
        backgroundView.setFitHeight(40);
        backgroundView.setImage(background);
        StackPane stackPane =new StackPane(backgroundView);
        if(problem!=null){
            Image problemImage=problem.getImage();
            ImageView problemView = new ImageView();
            problemView.setFitWidth(40);
            problemView.setFitHeight(40);
            problemView.setImage(problemImage);
            stackPane.getChildren().add(problemView);
        }
        if(heroes.size()>0){
            Image heroImage = heroes.get(0).getImage();
            ImageView heroView=new ImageView();
            heroView.setFitWidth(40);
            heroView.setFitHeight(40);
            heroView.setImage(heroImage);
            stackPane.getChildren().add(heroView);
        }
        return stackPane;
    }

    public boolean isObjectHere(Vector2D position) {
        return (objectHere(position) == null);
    }

    public boolean cannotBePutHere(Vector2D position) {
        if (river.contains(position))
            return true;
        return !isObjectHere(position);
    }
    private void placeObject(AbstractStaticObject object) {this.staticObjects.add(object);}
    public void addProblem(Problem problem){this.activeProblems.put(problem.position,problem);}
    public void removeProblem(Problem problem){this.activeProblems.remove(problem.position);}
    public Problem problemAt(Vector2D position){return activeProblems.get(position);}
    public Vector2D getProblemPosition(){
        Vector2D v;
        Random rand = new Random();
        v=new Vector2D(rand.nextInt(16), rand.nextInt(16));
        while(cannotBePutHere(v) || (objectHere(v) instanceof Obstacle)|| problemPositions.contains(v)){
            v=new Vector2D(rand.nextInt(16), rand.nextInt(16));
        }
        addProblemPosition(v);
        return v;
    }
    public ArrayList<AbstractSuperhero> heroesAt(Vector2D position){
        ArrayList<AbstractSuperhero> heroesHere = new ArrayList<>();
        for(AbstractSuperhero hero:heroes){
            if(hero.getPosition().equals(position))
                heroesHere.add(hero);

        }
        return heroesHere;
    }

    private void addProblemPosition(Vector2D v){this.problemPositions.add(v);}
    public void heroMoved(AbstractSuperhero hero){
        this.heroes.remove(hero);
        this.heroes.add(hero);
    }
    public void addHero(AbstractSuperhero hero){
        Random rand= new Random();
        Vector2D v=new Vector2D(rand.nextInt(16), rand.nextInt(16) );
        while (hero.cannotGoTo(v,this)){
            v=new Vector2D(rand.nextInt(16), rand.nextInt(16) );
        }
        hero.setPosition(v);
        this.heroes.add(hero);
    }
    public void render(Vector2D initialPosition, AbstractSuperhero hero) {
        int moves=hero.getAvailableDistance();
        HashMap<Vector2D,Integer> result = new HashMap<>();
        Vector2D[] baseMoves = new Vector2D[]{new Vector2D(1, 0), new Vector2D(-1, 0), new Vector2D(0, 1), new Vector2D(0, -1)};
        ArrayList<Vector2D> stack =new ArrayList<>();
        Vector2D v,u;
        AbstractStaticObject aso;
        int n,k;
        stack.add(initialPosition);
        result.put(initialPosition,0);
        while (stack.size()>0){
            v=stack.get(stack.size()-1);
            n=result.get(v);
            stack.remove(v);
            k=1;
            aso= objectHere(v);
            if(aso instanceof  Obstacle && !((Obstacle)aso).isSevere)
                k=2;
            if (n<=moves){
                for(Vector2D move:baseMoves){
                    u=v.add(move);
                    if(!hero.cannotGoTo(u,this)&&!result.containsKey(u)){
                        stack.add(u);
                        result.put(u, n + k);
                    }
                }
            }
            else
                result.remove(v);
        }
        this.render = result;
        this.isRendered=true;
    }
    public void deleteRender(){
        this.render=new HashMap<>();
        this.isRendered=false;
    }
    public boolean isRendered(){
        return this.isRendered;
    }
    public HashMap<Vector2D,Integer> getRender(){return this.render;}

    public void setHelicopterChoice(boolean choosingHelicopter) {
        this.choosingHelicopter = choosingHelicopter;
    }
}