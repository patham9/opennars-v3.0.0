package nars.grid2d;

import static nars.grid2d.Hauto.*;

/**
 * GridObject with a specific position
 */
public abstract class LocalGridObject implements GridObject {

    public float cx, cy, cheading; //current drawn location, for animation
    
    public int x;
    public int y;
    public int heading; //in degrees
    public Grid2DSpace space;

    public LocalGridObject(int x, int y) {
        setPosition(x, y);
    }
    
    /** set by space when added */
    @Override
    public void init(Grid2DSpace space) {
        this.space = space;
    }
    
    public void setPosition(int x, int y) {
        this.cx = this.x = x;
        this.cy = this.y = y;
    }
    
    public int x() { return x; }
    public int y() { return y; }    
    
    
    public static int angle(int targetAngle) {
        while (targetAngle > 180) targetAngle-=360;
        while (targetAngle <= -180) targetAngle+=360;
        return targetAngle;
    }
    
    /**
     * @return 
     */
    public Cell cellRelative(int dAngle) {
        int targetAngle = angle(heading + dAngle);
        
        int tx = x;
        int ty = y;
        switch (targetAngle) {
            case UP:
                ty++;
                break;
            case DOWN:
                ty--;
                break;
            case LEFT:
                tx--;
                break;
            case RIGHT:
                tx++;
                break;
            default:
                System.err.println("cellRelative(" + dAngle + " from " + heading + ") = Invalid angle: " + targetAngle);
                return null;
        }
        return space.cells.at(tx, ty);
    }
}
