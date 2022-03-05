package edu.neumont.csc150.model;

import java.awt.image.BufferedImage;

public class Object {
    private BufferedImage objectImage;
    private int xPos;
    private int yPos;
    private double area;

    public BufferedImage getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(BufferedImage objectImage) {
        this.objectImage = objectImage;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Object{" +
                "objectImage=" + objectImage +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", area=" + area +
                '}';
    }
}
