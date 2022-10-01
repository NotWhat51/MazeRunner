package com.belskaya.mazesolver.model;

import javafx.util.Pair;

import java.util.*;

public class Maze {

    private final int height;
    private final int width;
    private final Cell[][] maze;
    private Cell start;


    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Cell[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[j][i] = new Cell(j, i);
            }
        }
    }

    public void createMaze() {
        List<Pair<Cell, Cell>> frontiers = new ArrayList<>();
        Random random = new Random();
        int x = random.nextInt(width);
        int y = 1;

        Cell findEnd = new Cell(x, y);
        frontiers.add(new Pair<>(new Cell(x, y), new Cell(x, y)));
        while (!frontiers.isEmpty()) {
            Pair<Cell, Cell> way = frontiers.remove(random.nextInt(frontiers.size()));
            Cell challengerWall = way.getValue();
            if (challengerWall.isWall()) {
                challengerWall.makePass();
                way.getKey().makePass();
                frontiers.addAll(findFrontiers(challengerWall));
                findEnd = challengerWall;
            }
        }

        start = maze[x][y];
        start.makeStart();
        findEnd.makeEnd();
    }

    public List<Cell> findNeighbor(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if (x >= 1 && (maze[x - 1][y].isPass() || maze[x - 1][y].isEnd()))
            neighbors.add(maze[x - 1][y]);
        if (y >= 1 && (maze[x][y - 1].isPass() || maze[x][y - 1].isEnd()))
            neighbors.add(maze[x][y - 1]);
        if (x < width - 1 && (maze[x + 1][y].isPass() || maze[x + 1][y].isEnd()))
            neighbors.add(maze[x + 1][y]);
        if (y < height - 1 && (maze[x][y + 1].isPass() || maze[x][y + 1].isEnd()))
            neighbors.add(maze[x][y + 1]);

        return neighbors;
    }

    public Cell[][] getMaze() {
        return maze;
    }

    private List<Pair<Cell, Cell>> findFrontiers(Cell cell) {
        List<Pair<Cell, Cell>> frontiers = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if (x >= 2 && maze[x - 2][y].isWall())
            frontiers.add(new Pair<>(maze[x - 1][y], maze[x - 2][y]));
        if (y >= 2 && maze[x][y - 2].isWall())
            frontiers.add(new Pair<>(maze[x][y - 1], maze[x][y - 2]));
        if (x < width - 2 && maze[x + 2][y].isWall())
            frontiers.add(new Pair<>(maze[x + 1][y], maze[x + 2][y]));
        if (y < height - 2 && maze[x][y + 2].isWall())
            frontiers.add(new Pair<>(maze[x][y + 1], maze[x][y + 2]));

        return frontiers;
    }

    public Maze cloneMaze() {
        Maze buffMaze = new Maze(height, width);
        Cell[][] mazeClone = buffMaze.getMaze();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mazeClone[j][i] = new Cell(j, i);
                mazeClone[j][i].setValue(maze[j][i].getValue());
            }
        }

        buffMaze.start = new Cell(start.getX(), start.getY());
        buffMaze.start.makeStart();

        return buffMaze;
    }

    public void addWay(Deque<Cell> way) {
        for (Cell cell : way) {
            maze[cell.getX()][cell.getY()].setValue(cell.getValue());
        }
    }

    public Cell getStart() {
        return start;
    }

}
