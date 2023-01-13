package superheroes.gui;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import superheroes.CityMap;
import superheroes.Engine;
import superheroes.Vector2D;
import superheroes.characters.AbstractSuperhero;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class App extends Application{
    private CityMap map;
    private Engine engine;
    private AbstractSuperhero chosenHero;
    private GridPane gridPane;
    private Stage stage;
    private HBox hbox;
    private boolean helicopterChoice;
    private VBox heroBox=new VBox(new Label("NO SUPERHERO SELECTED"));

    public void anythingChanged() {
            try {
                if(engine.isGameOver()){
                    gameOver();
                }
                else if(engine.getDay()==60){
                    gameWon();
                }
                else{
                    setHBox();
                    drawMap();
                    VBox vbox = new VBox(hbox, gridPane);
                    hbox=new HBox(vbox,heroBox);
                    Scene scene = new Scene(hbox, 1000, 660);
                    stage.setScene(scene);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
    }
    @Override
    public void init() {
        map = new CityMap();
        this.engine = new Engine(map);
    }
    public void gameOver(){
        this.stage.setTitle("GameOver");
        Label label = new Label("GAME OVER");
        label.setFont(new Font("Arial Black",60));
        label.setTextFill(new Color(1.0,1.0,1.0,1.0));
        gridPane=new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.add(label,0,0);
        label.setAlignment(Pos.CENTER);
        Scene scene=new Scene(gridPane,1000,660);
        gridPane.setAlignment(Pos.CENTER);
        this.stage.setScene(scene);
    }
    public void gameWon(){
        this.stage.setTitle("You Won");
        Label label = new Label("YOU WON");
        label.setFont(new Font("Arial Black",60));
        label.setTextFill(new Color(1.0,1.0,1.0,1.0));
        gridPane=new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.add(label,0,0);
        label.setAlignment(Pos.CENTER);
        Scene scene=new Scene(gridPane,1000,660);
        gridPane.setAlignment(Pos.CENTER);
        this.stage.setScene(scene);
    }


    public void start(Stage primaryStage) throws FileNotFoundException {
        this.stage=primaryStage;
        this.stage.setTitle("City Map");
        drawMap();
        setHBox();
        VBox vbox = new VBox(hbox, gridPane);
        hbox=new HBox(vbox,heroBox);
        Scene scene = new Scene(hbox, 1000, 660);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private void drawMap() throws FileNotFoundException {
        gridPane = new GridPane();
        StackPane stackPane;
        for(int i =0;i<16;i++){
            for(int j = 0; j<16; j++){
                stackPane=map.getStackPane(new Vector2D(i,j));
                gridPane.add(stackPane,i,j);
            }
        }
        setGridPaneEvent(gridPane);
    }
    public void setGridPaneEvent(GridPane gridPane){
        gridPane.setOnMouseClicked((EventHandler<Event>) event -> {
            MouseEvent event1 = (MouseEvent) event;
            Vector2D v = new Vector2D((int)(event1.getX()/40),(int)(event1.getY()/40));
            chooseAction(v);
        });
    }
    private void setHBox(){
        Button startButton = new Button("Next Day");
        Label DayLabel=new Label("\t Current Day:\t"+ engine.getDay()+"\t");
        hbox=new HBox(startButton);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(DayLabel);
        addHearts(hbox);
        startButton.setOnAction(event -> {
            engine.nextDay();
            noHeroSelected();
            anythingChanged();
        });
    }
    private void addHearts(HBox hbox){
        int hearts=engine.getHearts();
        Image image;
        ImageView view;
        for(int i=0;i<4;i++){
            if(i<hearts)
                image=new Image("file:src/main/resources/hearts/full.png");
            else
                image=new Image("file:src/main/resources/hearts/empty.png");
            view=new ImageView(image);
            view.setFitWidth(20);
            view.setFitHeight(20);
            hbox.getChildren().add(view);
        }
    }
    public void chooseAction(Vector2D v){
        if(!helicopterChoice){
            if(this.map.isRendered()){
                HashMap<Vector2D,Integer> render =map.getRender();
                if(render.containsKey(v))
                    chosenHero.move(v,render.get(v),map);
                map.deleteRender();
            }
            else{
                if(this.map.heroesAt(v).size()>0){
                    chosenHero=map.heroesAt(v).get(0);
                    map.render(v,chosenHero);
                }
                else if (this.map.heroesCentre.getPosition().equals(v)){
                    map.setHelicopterChoice(true);
                    helicopterChoice=true;
                }
                else{
                    chosenHero=null;
                    map.setHelicopterChoice(false);
                    helicopterChoice=false;
                }
            }
        }
        else{
            if(this.map.heroesAt(v).size()>0){
                chosenHero=map.heroesAt(v).get(0);
                chosenHero.setHelicopter();
            }
            helicopterChoice=false;
            map.setHelicopterChoice(false);
        }
        showHero();
        anythingChanged();
    }
    public void showHero(){
        if(chosenHero!=null)
            heroBox=chosenHero.getInfoBox();
        else
            noHeroSelected();
    }
    public void noHeroSelected(){
        map.deleteRender();
        chosenHero=null;
        Label label = new Label("NO SUPERHERO SELECTED");
        heroBox=new VBox(label);

    }
}