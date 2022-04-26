package csc260.Controller;

import csc260.Document.*;
import csc260.View.globalPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WhiteCanvasController extends Application{
    String action;
    int currentInternal = -1;
    int stateNum = 0;
    boolean coordOneSet = true;
    Point2D firstCoor;

    @FXML
    private Button redo;

    @FXML
    private Button undo;

    @FXML
//    private Pane pane = globalPane.getInstance();
    private WhiteCanvasModel internal;

    @FXML
    protected void onExit() {
        Platform.exit();
    }
    public WhiteCanvasController(){
//        pane = globalPane.getInstance();
    }

    public void setInternal(WhiteCanvasModel internal) {

        this.internal = internal;

    }

    @FXML
    public void onSaveJPG(){

        try{
            Image snapshot = globalPane.getInstance().snapshot(null,null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot,null), "jpg", new File("paint.jpg"));
            System.out.println("Done");
        }catch (Exception e){
            System.out.println("Fail to save "+ e);
        }
    }

    @FXML
    public void onSave() throws IOException {
        Stage primaryStage = new Stage();

        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Saving");

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter kae = new FileChooser.ExtensionFilter("KAE files (*.kae)", "*.kae");

        fileChooser.getExtensionFilters().add(kae);


        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        internal.save(file);

    }

    @FXML
    public void onLoad() throws IOException, ClassNotFoundException {
// read object from file

        Stage primaryStage = new Stage();

        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Loading");

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter kae = new FileChooser.ExtensionFilter("KAE files (*.kae)", "*.kae");

        fileChooser.getExtensionFilters().add(kae);

        File file = fileChooser.showOpenDialog(primaryStage);

        internal.load(file);
    }

    @FXML
    public void onSaveImages() {
        Stage primaryStage = new Stage();

        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Saving");

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");

        fileChooser.getExtensionFilters().add(jpgFilter);
        fileChooser.getExtensionFilters().add(pngFilter);


        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            int size = file.toString().length();
            String extension = file.toString().substring(size - 3, size);

            try {
                Image snapshot = globalPane.getInstance().snapshot(null, null);
                BufferedImage awtImage = new BufferedImage((int) snapshot.getWidth(), (int) snapshot.getHeight(), BufferedImage.TYPE_INT_RGB);
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, awtImage), extension, file);
                System.out.println("Done");
            } catch (Exception e) {
                System.out.println("Fail to save " + e);
            }

        }
    }

    @FXML
    public void setLightTheme(){

        globalPane.getInstance().getStylesheets().remove("CSS/darkMode.css");
        System.out.println("Intialize set Light Mode");
        globalPane.getInstance().getStylesheets().add("CSS/lightMode.css");
        System.out.println("Done set Light Mode");

    }

    @FXML
    public void setDarkTheme(){

        globalPane.getInstance().getStylesheets().remove("CSS/lightMode.css");
        System.out.println("Intialize set Dark Mode");
        globalPane.getInstance().getStylesheets().add("CSS/darkMode.css");
        System.out.println("Done set Dark Mode");

    }

    public void addClass(){
        this.action = "class";
    }

    public void addInterface(){
        this.action = "interface";
    }

    public void addNote(){
        this.action = "note";
    }

    public void addDummy(){
        this.action = "dummy";
    }



    public void addDelegationEdge(){
        action = "delegation";
    }
    public void addInheritanceEdge(){
        action = "inheritance";
    }
    public void addContainmentEdge(){
        action = "containment";
    }




    public void removeClass(){

    }

    public void classAddVar(ClassBoxNode node) {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Input Var");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();


        Label label1 = new Label("Type");
        Label label2 = new Label("Name");

        Button button1 = new Button("Submit");

        button1.setOnAction(e -> {
            String type = textField1.getText();
            String name = textField2.getText();
            String Var = type + " " + name;
            node.addInstVar(Var);
            System.out.println(node.getContent().toString());
            popupwindow.close();
        }

        );
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, textField1, label2, textField2, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    public void undoAction(){
        if (internal.hasPrev()){
            internal.undo();
            internal.notifyViewers();
            redo.setDisable(false);

            if (!internal.hasPrev()){
                undo.setDisable(true);
            }
        }
    }

    public void redoAction(){
        if (internal.hasNext()){
            internal.redo();
            internal.notifyViewers();
            undo.setDisable(false);
            if (!internal.hasNext()){
                redo.setDisable(true);
            }
        }
    }

    public void methodAddPar(Method m) {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Input Parameter");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();


        Label label1 = new Label("Type");
        Label label2 = new Label("Name");

        Button button1 = new Button("Submit");

        button1.setOnAction(e -> {
            String type = textField1.getText();
            String name = textField2.getText();
            String Var = type + " " + name;
            m.addInstVar(Var);
            System.out.println(m.getParas().toString());
            popupwindow.close();
        });
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, textField1, label2, textField2, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }



    public void classAddMethod(ClassBoxNode node){
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Input Method for class");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();

        Label label1 = new Label("Method Name");
        Label label2 = new Label("Number of Parameters");

        Button button1= new Button("Submit");

        button1.setOnAction(e -> {
            String header = textField1.getText();
            Integer numPars = Integer.valueOf(textField2.getText());
            Method toAdd = new Method(header);
            if (numPars > 0) {
                for (int i = 0; i < numPars; i++) {
                    methodAddPar(toAdd);
                }
                node.addMethod(toAdd);
                popupwindow.close();
            }else{
                node.addMethod(toAdd);
                popupwindow.close();
            }
        });
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, textField1, label2, textField2, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    public void fullClassInput(ClassBoxNode node){
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Input details for class");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();

        Label label1 = new Label("Class Name");
        Label label2 = new Label("Number of Variables:");
        Label label3 = new Label("Number of Methods");

        Button button1= new Button("Submit");

        button1.setOnAction(e ->{
                String header = textField1.getText();
                Integer numVars = Integer.valueOf(textField2.getText());
                Integer numMethods = Integer.valueOf(textField3.getText());
                node.setHeader(header);
                System.out.println(node.getContent().toString());
                if(numVars != 0){
                    for(int i=0; i<numVars; i++){
                    classAddVar(node);
                    }
                    if(numMethods != 0){
                        node.addType(NodesConfig.CLASSBOX);
                        for(int i=0; i<numMethods; i++){
                            classAddMethod(node);
                        }
                    }
                }else{
                    if(numMethods != 0){
                        node.addType(NodesConfig.HALFCLASS);
                        for(int i=0; i<numMethods; i++){
                            classAddMethod(node);
                        }
                    }else{
                        node.addType(NodesConfig.NAMECLASS);
                    }
                }
                System.out.println(node.getHeader().toString());
//                System.out.println(node.getAttributes().toString());
//                System.out.println(node.getMethods().toString());
                popupwindow.close();
            });



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, textField1, label2, textField2, label3, textField3, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

    public void interfaceInput(Interface node){
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Input details for interface");

        TextField textField1 = new TextField();


        Label label1 = new Label("Interface Name");


        Button button1= new Button("Submit");

        button1.setOnAction(e ->{
                    String header = textField1.getText();
                    node.setHeader(header);
                    System.out.println(node.getContent().toString());
                    popupwindow.close();


                }

        );



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, textField1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }


    public WhiteCanvasModel getModel(){
        return internal;
    }

    public void initialize(){

        globalPane.getInstance().setOnMouseClicked(e ->{
            System.out.println("This is also Cliccked");

            double x = e.getX();
            double y = e.getY();
            Point2D coord = new Point2D(x, y);
            if (action.equals("class")){
                ClassBoxNode newNode = new ClassBoxNode("NewNode",coord);
                fullClassInput(newNode);
                internal.addClass(newNode);
            }
            else if (action.equals("interface")){
                Interface newNode = new Interface("header", coord);
                interfaceInput(newNode);
                internal.addClass(newNode);
            }
            else if (action.equals("note")){
                TextNode newNode = new TextNode("text", coord);
                internal.addClass(newNode);

            }
            else if (action.equals("delegation")){
                if(coordOneSet){
                    firstCoor = coord;
                    coordOneSet = false;
                }else{
                    internal.addEdge(firstCoor, coord, MappingEdges.DELEGATION);
                    coordOneSet = true;
                    action = "move";
                    System.out.println("COORDINATE 2:" + firstCoor.toString());

                }
            }
            else if (action.equals("containment")){
                if(coordOneSet){
                    firstCoor = coord;
                    coordOneSet = false;
                }else{
                    internal.addEdge(firstCoor, coord, MappingEdges.CONTAINMENT);
                    coordOneSet = true;
                    action = "move";
                    System.out.println("COORDINATE 2:" + firstCoor.toString());

                }
            }
            else if (action.equals("inheritance")){
                if(coordOneSet){
                    firstCoor = coord;
                    coordOneSet = false;
                }else{
                    internal.addEdge(firstCoor, coord, MappingEdges.INHERITANCE);
                    coordOneSet = true;
                    action = "move";
                    System.out.println("COORDINATE 2:" + firstCoor.toString());

                }
            }




            if (action != "inheritance" && action != "containment" && action != "delegation"){
                action = "move";
            }
            internal.notifyViewers();

            if (internal.hasPrev()) {
                undo.setDisable(false);
            }else{
                undo.setDisable(true);
            }

            if (internal.hasNext()){
                redo.setDisable(false);
            }else{
                redo.setDisable(true);
            }

        });
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(globalPane.getInstance());
        primaryStage.setTitle("Graph");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
