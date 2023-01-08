package superheroes;



public class Problem {
    public final ProblemType type;
    private final Engine engine;
    private boolean isDealtWith;
    private int daysToEnd,daysToComplete;
    public Problem(ProblemType type,Engine engine) {
        this.type = type;
        this.isDealtWith=false;
        this.daysToComplete = type.getBaseTime();
        this.engine=engine;
        switch (this.type) {
            case SuperVillain, Blackmailing -> daysToEnd = daysToComplete * 2;
            default -> daysToEnd = daysToComplete * 3;
        }
    }
    public void tick(){
        if(isDealtWith)
            daysToComplete--;
        daysToEnd--;
    }
    public int getDaysToEnd(){
        return daysToEnd;
    }

    public int getDaysToComplete() {
        return daysToComplete;
    }
}
