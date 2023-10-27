import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Road extends Object {
    //The position of this object is the top left poinf of the road
    private int height;
    public Road(int x , int y , int h){super(x,y);height = h;}

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#4A4744"));
        graphics.fillRectangle(new TerminalPosition(0, position.getY()), new TerminalSize(130, height), ' '); // the 110 is the screen total rows
        graphics.setForegroundColor(TextColor.Factory.fromString("#DBB700"));
        int i = 0;
        int ypos = position.getY()+(position.getY() - height)/2 -2;
        while (i < 130){
            graphics.setCharacter(new TerminalPosition(i, ypos), '-');
            i+=2;
        }
        graphics.setCharacter(new TerminalPosition(0, position.getY()-1),' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(0, position.getY()-1), new TerminalSize(130,1 ), ' '); // the 110 is the screen total rows
        graphics.fillRectangle(new TerminalPosition(0, position.getY()+height), new TerminalSize(130, 1), ' '); // the 110 is the screen total rows
    }
}
