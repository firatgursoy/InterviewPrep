package blobs;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 
 Problem:
 
 Pseudocode algo:
 
 */

class Solution {
  public static class Cell {
    public int row;
    public int col;
    
    public char unit;
    
    public Cell(int row, int col) {
      this.row = row;
      this.col = col;
    }
    
    @Override
    public boolean equals(Object obj) {
        Cell cell = (Cell)obj;
        
        return this.row == cell.row && this.col == cell.col;
    }
    
    public String toString() {
        return "(row: " + row + ", col: " + col + ")";
    }

    public boolean hasCholrineNeighbor(List<Cell> cholrine) {
        
        for (Cell cell : cholrine) {
            if (isTouching(cell, this)) {
                return true;
            }
        }
        
        return false;
    }
  }
  
  /* 
  * You're given a matrix of characters, x by y. Each index can have a
  * value of 'B' for bacteria, 'X' for chlorine, or '-' for 
  * empty air. A bacteria blob is the set of all connected bacteria
  * indices. The blob is 'killed' if any part of the bacteria blob
  * is touching chlorine.  For example: 
  *
  *    ALIVE:                      ALIVE:
  *   ----------                 ----------
  *   | B| B| -|                 | B| B| B|
  *   | -| B| -|                 | -| B| B|
  *   | -| -| -|                 | -| B| -| 
  *   | -| X| X|                 | X| -| X|
  *   ----------                 ----------
  *
  *
  *    DEAD:                       DEAD:
  *   ----------                 ----------  
  *   | B| B| -|                 | B| B| B|
  *   | -| B| -|                 | -| B| -|
  *   | -| X| -|                 | -| B| X|
  *   | -| X| X|                 | -| B| -|
  *   ----------                 ----------
  *
  *    DEAD:                       ALIVE:
  *   ----------                 ----------  
  *   | B| B| -|                 | B| B| B|
  *   | -| B| -|                 | -| -| X|
  *   | -| X| -|                 | -| B| X|
  *   | -| X| X|                 | -| B| -|
  *   ----------                 ----------
  *
  * Write a function(s) that determines if your matrix contains a
  * living bacteria blob.
  */
  public static boolean isAlive(char[][] sample) {
    boolean isLiving = false;
    return isLiving;
  }
  
  public static boolean isTouching(Cell first, Cell second) {
    int row = first.row;
    int col = first.col;
    
    int secondRow = second.row;
    int secondCol = second.col;
    
    if (row == secondRow && Math.abs(col - secondCol) == 1) {
      return true;
    }
    if (col == secondCol && Math.abs(row - secondRow) == 1) {
      return true;
    }
    
    return false;
  }
  
  /*
  
  */
  public static void insertIntoBlob(Cell first, List<List<Cell>> blobs) {
    boolean isAdded = false;
    
    for(List<Cell> set: blobs) {
        //if touching add to set, set isAdded to true
      
      int length = set.size();
      for(int i = 0; i < length; i++) {
        Cell cell = set.get(i);
        
        if (isTouching(first, cell) && !first.equals(cell)) {
          set.add(first);
          isAdded = true;
        }
      }
      
    }
    
    if (!isAdded) {
      List<Cell> set = new ArrayList<>();
      set.add(first);
      blobs.add(set);
    }
  }
  
  public static void setUpSample(char[][] sample) {
      sample[0][0] = 'B';
      sample[0][1] = 'B';
      sample[0][2] = 'B';
      
      sample[2][1] = 'B';
      sample[3][1] = 'B';
      sample[2][2] = 'X';
      
      // toggle to change the use case from dead to alive
      sample[1][2] = 'X';
  }
  
  public static void main(String[] args) { 
    // TODO: Your algorithm goes here
    
    int rows = 4;
    int cols = 3;
    
    char[][] sample = new char[rows][cols];
    
    for(int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            sample[i][j] = '-';
        }
    }
    
    setUpSample(sample);
    
    List<List<Cell>> blobs = new ArrayList<>();
    LinkedList<Cell> allBlobs = new LinkedList<>();
    List<Cell> cholrine = new ArrayList<>();
    
    // add all blobs
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < cols; j++) {
        char temp = sample[i][j];
        
        if (temp == 'B') {
          allBlobs.add(new Cell(i, j));
        }
        
        if (temp == 'X') {
            cholrine.add(new Cell(i, j));
        }
        
      }
    }
    
    
    while (!allBlobs.isEmpty()) {
      // get a cell
      Cell candidate = allBlobs.pollFirst();
      
      insertIntoBlob(candidate, blobs);
    }
    
    int result = 0;
    //for each set in blobs check if a nei
    
    for (List<Cell> cells : blobs) {
        for (Cell cell : cells) {
            if (cell.hasCholrineNeighbor(cholrine)) {
                result++;
            }
        }
    }
    
    if (result == blobs.size()) {
        System.out.println("DEAD");
    } else {
        System.out.println("ALIVE");
    }
        
  }
}
