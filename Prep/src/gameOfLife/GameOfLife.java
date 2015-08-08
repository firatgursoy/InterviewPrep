package gameOfLife;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

class Cell {
    public static final char deadChar = '=';
    public static final char aliveChar = '+';
    
    public boolean alive;
    
    public Cell(boolean alive) {
        this.alive = alive;
    }
}

public class GameOfLife {
    
    static short rows = 10;
    static short cols = 10;
    static int interval = 1000;
    static List<Cell> cells = new ArrayList<>();

    public static void main(String[] args) {
        IntStream.range(0, rows * cols).forEach(x -> cells.add(new Cell(false)));
        
        ChangeCell(new Point(3, 1), true);
        ChangeCell(new Point(4, 2), true);
        ChangeCell(new Point(4, 3), true);
        ChangeCell(new Point(3, 3), true);
        ChangeCell(new Point(2, 3), true);
        
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ArrayList<Cell> newCells = new ArrayList<>();
                
                for (Cell cell : cells) {
                    int index = cells.indexOf(cell);
                    
                    short amountTouching = 0;
                    
                    //right of cell
                    if (index + 1 < cells.size() && cells.get(index + 1).alive) {
                        amountTouching++;
                    }
                    
                    //left of cell
                    if (index - 1 > -1 && cells.get(index - 1).alive) {
                        amountTouching++;
                    }
                    
                    //below cell, add cols to index
                    if (index + cols < cells.size() && cells.get(index + cols).alive) {
                        amountTouching++;
                    }
                    
                    //above cell, subtract cols
                    if(index - cols > -1 && cells.get(index - cols).alive) {
                        amountTouching++;
                    }
                    
                    //top right cell
                    if (index + cols + 1 < cells.size() && cells.get(index + cols + 1).alive) {
                        amountTouching++;
                    }
                    
                    //top left cell
                    if (index + cols - 1 < cells.size() && cells.get(index + cols - 1).alive) {
                        amountTouching++;
                    }
                    
                    //bottom right
                    if (index - cols + 1 > -1 && cells.get(index - cols + 1).alive) {
                        amountTouching++;
                    }
                    
                    //bottom left
                    if (index - cols - 1 > -1 && cells.get(index - cols -1).alive) {
                        amountTouching++;
                    }
  
                    if (cell.alive) {
                        if (amountTouching < 2 || amountTouching > 3) {
                            newCells.add(new Cell(false));
                        } else if (amountTouching == 2 || amountTouching == 3) {
                            newCells.add(new Cell(true));
                        } 
                    } else if (!cell.alive && amountTouching == 3) {
                        newCells.add(new Cell(true));
                    }
                }
                cells = newCells;
                UpdateCells();
            }
        };
        
        timer.scheduleAtFixedRate(timerTask, 1000, interval);
    }
    
    private static void ChangeCell(Point cell, boolean alive) {
        if (cell.x < cols && cell.y < cols) {
            cells.get(cols * cell.y + cell.x).alive = alive;
        }
    }
    
    private static void UpdateCells() {
        IntStream.range(0, rows).forEach(x -> System.out.println());
        
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (cells.get(cols * y + x).alive) {
                    System.out.print(Cell.aliveChar);
                } else {
                    System.out.print(Cell.deadChar);
                }
            }
            System.out.println();
        }
    }
}
