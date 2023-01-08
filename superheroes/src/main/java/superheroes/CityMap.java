package superheroes;

import superheroes.characters.AbstractSuperhero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

public class CityMap {
    private final int width = 16;
    private final int height = 16;
    public final River river;
    private final ArrayList<AbstractStaticObject> staticObjects = new ArrayList<>();

    public CityMap() {
        this.river = new River();
        for (int i = 0; i < 8; i++) {
            placeObject(new Obstacle(false, this));
        }
        for (int i = 0; i < 16; i++) {
            placeObject(new Obstacle(true, this));
        }
    }

    public AbstractStaticObject objectsHere(Vector2D position) {
        for (AbstractStaticObject staticObject : staticObjects) {
            if (Objects.equals(staticObject.getPosition(), position))
                return staticObject;
        }
        return null;
    }

    public boolean isObjectHere(Vector2D position) {
        return objectsHere(position) == null;
    }

    public boolean canBePutHere(Vector2D position) {
        if (river.contains(position))
            return false;
        return isObjectHere(position);
    }

    private void placeObject(AbstractStaticObject object) {
        this.staticObjects.add(object);

    }

    public TreeSet<Vector2D> possibleMoves(Vector2D initialPosition, AbstractSuperhero hero) {
        int moves=hero.getMaxDistance();
        HashMap<Vector2D,Integer> result = new HashMap<>();
        Vector2D[] baseMoves = new Vector2D[]{new Vector2D(1, 0), new Vector2D(-1, 0), new Vector2D(0, 1), new Vector2D(0, -1)};
        ArrayList<Vector2D> stack =new ArrayList<>();
        Vector2D v,u;
        AbstractStaticObject aso;
        int n,k;
        stack.add(initialPosition);
        while (stack.size()>0){
            v=stack.get(stack.size()-1);
            n=result.get(v);
            stack.remove(v);
            k=1;
            aso=objectsHere(v);
            if(aso instanceof  Obstacle && !((Obstacle)aso).isSevere){
                k=2;
            }
            if (n<moves){
                for(Vector2D move:baseMoves){
                    u=v.add(move);
                    if(!hero.cannotGoTo(u,this)&&!result.containsKey(u)){
                        stack.add(u);
                        result.put(u,n+k);
                    }
                }
            }
        }
        return new TreeSet<>(result.keySet());
    }
}
