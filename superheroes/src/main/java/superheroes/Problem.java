package superheroes;

import java.util.ArrayList;
import java.util.Random;

public enum Problem {
    Fire,
    CriminalMystery,
    TechnicalProblem,
    SuperVillain,
    Blackmailing;
    public  int timeToFinish=getBaseTime();
    public int timeToEnd=getEndTime();
    private int getBaseTime(){
        Random rand = new Random();
        if (this == Problem.SuperVillain) {
            return rand.nextInt(3) + 3;
        }
        return rand.nextInt(3) + 5;
    }
    private int getEndTime(){
        return switch (this) {
            case SuperVillain, Blackmailing -> this.timeToFinish * 2;
            default -> this.timeToFinish * 3;
        };
    }
    Problem randomProblem(){
        Random rand= new Random();
        int x =rand.nextInt(20);
        if(x<17){
            return Problem.values()[rand.nextInt(3)];
        }
        return Problem.values()[3];
    }
    //Zwraca listę rund w których występuje problem
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
            x+= (rand.nextInt(Math.max(y-1,1)-x)+x+1);
            problems.add(x);
        }
        return problems;
    }
}
