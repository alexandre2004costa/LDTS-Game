import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Player extends Element{
    public String facingDirection;
    public boolean mouthOpen = true;
    public Player(int x, int y){super(x,y);facingDirection = "up";}
    private void drawTheStyle(char[][] pacManImage,TextGraphics graphics){
        int x = position.getX();
        int y = position.getY();
        for (int row = 0; row < pacManImage.length; row++) {
            for (int col = 0; col < pacManImage[row].length; col++) {
                if (pacManImage[row][col] != '#') {
                    graphics.fillRectangle(new TerminalPosition(x+col,y+row),new TerminalSize(1,1),pacManImage[row][col]);
                }
            }
        }
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#633CA5"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#B5D221"));
        graphics.enableModifiers(SGR.BOLD);
        char[][] pacManImage;
        char[][] pacManImageC;
        switch (facingDirection){
            case "right":
                pacManImage = new char[][]{
                        { ' ', ' ', ' ', ' ',' '},
                        {' ', ' ', ' ', '#', '#'},
                        {' ', ' ', ' ', ' ', ' '},
                };
                pacManImageC = new char[][]{
                        { ' ', ' ', ' ', ' ',' '},
                        {' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' '},
                };
                if (mouthOpen)drawTheStyle(pacManImage,graphics);
                else drawTheStyle(pacManImageC,graphics);
                break;
            case "left":
                pacManImage = new char[][]{
                        { ' ', ' ', ' ', ' ',' '},
                        {'#', '#', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' '},
                };
                pacManImageC = new char[][]{
                        { ' ', ' ', ' ', ' ',' '},
                        {' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' '},
                };
                if (mouthOpen)drawTheStyle(pacManImage,graphics);
                else drawTheStyle(pacManImageC,graphics);
                break;
            case "down":
                pacManImage = new char[][]{
                        { ' ', ' ', ' ', ' ',' '},
                        {' ', ' ', ' ', ' ', ' '},
                        {' ', '#', '#', ' ', ' '},
                };
                pacManImageC = new char[][]{
                        { ' ', ' ', ' ', ' ',' '},
                        {' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' '},
                };
                if (mouthOpen)drawTheStyle(pacManImage,graphics);
                else drawTheStyle(pacManImageC,graphics);
                break;
            case "up":
                pacManImage = new char[][]{
                        { ' ', ' ', '#', '#',' '},
                        {' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' '},
                };
                pacManImageC = new char[][]{
                        { ' ', ' ', ' ', ' ',' '},
                        {' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' '},
                };
                if (mouthOpen)drawTheStyle(pacManImage,graphics);
                else drawTheStyle(pacManImageC,graphics);
                break;
        }

    }

    public void move(String direction){
        facingDirection = direction;
        switch (direction){
            case "up":
                position = moveUp();
                break;
            case "down":
                position = moveDown();
                break;
            case "left":
                position = moveLeft();
                break;
            case "right":
                position = moveRight();
                break;
        }
    }
}
