/*  MazeSolver.java

 */

import data_structures.*;

public class MazeSolver 
{
    private Queue<GridCell> queue;
    private Stack<GridCell> stack;
    private MazeGrid grid;
    private final int DIMENSION;
    
    public MazeSolver(int dimension) 
    {
        grid = new MazeGrid(this,dimension);
        queue = new Queue<GridCell>();
        stack = new Stack<GridCell>();
        DIMENSION = dimension;
    }
    
    public void mark()
    {
        queue.enqueue(grid.getCell(0, 0));
        grid.getCell(0, 0).setDistance(0);
        while(!queue.isEmpty())
        {
            GridCell cell = queue.dequeue();
            if(cell == null)
                break;
            int x = cell.getX();
            int y = cell.getY();
            GridCell up = grid.getCell(x, y-1);
            GridCell down = grid.getCell(x, y+1);
            GridCell left = grid.getCell(x-1, y);
            GridCell right = grid.getCell(x+1, y);
            int distance = cell.getDistance();
            grid.markDistance(cell);
            if(grid.isValidMove(up) && !up.wasVisited())
            {
                up.setDistance(distance+1);
                queue.enqueue(up);
            }
            if(grid.isValidMove(down) && !down.wasVisited())
            {
                down.setDistance(distance+1);
                queue.enqueue(down);
            }
            if(grid.isValidMove(left) && !left.wasVisited())
            {
                left.setDistance(distance+1);
                queue.enqueue(left);
            }
            if(grid.isValidMove(right) && !right.wasVisited())
            {
                right.setDistance(distance+1);
                queue.enqueue(right);
            }   
        }
    }
    
    public boolean move()
    {
        int distance = grid.getCell(DIMENSION-1,DIMENSION-1).getDistance();
        if(distance == -1)
            return false;
        stack.push(grid.getCell(DIMENSION-1,DIMENSION-1));
        while(distance != 0)
        {
            int min = stack.peek().getDistance();
            int x = stack.peek().getX();
            int y = stack.peek().getY();
            GridCell up = grid.getCell(x, y-1);
            GridCell down = grid.getCell(x, y+1);
            GridCell left = grid.getCell(x-1, y);
            GridCell right = grid.getCell(x+1, y);
            GridCell nextStep = up;
            if(grid.isValidMove(up))
                if(up.getDistance() < min && up.wasVisited())
                {
                    min = up.getDistance();
                    nextStep = up;
                }
            if(grid.isValidMove(down))
                if(down.getDistance() < min && down.wasVisited())
                {
                    min = down.getDistance();
                    nextStep = down;
                }
            if(grid.isValidMove(left))
                if(left.getDistance() < min && left.wasVisited())
                {
                    min = left.getDistance();
                    nextStep = left;
                }
            if(grid.isValidMove(right))
                if(right.getDistance() < min && right.wasVisited())
                    nextStep = right;
            
            distance = min;
            stack.push(nextStep);
        }
        
        while(!stack.isEmpty())
            grid.markMove(stack.pop());
        return true;
    }
    
    public void reset()
    {
        queue.makeEmpty();
        stack.makeEmpty();
    }
    
    public static void main(String[] args)
    {
        new MazeSolver(30);
    }
}
