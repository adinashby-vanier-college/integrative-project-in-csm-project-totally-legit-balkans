package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.simulation.Input;
import edu.vanier.superspace.simulation.Tickable;

public class MousePositionTracker extends Component implements Tickable {
    /**
     * Component class for the cursor/mouse tracker. Gets the position of the camera at each tick
     * @param deltaTime 
     */
    @Override
    public void onUpdate(double deltaTime) {
        getEntity().getTransform().setPosition(Camera.getInstance().screenSpaceToWorldSpace(Input.getCurrentMouseCanvasPosition()));
    }
}
