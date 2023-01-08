package superheroes;

import java.util.ArrayList;
import java.util.Random;

public enum ProblemType {
    Fire,
    CriminalMystery,
    TechnicalProblem,
    SuperVillain,
    Blackmailing;
    public int getBaseTime(){
        Random rand = new Random();
        if (this == ProblemType.SuperVillain) {
            return rand.nextInt(3) + 3;
        }
        if (this == ProblemType.Blackmailing) {
            return 3;
        }
        return rand.nextInt(3) + 5;
    }
    static ProblemType randomProblem(){
        Random rand= new Random();
        int x =rand.nextInt(20);
        if(x<17){
            return ProblemType.values()[rand.nextInt(3)];
        }
        return ProblemType.values()[3];
    }
    //Zwraca listę rund w których występuje problem
}
