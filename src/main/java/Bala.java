import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bala extends Element{
    private String dir;
    public Bala(int x, int y,String d){super(x, y);dir = d;}
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#75E579"));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(1, 1), ' ');
    }
    public void move(){
        switch (dir){
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
