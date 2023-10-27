import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Lake extends Object{
    //The position of this object is the top left poinf of the lake
    private int height;
    public Lake(int x , int y , int h){super(x,y);height = h;}

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#25E4DD"));
        graphics.fillRectangle(new TerminalPosition(0, position.getY()), new TerminalSize(130, height), ' '); // the 110 is the screen total rows
        graphics.setBackgroundColor(TextColor.Factory.fromString("#663300"));
        graphics.fillRectangle(new TerminalPosition(0, position.getY()-1), new TerminalSize(130, 1), ' '); // the 110 is the screen total rows
        graphics.fillRectangle(new TerminalPosition(0, position.getY()+height), new TerminalSize(130, 1), ' '); // the 110 is the screen total rows
    }

}
