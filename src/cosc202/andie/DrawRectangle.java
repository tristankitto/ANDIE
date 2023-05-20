package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawRectangle implements ImageOperation, java.io.Serializable {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    DrawRectangle(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public BufferedImage apply(BufferedImage input) {
        Graphics2D g2d = input.createGraphics();

        if (startX > endX && startY > endY) {
            g2d.drawRect(endX + 10, endY + 10, startX - endX, startY - endY);
        }
        if (startX < endX && startY > endY) {
            g2d.drawRect(startX + 10, endY + 10, endX - startX, startY - endY);
        }
        if (startX > endX && startY < endY){
            g2d.drawRect(endX + 10, startY + 10, startX - endX, endY - startY);
        }
        g2d.drawRect(startX + 10, startY + 10, endX - startX, endY - startY);
        return input;
    }
}

