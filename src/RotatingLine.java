import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.canvas.GraphicsContext;


public class RotatingLine extends Application {
    int angle = 0;
    int red;
    int green;
    int blue;
    static int col = 0;
    static int rev = 0;
    double x = 600, y = 600, xf = 350, yf = 300;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 6 v5");
        Group root = new Group();
        Scene scene = new Scene(root,800,700);
        Canvas canvas = new Canvas(800, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fill();
        gc.fillRect(0,0,800,700);


        Label text1 = new Label();
        text1.setText("Laboratorul 6 TAP");
        text1.setStyle("-fx-font: normal bold 20px 'serif'");
        text1.setTextFill(Color.BLUE);
        text1.setTranslateX(250);

        Line line = new Line(xf, yf, 200, 200);
        line.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        line.setStrokeWidth(10);

        Button button = new Button("START");
        button.setTranslateX(200);
        button.setTranslateY(560);

        Button button2 = new Button("INVERT");
        button2.setTranslateX(260);
        button2.setTranslateY(560);

        Button button3 = new Button("SPEED UP");
        button3.setTranslateX(320);
        button3.setTranslateY(560);

        Button button4 = new Button("SLOW DOWN");
        button4.setTranslateX(395);
        button4.setTranslateY(560);

        TextField input1 = new TextField();
        input1.setTranslateX(300);
        input1.setTranslateY(600);

        Button button5 = new Button("CHANGE TITLE");
        button5.setTranslateX(450);
        button5.setTranslateY(600);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(100), ae ->
                drawShapes(line,20)));

        ChoiceBox bgbox = new ChoiceBox();
        bgbox.getItems().addAll("Red", "Yellow", "Black", "White", "Gray");
        bgbox.setTranslateX(600);
        bgbox.setTranslateY(400);


        button.setOnAction(event -> {
            if (col == 0) {
                timeline.play();
                button.setText("STOP");
                col = 1;
            } else {
                timeline.stop();
                col = 0;
                button.setText("START");
            }
        });

        button2.setOnAction(event -> {
            if (rev == 0) {
                timeline.stop();
                timeline.getKeyFrames().clear();
                timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(100), ae ->
                        drawShapes(line,-20)));
                if(col == 1){
                    timeline.play();
                }
                rev = 1;
            } else {
                timeline.stop();
                timeline.getKeyFrames().clear();
                timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(100), ae ->
                        drawShapes(line, 20)));
                if(col == 1){
                    timeline.play();
                }
                rev = 0;
            }
            });

        button3.setOnAction(event -> {
            timeline.setRate(timeline.getRate() + 1);
        });

        button4.setOnAction(event -> {
            if (timeline.getRate() > 1 ){
                timeline.setRate(timeline.getRate() - 1);
            }
        });

        button5.setOnAction(event -> {
           String loc_text = input1.getCharacters().toString();
           text1.setText(loc_text);
        });

        bgbox.setOnAction(event -> {
            int selectedIndex = bgbox.getSelectionModel().getSelectedIndex();
            //Object selectedItem = bgbox.getSelectionModel().getSelectedItem();
            //("Red", "Yellow", "Black", "White", "Gray");
            if(selectedIndex == 0){
                gc.setFill(Color.RED);
                gc.fill();
                gc.fillRect(0,0,800,700);
            }else if(selectedIndex == 1){
                gc.setFill(Color.YELLOW);
                gc.fill();
                gc.fillRect(0,0,800,700);
            }else if(selectedIndex == 2){
                gc.setFill(Color.BLACK);
                gc.fill();
                gc.fillRect(0,0,800,700);
            }else if(selectedIndex == 3){
                gc.setFill(Color.WHITE);
                gc.fill();
                gc.fillRect(0,0,800,700);
            }else if(selectedIndex == 4){
                gc.setFill(Color.GRAY);
                gc.fill();
                gc.fillRect(0,0,800,700);
            }
        });

        root.getChildren().add(canvas);
        root.getChildren().add(text1);
        root.getChildren().add(line);
        root.getChildren().add(button);
        root.getChildren().add(button2);
        root.getChildren().add(button3);
        root.getChildren().add(button4);
        root.getChildren().add(input1);
        root.getChildren().add(button5);
        root.getChildren().add(bgbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void drawShapes(Line line, int angle) {
        int red;
        int green;
        int blue;
        red = (int) (Math.random() * 256);
        green = (int) (Math.random() * 256);
        blue = (int) (Math.random() * 256);
        line.setStroke(Color.rgb(red, green, blue));
        line.setStrokeWidth(10);
        line.getTransforms().add(new Rotate(angle, xf, yf));
    }
}