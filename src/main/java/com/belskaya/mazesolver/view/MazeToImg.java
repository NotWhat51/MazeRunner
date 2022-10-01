package com.belskaya.mazesolver.view;

import com.belskaya.mazesolver.model.Cell;

import java.awt.image.BufferedImage;

public class MazeToImg {
    private static final int PIXEL_SIZE = 5;
    private static final int IMAGE_TYPE = 3;

    public BufferedImage newImage(Cell[][] maze) {
        int width = maze[0].length;
        int height = maze.length;

        BufferedImage result = new BufferedImage(width * PIXEL_SIZE, height * PIXEL_SIZE, IMAGE_TYPE);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                for (int pixelX = 0; pixelX < PIXEL_SIZE; pixelX++) {
                    for (int pixelY = 0; pixelY < PIXEL_SIZE; pixelY++) {
                        result.setRGB(y * PIXEL_SIZE + pixelY, x * PIXEL_SIZE + pixelX, Cell.getColorCell(maze[x][y]).getRGB());
                    }
                }
            }
        }
        return result;
    }
}
