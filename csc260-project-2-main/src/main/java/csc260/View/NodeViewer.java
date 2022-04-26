package csc260.View;


import csc260.Document.Node;
import csc260.Document.WhiteCanvasModel;

public class NodeViewer extends NodeViewerFactory implements Viewer {
    private Node internal;

    public NodeViewer(Node N) {
        super(N);
    }

    @Override
    public void update() {
        draw(globalPane.getInstance());
    }

}
