package csc260.View;

import csc260.Document.Edge;
import csc260.Document.WhiteCanvasModel;


public class EdgeViewer extends EdgeFactoryViewer implements Viewer {
    Edge internal;

    public EdgeViewer(Edge E){
        super(E);
        this.internal = E;
    }

    @Override
    public void update() {
        draw(globalPane.getInstance());
    }


}
