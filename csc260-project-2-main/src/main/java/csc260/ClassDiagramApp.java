package csc260;

import csc260.Controller.WhiteCanvasController;
import csc260.Document.Node;
import csc260.Document.WhiteCanvasModel;
import csc260.View.WhiteCanvasViewer;
import csc260.View.globalPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ClassDiagramApp extends Application{

    private WhiteCanvasViewer view;

    private WhiteCanvasController controller;

    private WhiteCanvasModel model;

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("whiteCanvas.fxml"));

        model = new WhiteCanvasModel(800,800);
        view = new WhiteCanvasViewer(model, controller);
        model.addViewer(view);
        BorderPane borderPane = loader.load();

        borderPane.setCenter(globalPane.getInstance());
        controller = loader.getController();
        controller.setInternal(model);



        Scene scene = new Scene(borderPane);
        stage.setTitle("csc260 project 2!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
