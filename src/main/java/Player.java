import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Player extends Element{
    public String facingDirection;
    public Player(int x, int y){super(x,y);facingDirection = "up";}
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        int x = position.getX();
        int y = position.getY();
        int lado = 2;
        switch (facingDirection){
            case "up":
                graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()),new TerminalSize(1,1),' ');
                graphics.setBackgroundColor(TextColor.Factory.fromString("#A91818"));
                graphics.setCharacter(new TerminalPosition(position.getX(), position.getY()+1),'+');
                graphics.setCharacter(new TerminalPosition(position.getX()-1, position.getY()+1),'-');
                graphics.setCharacter(new TerminalPosition(position.getX()+1, position.getY()+1),'-');
                graphics.putString(new TerminalPosition(position.getX(), position.getY()+2),"|");
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
