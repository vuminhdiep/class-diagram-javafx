package csc260.View;

import csc260.Controller.WhiteCanvasController;
import csc260.Document.Edge;
import csc260.Document.Node;
import csc260.Document.WhiteCanvasModel;

import java.util.Objects;
    public class WhiteCanvasViewer implements Viewer {


        private WhiteCanvasModel model;

        public WhiteCanvasViewer(WhiteCanvasModel model, WhiteCanvasController controller){
            this.model = model ;
        }




    @Override
    public void update(){
        globalPane.getInstance().getChildren().clear();
        for (Node N : model.getClasses()) {
            N.update();
        }
        for (Edge E:model.getEdgeClasses()){
            E.update();
        }
    }

    }
