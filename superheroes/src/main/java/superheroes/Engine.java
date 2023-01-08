package superheroes;

import java.util.ArrayList;
import java.util.Random;

public class Engine implements Runnable{
    int rounds=60;
    private ArrayList<Integer>problematicRounds;
    public final CityMap map;
    private ArrayList<Problem> problems=new ArrayList<>();
    public Engine(CityMap map){
        this.map=map;
        problematicRounds=getProblems();
        for(int i =0;i<problematicRounds.size();i++){
            problems.add(new Problem(ProblemType.randomProblem(),this));
        }

    }
    @Override
    public void run(){

    }
    public ArrayList<Integer> getProblems(){
        int x=0;
        int y;
        Random rand = new Random();
        ArrayList<Integer>problems=new ArrayList<>();
        for(int i=0;i<3;i++){
            problems.add(x);
        }
        while (x<60){
            y=(x+65)%15;
            x+= (rand.nextInt(Math.max(y-1,1))+y+1);
            problems.add(x);
        }
        return problems;
    }
}
