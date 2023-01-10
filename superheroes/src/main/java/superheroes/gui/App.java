package superheroes.gui;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import superheroes.CityMap;
import superheroes.Engine;
import superheroes.Vector2D;
import superheroes.characters.AbstractSuperhero;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class App extends Application{
    private CityMap map;
    private Thread engineThread;
    private Engine engine;
    private AbstractSuperhero chosenHero;
    private Stage stage;
    public void anythingChanged() {
        System.out.println("anything");
            try {
                Button startButton = new Button("Next Day");
                HBox hbox=setHbox();
                VBox vbox = new VBox(hbox, drawMap());
                Scene scene = new Scene(vbox, 640, 660);
                stage.setScene(scene);
                startButton.setOnAction(event -> {
                    engine.nextDay();
                });
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
    }
    @Override
    public void init() {
        map = new CityMap();
        this.engine = new Engine(map);
        this.engineThread = new Thread(engine);
    }


    public void start(Stage primaryStage) throws FileNotFoundException {
        this.stage=primaryStage;
        primaryStage.setTitle("City Map");
        GridPane gridPane=drawMap();
        HBox hbox=setHbox();
        VBox vbox = new VBox(hbox, gridPane);
        Scene scene = new Scene(vbox, 640, 660);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private GridPane drawMap() throws FileNotFoundException {
        GridPane gridPane = new GridPane();
        StackPane stackPane;
        for(int i =0;i<16;i++){
            for(int j = 0; j<16; j++){
                stackPane=map.getStackPane(new Vector2D(i,j));
                gridPane.add(stackPane,i,j);
            }
        }
        setGridPaneEvent(gridPane);
        return gridPane;
    }
    public void setGridPaneEvent(GridPane gridPane){
        gridPane.setOnMouseClicked((EventHandler<Event>) event -> {
            MouseEvent event1 = (MouseEvent) event;
            Vector2D v = new Vector2D((int)(event1.getX()/40),(int)(event1.getY()/40));
            chooseAction(v);
        });
    }
    public HBox setHbox(){
        Button startButton = new Button("Next Day");
        Label DayLabel=new Label("\t Current Day:\t"+ engine.getDay());
        HBox hbox=new HBox(startButton);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(DayLabel);
        startButton.setOnAction(event -> {
            engine.nextDay();
        });
        return hbox;
    }
    public void chooseAction(Vector2D v){
        if(this.map.isRendered()){
            HashMap<Vector2D,Integer> render =map.getRender();
            if(render.containsKey(v))
                chosenHero.move(v,render.get(v));
            map.deleteRender();
            anythingChanged();
        }
        else{
            if(this.map.heroAt(v)!=null){
                chosenHero=map.heroAt(v);
                if (chosenHero.getAvailableDistance()>0){
                    map.render(v,chosenHero);
                    anythingChanged();
                }
            }
        }
    }
}