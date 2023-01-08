package superheroes.gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import superheroes.CityMap;
import superheroes.Engine;
import superheroes.Vector2D;

import java.io.FileNotFoundException;

public class App extends Application{
    private CityMap map;
    private GridPane gridPane;
    private Thread engineThread;
    private Engine engine;
    private Stage stage;
    public void anythingChanged(Vector2D a, Vector2D b) {
        Platform.runLater(() -> {
            try {
                drawMap();
                VBox vbox = new VBox(this.gridPane);
                Scene newScene = new Scene(vbox, 600, 600);
                stage.setScene(newScene);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Override
    public void init() throws Exception {
        map = new CityMap();
        this.engine = new Engine(map);
        this.engineThread = new Thread(engine);
    }


    public void start(Stage primaryStage) throws FileNotFoundException {
        this.stage=primaryStage;
        primaryStage.setTitle("City Map");
        Button startButton = new Button("Next Day");
        drawMap();
        VBox box = new VBox(startButton, gridPane);
        Scene scene = new Scene(box, 640, 660);
        startButton.setOnAction(event -> {
            engineThread.start();
        });
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void drawMap() throws FileNotFoundException {
        this.stage=new Stage();
        this.gridPane = new GridPane();
        for(int i =0;i<16;i++){
            for(int j = 0; j<16; j++){
                Image empty = new Image("file:src/main/resources/empty.png");
                ImageView emptyView = new ImageView();
                emptyView.setFitWidth(40);
                emptyView.setFitHeight(40);
                emptyView.setImage(empty);
                gridPane.add(emptyView,i,j);
            }
        }
    }
}