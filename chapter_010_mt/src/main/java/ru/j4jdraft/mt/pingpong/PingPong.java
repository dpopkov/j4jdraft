package ru.j4jdraft.mt.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {
    private static final int RECT_SIZE = 10;
    private static boolean interrupting = false;

    private RectangleMove rectangleMove;

    @Override
    public void start(Stage primaryStage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, RECT_SIZE, RECT_SIZE);
        group.getChildren().add(rect);
        if (interrupting) {
            rectangleMove = new RectangleMoveInterrupted(rect, limitX - RECT_SIZE, 100);
        } else {
            rectangleMove = new RectangleMoveNaive(rect, limitX - RECT_SIZE, 50);
        }
        Thread movingThread = new Thread(rectangleMove, "Moving Thread");
        movingThread.start();
        primaryStage.setScene(new Scene(group, limitX, limitY));
        primaryStage.setTitle("PingPong");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> rectangleMove.stop());
        primaryStage.show();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].startsWith("interrupt")) {
                interrupting = true;
            }
        }
        launch(args);
    }
}
