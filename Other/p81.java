package p81;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class p81 extends JPanel
{
    private static final int        size = 25;  //rows/columns in board
    private static final int        xStart = 0;
    private static final int        yStart = 0;
    private final gridCell[][]      cells;
    private static int              xCoord = 0; // x,y location of Rover
    private static int              yCoord = 0;
    private static enum             directions {UP, DOWN, LEFT, RIGHT};
    private static directions       direction = directions.DOWN;
    private static final int        boardSize = 1000; //size of board in pixels
    private static final int        timeDelay = 500; //in milliseconds
    private final ArrayList<directions>   backtrack = new ArrayList<>();
    private static boolean          isBacktracking = false;
    protected static class gridCell
    {
        boolean visited;
        boolean obstacle;

        public gridCell()
        {
            visited = false;
            obstacle = false;
        }
        
        public boolean isVisited()
        {
            return visited;
        }
        public void setVisited(boolean b)
        {
            visited = b;
        }
        
        public boolean isObstacle()
        {
            return obstacle;
        }
        public void setObstacle(boolean b)
        {
            obstacle = b;
        }
    }
    /*******************
     * drawing methods
     *******************/
    public void updateGrid() 
    {
        if (isBacktracking)
        {   //if we're backtracking, ignore other stuff
            goBack();
            return;
        }
        if (canGoUp() && ! upIsVisited() && ! upIsObstacle())
        {
            markVisitable(directions.UP);
            goUp();
        }
        else if (canGoRight() && ! rightIsVisited() && ! rightIsObstacle())
        {
            markVisitable(directions.RIGHT);
            goRight();
        }
        else if (canGoLeft() && ! leftIsVisited() && ! leftIsObstacle())
        {
            markVisitable(directions.LEFT);
            goLeft();
        }
        else if (canGoDown() && ! downIsVisited() && ! downIsObstacle())
        {
            markVisitable(directions.DOWN);
            goDown();
        }
        else
        {
            isBacktracking = true;
        }
    }
    
    private void goBack()
    {
        if (backtrack.isEmpty())
        {   //failed. backtracking didn't work
            System.out.println("backtracking failed");
            isBacktracking = false;
            return;
        }
        
        switch(backtrack.get(backtrack.size() - 1))
        {   //get most recent backtrack, make sure rover can go that way, and continue
            case UP:
                if (canGoUp() && ! upIsObstacle())
                {
                    goUp();
                }
                break;
            case DOWN:
                if (canGoDown()&& ! downIsObstacle())
                {
                    goDown();
                }
                break;
            case RIGHT:
                if (canGoRight() && ! rightIsObstacle())
                {
                    goRight();
                }
                break;
            case LEFT:
                if (canGoLeft() && ! leftIsObstacle())
                {
                    goLeft();
                }
                break;
        }
        
        backtrack.remove(backtrack.size() - 1);
        
        //if we backtracked and went to a visitable neighbor grid, stop backtracking and continue
        if (canGoUp() && ! upIsVisited() && ! upIsObstacle())
        {
//            markVisitable(directions.UP);
            isBacktracking = false;
//            goUp();
        }
        else if (canGoRight() && ! rightIsVisited() && ! rightIsObstacle())
        {
//            markVisitable(directions.RIGHT);
            isBacktracking = false;
            goRight();
        }
        else if (canGoLeft() && ! leftIsVisited() && ! leftIsObstacle())
        {
//            markVisitable(directions.LEFT);
            isBacktracking = false;
//            goLeft();
        }
        else if (canGoDown() && ! downIsVisited() && ! downIsObstacle())
        {
//            markVisitable(directions.DOWN);
            isBacktracking = false;
//            goDown();
        }
        
        if (backtrack.isEmpty()) 
        {
            isBacktracking = false;
        }
    }
    
    private void markVisitable(directions d)
    {
        //add the opposite direction of the rover went to the list of backtracking
        switch(d)
        {
            case UP:
                backtrack.add(directions.DOWN);
                break;
            case DOWN:
                backtrack.add(directions.UP);
                break;
            case RIGHT:
                backtrack.add(directions.LEFT);
                break;
            case LEFT:
                backtrack.add(directions.RIGHT);
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        setSize(boardSize + 2, boardSize + 2);
        int unit = boardSize / size;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        
        //draw the board rows
        for (int i = 0; i < size; i++)
        {
            g2.drawLine(0, i * unit, boardSize, i * unit);
        }
        //draw the board columns
        for (int i = 0; i < size; i++)
        {
            g2.drawLine(i * unit, 0, i * unit, boardSize);
        }
        
        //draw the last column and row
        g2.drawLine(0, boardSize, boardSize, boardSize);
        g2.drawLine(boardSize , 0, boardSize , boardSize);
        
        for (int i = 0 ; i < size; i++)
        {
            for (int j = 0 ; j < size; j++)
            {
                if (cells[i][j].isVisited())
                { //is cell was visited, make it gray
                    g2.setColor(Color.GRAY);
                    g2.fillRect((unit * i) + 2, (unit * j) + 2, unit - 3, unit - 3);
                    
                }
                if (cells[i][j].isObstacle())
                {   //if cell is an obstacle, make it black
                    g2.setColor(Color.black);
                    g2.fillRect((unit * i) + 2, (unit * j) + 2, unit - 3, unit  - 3);
                }
            }
        }        
        
        //set location of rover as red
        g2.setColor(Color.RED);
        g2.fillRect((unit * xCoord) + 2, (unit * yCoord) + 2, unit - 3, unit - 3);
    }
    
    /************************
    * direction checker functions
    ************************/
    private static boolean canGoUp()
    {
        return yCoord > 0;
    }
    private static boolean canGoLeft()
    {
        return xCoord > 0;
    }
    private static boolean canGoRight()
    {
        return xCoord < size - 1;
    }
    private static boolean canGoDown()
    {
        return yCoord < size - 1;
    }
    
    /************************
     * check if next spot is visited
    ************************/
    private  boolean upIsVisited()
    {
        return yCoord > 0 ? cells[xCoord][yCoord - 1].isVisited() : false;
    }
    private boolean leftIsVisited()
    {
        return xCoord > 0 ? cells[xCoord - 1][yCoord].isVisited() : false;
    }
    private boolean rightIsVisited()
    {
        return xCoord < size - 1 ? cells[xCoord + 1][yCoord].isVisited() : false;
    }
    private boolean downIsVisited()
    {
        return yCoord < size - 1 ? cells[xCoord][yCoord + 1].isVisited() : false;
    }
    
    
//    && !cells[xCoord][yCoord - 1].isObstacle())
    
    /************************
     * check if next spot is an obstacle
    ************************/
    private boolean upIsObstacle()
    {
        return yCoord > 0 ? cells[xCoord][yCoord - 1].isObstacle(): false;
    }
    
    private boolean rightIsObstacle()
    {
        return xCoord < size - 1 ? cells[xCoord + 1][yCoord].isObstacle() : false;
    }
    
    private boolean leftIsObstacle()
    {
        return xCoord > 0 ? cells[xCoord - 1][yCoord].isObstacle() : false;
    }
    
    private boolean downIsObstacle()
    {
        return yCoord < size - 1 ? cells[xCoord][yCoord + 1].isObstacle() : false;
    }
    
    /************************
     * moving functions
    ************************/
    private void goUp()
    {
        direction = directions.UP;
        yCoord -= 1;
        cells[xCoord][yCoord].setVisited(true);
    }

    private void goRight()
    {
        direction = directions.RIGHT;
        xCoord += 1;
        cells[xCoord][yCoord].setVisited(true);
    }

    private void goLeft()
    {
        direction = directions.LEFT;
        xCoord -= 1;
        cells[xCoord][yCoord].setVisited(true);
    }

    private void goDown()
    {
        direction = directions.DOWN;
        yCoord += 1;
        cells[xCoord][yCoord].setVisited(true);
    }
    
    //sets up JFrame
    private static void setUpFrame(JFrame frame)
    {
        frame.setSize(boardSize + 5, boardSize + 5);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getRootPane().setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
    }
    
    private static void setUpGrid(p81.gridCell[][] cells)
    {
        for (int i = 0; i < size; i++)  
        {
            for (int j = 0; j < size; j++)
            {
                cells[i][j] = new gridCell();
            }
        }
        cells[xStart][yStart].setVisited(true); //starting location is visited
        
        //setup obstacles
        Random rand = new Random();
        
        int numberOfObstacles = (int) (rand.nextInt(size / 4) + 4);
        //build random obstacle walls
        for (int i = 0; i < numberOfObstacles; i++)
        {
            int limit = rand.nextInt(size * 10);
            int point = (int) (rand.nextInt(size));
            int offset;
            
            switch((int) (rand.nextInt(4))) // choose a wall to build from: 0 = top wall, 1 = right wall, 2 = left wall, 3 = bottom wall
            {
                case 0: //top wall
                    offset = 0;
                    while (rand.nextInt(100) < limit)
                    {
                        limit -= 10; // reduce chance of another obstacle by 10%
                        
                        while(cells[offset][point].isObstacle()) // go to next cell that isn't an obstacle
                        {
                            offset++;
                            if (offset >= size) 
                            {
                                break;
                            }
                        }
                        
                        //found a free cell, now set it as an obstacle
                        if (offset + 1 < size)
                        {
                            //set it as an obstacle as long as the next point isn't an obstacle 
                            //otherwise this creates an unreachable area
                            if (!cells[offset + 1][point].isObstacle())
                            {
                                cells[point][offset].setObstacle(true);
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
                    break;

                case 1: //right wall
                    offset = size - 1; 
                    while (rand.nextInt(100) < limit)
                    {
                        limit -= 10; // reduce chance of another obstacle by 10%
                        
                        while(cells[point][offset].isObstacle()) // go to next cell that isn't an obstacle
                        {
                            offset--;
                            if (offset <= size) 
                            {
                                break;
                            }
                        }
                        
                         //found a free cell, now set it as an obstacle
                        if (offset - 1 > 0)
                        {
                            //set it as an obstacle as long as the next point isn't an obstacle 
                            //otherwise this creates an unreachable area
                            if (!cells[point][offset - 1].isObstacle())
                            {
                                cells[point][offset].setObstacle(true);
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
                    break;
                case 2: //left wall

                    offset = 0;
                    while (rand.nextInt(100) < limit)
                    {
                        limit -= 10; // reduce chance of another obstacle by 10%
                        
                        while(cells[point][offset].isObstacle()) // go to next cell that isn't an obstacle
                        {
                            offset++;
                            if (offset >= size) 
                            {
                                break;
                            }
                        }
                        
                         //found a free cell, now set it as an obstacle
                        if (offset + 1 < size)
                        {
                            //set it as an obstacle as long as the next point isn't an obstacle 
                            //otherwise this creates an unreachable area
                            if (!cells[point][offset + 1].isObstacle())
                            {
                                cells[point][offset].setObstacle(true);
                            }
                            else
                            {
                                break;
                            }
                        }
                        cells[point][offset].setObstacle(true);
                    }
                    break;
 
                case 3: //bottom wall
                    offset = size - 1;
                    while (rand.nextInt(100) < limit)
                    {
                        limit -= 10; // reduce chance of another obstacle by 10%
                         
                        while(cells[offset][point].isObstacle()) // go to next cell that isn't an obstacle
                        {
                            offset--;
                            if (offset <= size) 
                            {
                                break;
                            }
                        }
                        
                         //found a free cell, now set it as an obstacle
                        if (offset - 1 > 0)
                        {
                            //set it as an obstacle as long as the next point isn't an obstacle 
                            //otherwise this creates an unreachable area
                            
                            if (!cells[offset - 1][point].isObstacle())
                            {
                                cells[offset][point].setObstacle(true);
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
                    break;
            }
        }
    }
    
    @Override
    @Transient
    public Dimension getPreferredSize() 
    {
        return new Dimension(boardSize, boardSize);
    }

    public p81(p81.gridCell[][] cells)
    {
        this.cells = cells;
    }
    
    public boolean allVisited()
    {
        //checks to see if all locations have been visited
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (! cells[i][j].isVisited() && !cells[i][j].isObstacle()) 
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void endProgram()
    {
        backtrack.clear();
    }

    public static void main(String[] args) 
    {
        //set up grid used to represent the field
        final gridCell[][] gc = new gridCell[size][size];
        setUpGrid(gc);
        
        //setup FJrame GUI
        final p81 c = new p81(gc);
        JFrame frame = new JFrame();
        frame.getContentPane().add(c);
        setUpFrame(frame);
        
        final Timer timer = new Timer(timeDelay, new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                        //update rover position and grid every (timerDelay) milliseconds
                    c.updateGrid();
                    c.repaint();
                    if (c.allVisited())
                    {
                        //if all locations visited, we can stop
                        ((Timer)e.getSource()).stop();
                        c.endProgram();
//                        System.exit(0);
                    }
                }
            }
        );
        timer.start();
    }
}