package csc260.View;

import javafx.scene.layout.Pane;

public class globalPane {

    private static Pane pane = new Pane();
    public static Pane getInstance(){
        return pane;
    }


}
