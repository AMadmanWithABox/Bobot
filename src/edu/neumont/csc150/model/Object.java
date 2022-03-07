package edu.neumont.csc150.model;

import java.awt.image.BufferedImage;

public class Object {
    private BufferedImage objectImage;
    private int xPos;
    private int yPos;
    private double area;

    /**
     * Returns the detected item as an image
     * @return
     */
    public BufferedImage getObjectImage() {
        return objectImage;
    }

    /**
     * Sets the image of detected object
     * @param objectImage
     */
    public void setObjectImage(BufferedImage objectImage) {
        this.objectImage = objectImage;
    }

    /**
     * Gets the x-position of the detected object
     * @return
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Sets the x-position of the detected object
     * @param xPos
     */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Gets the y-position of the detected object
     * @return
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Sets the y-position of the detected object
     * @param yPos
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Gets the area of the detected object
     * @return
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets the area of the detected object
     * @param area
     */
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
