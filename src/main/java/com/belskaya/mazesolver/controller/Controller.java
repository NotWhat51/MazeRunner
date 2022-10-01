package com.belskaya.mazesolver.controller;

import com.belskaya.mazesolver.model.Cell;
import com.belskaya.mazesolver.model.Maze;
import com.belskaya.mazesolver.model.MazeSolver;
import com.belskaya.mazesolver.view.MazeToImg;
import javafx.fxml.FXML;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;
import java.util.Deque;

public class Controller {
    public Slider width;
    public Slider height;
    public ImageView img;

    private Maze maze;
    private ScrollPane scrollPane;

    @FXML
    void initialize() {
        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPannable(true);
    }

    @FXML
    protected void solveMaze() {
        MazeSolver mazeSolver = new MazeSolver();
        Deque<Cell> way = mazeSolver.solve(maze);

        maze.addWay(way);

        MazeToImg imgCreator = new MazeToImg();
        BufferedImage newImage = imgCreator.newImage(maze.getMaze());

        img.setImage(SwingFXUtils.toFXImage(newImage, null));
    }

    @FXML
    protected void newMaze() {
        maze = new Maze((int) Math.round(height.getValue()), (int) Math.round(width.getValue()));

        maze.createMaze();

        MazeToImg imgCreator = new MazeToImg();
        BufferedImage newImage = imgCreator.newImage(maze.getMaze());
        img.setImage(SwingFXUtils.toFXImage(newImage, null));

        scrollPane.setContent(img);
    }
}