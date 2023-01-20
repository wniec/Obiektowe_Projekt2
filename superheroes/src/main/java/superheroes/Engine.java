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
    private final ArrayList <Problem> problemsToRemove;
    private boolean gameOver;
    private int hearts;
    private int problemsForNow=4;
    private boolean blackmailingChosen=false;
    private boolean centreFireChosen=false,townHallFireChosen=false;
    private final AbstractSuperhero[] heroes=new AbstractSuperhero[]{new SuperInformatician(),new SuperFireman(),new SuperWarrior(),new SuperPoliceman()};
    private final ArrayList<Problem> problems=new ArrayList<>();
    public Engine(CityMap map){
        this.problemsToRemove = new ArrayList<>();
        this.map=map;
        this.hearts=3;
        gameOver=false;
        problematicRounds=getProblems();
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
        clearProblems();
        rounds++;
        if(rounds==problematicRounds.get(problemsForNow)){
            chooseProblem();
            problemsForNow=min(problemsForNow+1,problematicRounds.size()-1);
        }
        for(AbstractSuperhero hero: heroes){
            hero.tick();
        }
    }
    public void removeProblem(Problem problem){this.problemsToRemove.add(problem);}
    public void clearProblems(){
        for (Problem problem:problemsToRemove){
            this.problems.remove(problem);
            map.removeProblem(problem);
        }
        problemsToRemove.clear();
    }
    public void gameOver(){
        this.gameOver=true;}
    public boolean isGameOver(){return this.gameOver;}
    public void addHeart(){hearts++;}

    public int getHearts() {return hearts;}

    public void removeHeart(){
        hearts--;
        if (hearts<=0)
            gameOver();
    }
    public void chooseProblem(){
        Problem problem;
        Random random=new Random();
        problem=new Problem(ProblemType.randomProblem(),this,map);
        //Mechanika losowania problemu, którym może być szataż;
        //Zwiększam prawdopodobieństwo pożaru w ratuszu i w centrum superbohaterów
        if (!blackmailingChosen&&problem.type != ProblemType.Fire){
            int chance=random.nextInt(120);
            if(chance<rounds)
            {
                problem = new Problem(ProblemType.Blackmailing,this,map);
                blackmailingChosen=true;
            }
        }
        else if(problem.type==ProblemType.Fire&&!townHallFireChosen&&rounds>15){
            problem = new Problem(this,map,false);
            townHallFireChosen=true;
        }
        else if(problem.type==ProblemType.Fire&&!centreFireChosen&&rounds>30){
            problem = new Problem(this,map,true);
            centreFireChosen=true;
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
