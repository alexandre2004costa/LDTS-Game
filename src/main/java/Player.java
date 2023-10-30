import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Player extends Element{
    public String facingDirection;
    public boolean mouthOpen = true;
    private String playerColor = "#B5D221";
    char[][] pacManUp;
    char[][] pacManDown;
    char[][] pacManLeft;
    char[][] pacManRight;
    char[][] pacManClosed;
    public Player(int x, int y){
        super(x,y);
        facingDirection = "right";
        pacManUp = new char[][]{
                { ' ', ' ', '#', '#',' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
        };
        pacManDown = new char[][]{
                { ' ', ' ', ' ', ' ',' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', '#', '#', ' ', ' '},
        };
        pacManLeft = new char[][]{
                { ' ', ' ', ' ', ' ',' '},
                {'#', '#', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
        };
        pacManRight = new char[][]{
                { ' ', ' ', ' ', ' ',' '},
                {' ', ' ', ' ', '#', '#'},
                {' ', ' ', ' ', ' ', ' '},
        };
        pacManClosed = new char[][]{
                { ' ', ' ', ' ', ' ',' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
        };

    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(playerColor));
        switch (facingDirection){
            case "right":
                if (mouthOpen)drawTheStyle(pacManRight,graphics);
                else drawTheStyle(pacManClosed,graphics);
                break;
            case "left":
                if (mouthOpen)drawTheStyle(pacManLeft,graphics);
                else drawTheStyle(pacManClosed,graphics);
                break;
            case "down":
                if (mouthOpen)drawTheStyle(pacManDown,graphics);
                else drawTheStyle(pacManClosed,graphics);
                break;
            case "up":
                if (mouthOpen)drawTheStyle(pacManUp,graphics);
                else drawTheStyle(pacManClosed,graphics);
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
