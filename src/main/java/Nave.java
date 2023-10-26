import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Nave extends Element{
    public String facingDirection;
    public Nave(int x, int y){super(x,y);facingDirection = "up";}
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#568AC6"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C541E6"));
        graphics.enableModifiers(SGR.BOLD);
        int x = position.getX();
        int y = position.getY();
        int lado = 2;
        switch (facingDirection){
            case "up":
                graphics.fillTriangle(
                        new TerminalPosition(x, y - lado),
                        new TerminalPosition(x - (int) (lado * Math.sqrt(3) / 2), y + lado / 2),
                        new TerminalPosition(x + (int) (lado * Math.sqrt(3) / 2), y + lado / 2),
                        ' ');
                break;
            case "down":
                graphics.fillTriangle(
                        new TerminalPosition(x, y + lado),
                        new TerminalPosition(x - (int) (lado * Math.sqrt(3) / 2), y - lado / 2),
                        new TerminalPosition(x + (int) (lado * Math.sqrt(3) / 2), y - lado / 2),
                        ' '
                );
                break;
            case "left":
                graphics.fillTriangle(
                        new TerminalPosition(x - lado, y),
                        new TerminalPosition(x + lado / 2, y - (int) (lado * Math.sqrt(3) / 2)),
                        new TerminalPosition(x + lado / 2, y + (int) (lado * Math.sqrt(3) / 2)),
                        ' '
                );
                break;
            case "right":
                graphics.fillTriangle(
                        new TerminalPosition(x + lado, y),
                        new TerminalPosition(x - lado / 2, y - (int) (lado * Math.sqrt(3) / 2)),
                        new TerminalPosition(x - lado / 2, y + (int) (lado * Math.sqrt(3) / 2)),
                        ' '
                );
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
