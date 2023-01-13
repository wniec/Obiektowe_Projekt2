package superheroes;
import javafx.application.Application;
import superheroes.gui.App;

public class Main {
    public static void main(String[] args){
        try {
            Application.launch(App.class, args);
        }catch(IllegalArgumentException ex){
            System.out.println(ex);
        }
    }
}