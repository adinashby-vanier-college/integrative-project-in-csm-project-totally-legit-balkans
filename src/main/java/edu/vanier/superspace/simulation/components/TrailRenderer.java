package edu.vanier.superspace.simulation.components;

import edu.vanier.superspace.dto.RenderLayers;
import edu.vanier.superspace.mathematics.Vector2;
import edu.vanier.superspace.simulation.Tickable;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author crist
 */
public class TrailRenderer extends Renderer implements Tickable{
    
    private ArrayList<Vector2> points = new ArrayList<>();
    private final static double distanceToNextPoint = 10;
    private boolean firstFrame = true;

    public TrailRenderer() {
        super(RenderLayers.TRAIL);
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        for(int i = 0; i < points.size()-1; i++){
            
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(10);
            gc.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
            
        }
    }

    @Override
    public Vector2 estimateSize() {
        return null;
    }

    @Override
    public void onUpdate(double deltaTime) {
        
        if (firstFrame){
            firstFrame = false;
            return;
        }
        if(points.isEmpty()){
            points.add(Vector2.copyOf(entity.getTransform().getPosition()));
            return;
        }
        
        System.out.println("D: "+points.getFirst());
        
        if(points.getLast().distanceTo(entity.getTransform().getPosition())>distanceToNextPoint){
           points.add(Vector2.copyOf(entity.getTransform().getPosition()));
            return;
        }
        
        
    }
}
