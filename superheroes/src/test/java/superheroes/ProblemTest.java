package superheroes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProblemTest {
    @Test
    public void getTimeTest(){
        Problem problem = new Problem(ProblemType.TechnicalProblem,new Engine(new CityMap()));
        assertTrue(problem.getDaysToComplete()>4&& problem.getDaysToComplete()<8);
    }
    @Test
    public void get2TimeTest(){
        Problem problem = new Problem(ProblemType.TechnicalProblem,new Engine(new CityMap()));
        assertEquals(problem.getDaysToComplete(),problem.getDaysToComplete());
    }
    @Test
    public void getEndTimeTest(){
        Problem problem = new Problem(ProblemType.TechnicalProblem,new Engine(new CityMap()));
        assertEquals(problem.getDaysToEnd(),3* problem.getDaysToComplete());
    }
}
