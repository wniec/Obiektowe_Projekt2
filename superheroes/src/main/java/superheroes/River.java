package superheroes;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class River {
    private final TreeSet<Vector2D> bridges;
    private final TreeSet<Vector2D> river;
    public River(){
        Random rand= new Random();
        ArrayList<Vector2D> river = new ArrayList<>();
        int x1= rand.nextInt(8)+4;
        int y= rand.nextInt(16);
        int x2=x1;
        while(x2==x1){
            x2= rand.nextInt(8)+4;
        }
        for(int i=0;i<y;i++){
            river.add(new Vector2D(x1,i));
        }
        for(int i=0;i<abs(x1-x2);i++){
            river.add(new Vector2D(min(x1,x2)+i,y));
        }
        for(int i=y;i<16;i++){
            river.add(new Vector2D(x2,i));
        }
        TreeSet<Vector2D>bridges= new TreeSet<>(new CompareVector());
        int i=0;
        int index;
        Vector2D v;
        while(i<4){
            index=rand.nextInt(river.size());
            v=river.get(index);
            if(!bridges.contains(v)){
                bridges.add(v);
                river.remove(v);
                i++;
            }
        }
        this.bridges=bridges;
        TreeSet<Vector2D> tmp =new TreeSet<>(new CompareVector());
        tmp.addAll(river);
        this.river=tmp;
    }
    public boolean isBridge(Vector2D v){
        return bridges.contains(v);
    }
    public boolean isRiver(Vector2D v){
        return river.contains(v);
    }
    public boolean contains(Vector2D v){
        return river.contains(v)||bridges.contains(v);
    }
}
