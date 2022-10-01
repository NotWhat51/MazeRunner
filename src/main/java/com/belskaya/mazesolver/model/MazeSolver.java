package com.belskaya.mazesolver.model;

import java.util.*;

public class MazeSolver {

    private static final Random RANDOM = new Random();

    public Deque<Cell> solve(Maze maze) {
        Maze mazeToSolve = maze.cloneMaze();
        Cell start = mazeToSolve.getStart();
        List<Cell> neighbors = new ArrayList<>(mazeToSolve.findNeighbor(start));
        Deque<Cell> stack = new ArrayDeque<>();
        stack.push(start);
        while (true) {
            if (!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(RANDOM.nextInt(neighbors.size()));
                if (neighbor.isEnd()) {
                    stack.push(neighbor);
                    break;
                }
                neighbor.makeVisited();
                stack.push(neighbor);
                neighbors.clear();
            } else {
                stack.pop().makeVisited();
            }
            if (stack.peek() != null) {
                neighbors.addAll(mazeToSolve.findNeighbor(stack.peek()));
            }
        }
        return stack;
    }
}
