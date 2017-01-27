/* Class GridCell
 * A wrapper class that holds the xy coordinates, and distance from the origin
 * (0,0) of a cell in the Grid.
 */
public class GridCell {
    private int xCoordinate, yCoordinate, distanceFromOrigin;
    
    public GridCell(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
        distanceFromOrigin = -1;
    }
    
    private void setX(int x) {
        xCoordinate = x;
    }
    
    private void setY(int y) {
        yCoordinate = y;
    }
    
    public void setDistance(int d) {
        distanceFromOrigin = d;
    }
    
    public int getX() {
        return xCoordinate;
    }
    
    public int getY() {
        return yCoordinate;
    }
    
    public int getDistance() {
        return distanceFromOrigin;
    }
    
    public boolean wasVisited() {
        return distanceFromOrigin != -1;
    }
    
    public String toString() {
        return "X: " + xCoordinate + "  Y: " + yCoordinate;
    }
    
    public boolean equals(GridCell c) {
        return c.xCoordinate == xCoordinate && c.yCoordinate == yCoordinate;
    }
}
