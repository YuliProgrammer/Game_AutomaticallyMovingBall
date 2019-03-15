package com.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;


// 3*. Сделать чтобы шар двигался автоматически и не вылетал за пределы экрана.

public class Main extends Application {

    private final int STAGE_HEIGHT = 400;
    private final int STAGE_WIDTH = 400;


    public static Timer timer = new Timer();

    private GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Moving ball");
        Canvas canvas = new Canvas();
        canvas.setWidth(STAGE_WIDTH);
        canvas.setHeight(STAGE_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();

        gc = canvas.getGraphicsContext2D();
        draw();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                game();
            }
        };
        timer.schedule(task, 40, 40);

    }

    private int stepX = 2;
    private int stepY = 6;

    private void game() {

        if (x >= gc.getCanvas().getWidth() - 30 || x <= 0) {
            stepX = stepX * (-1);
        }

        if (y >= gc.getCanvas().getHeight() - 30 || y <= 0) {
            stepY = stepY * (-1);
        }

        x = x + stepX;
        y = y + stepY;

        clear();
        draw();
    }

    private int x = 10;
    private int y = 10;

    private void draw() {
        gc.setLineWidth(3);
        gc.setStroke(Color.FORESTGREEN);
        gc.strokeOval(x, y, 20, 20);

        gc.setFill(Color.DARKSEAGREEN);
        gc.fillOval(x, y, 20, 20);
    }

    private void clear() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

}

