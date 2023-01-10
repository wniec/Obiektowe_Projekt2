package superheroes;

import superheroes.characters.*;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Engine implements Runnable{
    int rounds=0;
    private final ArrayList<Integer>problematicRounds;
    public final CityMap map;
    private int hearts;
    private int problemsForNow=4;
    private boolean blackmailingChosen=false;
    private AbstractSuperhero[] heroes=new AbstractSuperhero[]{new SuperInformatician(),new SuperFireman(),new SuperWarrior(),new SuperPoliceman()};
    private final ArrayList<Problem> problems=new ArrayList<>();
    public Engine(CityMap map){
        this.map=map;
        this.hearts=3;
        problematicRounds=getProblems();
        Problem problem;
        for(int i =0;i<min(problematicRounds.size(),3);i++){
            chooseProblem();
        }
        for(AbstractSuperhero hero:heroes){
            map.addHero(hero);
        }

    }
    @Override
    public void run(){

    }
    public void nextDay(){
        for(Problem problem : problems){
            problem.tick();
        }
        rounds++;
        if(rounds==problematicRounds.get(problemsForNow)){
            chooseProblem();
            problemsForNow=min(problemsForNow+1,problematicRounds.size()-1);
        }
        for(AbstractSuperhero hero: heroes){
            hero.tick();
        }
    }
    public void removeProblem(Problem problem){this.problems.remove(problem);}
    public void gameOver(){}
    public void addHeart(){hearts++;}
    public void removeHeart(){
        hearts--;
        if (hearts==0)
            gameOver();
    }
    public void chooseProblem(){
        Problem problem;
        Random random=new Random();
        problem=new Problem(ProblemType.randomProblem(),this,map);
        if (!blackmailingChosen&&problem.type != ProblemType.CriminalMystery){
            int chance=random.nextInt(100);
            if(chance<rounds)
            {
                problem = new Problem(ProblemType.Blackmailing,this,map);
                blackmailingChosen=true;
            }
        }
        problems.add(problem);
        map.addProblem(problem);
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
            y=(x+65)/15;
            x+= (rand.nextInt(max(y-1,1))+y+1);
            if(x<60)
                problems.add(x);
        }
        return problems;
    }

    public int getDay() {
        return rounds;
    }
}
