package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.simulation.Input;
import edu.vanier.superspace.simulation.Tickable;

public class MousePositionTracker extends Component implements Tickable {
    @Override
    public void onUpdate(double deltaTime) {
        getEntity().getTransform().setPosition(Camera.getInstance().screenSpaceToWorldSpace(Input.getCurrentMouseCanvasPosition()));
    }
}
