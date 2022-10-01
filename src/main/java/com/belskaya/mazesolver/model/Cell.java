package com.belskaya.mazesolver.model;

import java.awt.Color;

public class Cell {

    private final int x;
    private final int y;
    private CellValue cellValue = CellValue.WALL;
    //по умолчанию все ячейки - стена

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //превроащаем стену в проход
    public void makePass() {//проход равен 0
        cellValue = CellValue.PASS;
    }

    //"Это проход?"
    public boolean isPass() {//это проход или нет
        return cellValue.equals(CellValue.PASS);
    }

    //"Я тут был"
    public void makeVisited() {//отметить что мы там были
        cellValue = CellValue.VISITED;
    }

    //"Это стена?"
    public boolean isWall() {
        return cellValue.equals(CellValue.WALL);
    }

    //Присваиваем ячейке значение старта
    public void makeStart() {//начало равно 2
        cellValue = CellValue.START;
    }

    //Присваиваем ячейке значение финиша
    public void makeEnd() {//конец равен 3
        cellValue = CellValue.END;
    }

    //"Это финиш?"
    public boolean isEnd() {//это конец или нет
        return cellValue.equals(CellValue.END);
    }

    //Раскрашиваем ячейки в соответсвие с их значениями
    public static Color getColorCell(Cell cell) {
        Color color = new Color(0, 0, 0);
        switch (cell.cellValue) {
            case WALL -> color = Color.BLACK;
            case PASS -> color = Color.WHITE;
            case START -> color = Color.ORANGE;
            case VISITED -> color = Color.RED;
            case END -> color = Color.CYAN;
        }
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellValue getValue() {
        return cellValue;
    }

    public void setValue(CellValue cellValue) {
        this.cellValue = cellValue;
    }
}
