package gameOfLife.Two;

import java.util.Collections;
import java.util.stream.IntStream;

class Cell {
    private boolean state;
    private boolean newState;
    
    public Cell() {
        
    }
    
    public Cell(boolean state) {
        this.state = state;
    }
    
    public void setNewState(boolean state) {
        this.newState = state;
    }
    
    public void setNeighbors(int neighborCount) {
        boolean state = getState();
        
        if (state) {
            if (neighborCount < 2 || neighborCount > 3) {
                setNewState(false);
            }
            else if (neighborCount == 3 || neighborCount == 2) {
                setNewState(true);
            }
        } else if (neighborCount == 3) {
            setNewState(true);
        } else {
            setNewState(false);
        }
    }
    
    public void updateState() {
        this.state = this.newState;
    }

    public boolean getState() {
        return this.state;
    }
}

class Board {
    private Cell[][] grid;
    private int rows;
    private int cols;
    
    
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        
        grid = new Cell[rows][cols];
        IntStream.range(0, rows).forEach(x -> {
            IntStream.range(0, cols).forEach(y -> {
                grid[x][y] = new Cell();
            });
        });
    }

    public void initBoard(int row, int col, boolean state) {
        grid[row][col].setNewState(state);
        grid[row][col].updateState();
    }
    
    public void update() {
        prepareGrid();
        commitGrid();
    }
    
    private void prepareGrid() {
        IntStream.range(0, rows).forEach(x -> {
            IntStream.range(0, cols).forEach(y -> {
                int neighborCount = getNeighborCount(x, y);
                Cell cell = this.grid[x][y];
                cell.setNeighbors(neighborCount);
            });
        });
        
    }
    
    public void commitGrid() {
        IntStream.range(0, rows).forEach(x -> {
            IntStream.range(0, cols).forEach(y -> {
                this.grid[x][y].updateState();
            });
        });
    }
    
    public int getNeighborCount(int row, int col) {
        int sum = 0;
        
        // dialpad positions
        // 1 position
        if (row != 0 && col != 0) {
            if(isAlive(row - 1, col - 1)) {
                sum++;
            }
        }
        
        // 2 position
        if (row != 0) {
            if(isAlive(row - 1, col)) {
                sum++;
            }
        }
        
        // 3 position
        if (row != 0 && col != this.cols - 1) {
            if (isAlive(row -1, col + 1)) {
                sum++;
            }
        }
        
        // 4 position
        if (col != 0) {
            if (isAlive(row, col - 1)) {
                sum++;
            }
        }
        
        // 6 position
        if (col != this.cols - 1) {
            if (isAlive(row, col + 1)) {
                sum++;
            }
        }
        
        // 7 position
        if (row != this.rows - 1 && col != 0) {
            if (isAlive(row + 1, col - 1)) {
                sum++;
            }
        }
        
        // 8 position
        if (row != this.rows -1) {
            if (isAlive(row + 1, col)) {
                sum++;
            }
        }
        
        // 9 position
        if (row != this.rows -1 && col != this.cols - 1) {
            if (isAlive(row + 1, col + 1)) {
                sum++;
            }
        }
        
        
        return sum;
    }
    
    private boolean isAlive(int row, int col) {
        return grid[row][col].getState();
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public Cell[][] getGrid() {
        return grid;
    }
}

class Game {
    
}

interface Display {
    void display(Board board);
}

class ConsoleDisplay implements Display {
    @Override
    public void display(Board board) {
        Cell[][] grid = board.getGrid();
        
        String border = String.format("+%0" + 2*grid.length + "d+", 0).replace("0","-");
        
        System.out.println(border);
        
        for (Cell[] row : grid) {
            String r = "|";
            for (Cell c : row) {
                r += c.getState() ? "* " : "  ";
            }
            r += "|";
            System.out.println(r);
        }
        
        System.out.println(border);
    }
}

public class GameOfLifeSolution {
    public static void main(String[] args) throws InterruptedException {
        int iterations = 1000;
        
        Display display = new ConsoleDisplay();
        Board board = new Board(10, 10);
        
        board.initBoard(3, 1, true);
        board.initBoard(4, 2, true);
        board.initBoard(4, 3, true);
        board.initBoard(3, 3, true);
        board.initBoard(2, 3, true);
        
        for(int i = 0; i < iterations; i++) {
            display.display(board);
            board.update();
            
            Thread.sleep(1000);
        }
    }
}
