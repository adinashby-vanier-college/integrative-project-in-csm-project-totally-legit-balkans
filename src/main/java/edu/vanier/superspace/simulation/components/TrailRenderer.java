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
    
    public static int thickness = 10;
    public static int skip = 0;

    public TrailRenderer() {
        super(RenderLayers.TRAIL);
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        
        int skipCount = 1;
        
        for(int i = 0; i < points.size()-1; i++){
            
            if(skipCount==skip){
                skipCount = 1;
            }else{
            
                gc.setStroke(Color.WHITE);
                gc.setLineWidth(thickness);
                gc.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
                skipCount++;
                
            }
            
            
            
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
        
        if(points.getLast().distanceTo(entity.getTransform().getPosition())>distanceToNextPoint){
           points.add(Vector2.copyOf(entity.getTransform().getPosition()));
        }
    }
}
