package superheroes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProblemTest {
    @Test
    public void getTimeTest(){
        CityMap c=new CityMap();
        Problem problem = new Problem(ProblemType.TechnicalProblem,new Engine(c),c);
        assertTrue(problem.getDaysToComplete()>4&& problem.getDaysToComplete()<8);
    }
    @Test
    public void get2TimeTest(){
        CityMap c=new CityMap();
        Problem problem = new Problem(ProblemType.TechnicalProblem,new Engine(c),c);
        assertEquals(problem.getDaysToComplete(),problem.getDaysToComplete());
    }
    @Test
    public void getEndTimeTest(){
        CityMap c=new CityMap();
        Problem problem = new Problem(ProblemType.TechnicalProblem,new Engine(c),c);
        assertEquals(problem.getDaysToEnd(),3* problem.getDaysToComplete());
    }
}
