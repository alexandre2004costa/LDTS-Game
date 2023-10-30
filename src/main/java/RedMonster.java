import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class RedMonster extends Monster{
    public RedMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#E2000E"));
        graphics.fillRectangle(new TerminalPosition(81,1),new TerminalSize(1,1),' ');
        super.draw(graphics);
    }
    public Position target(Position position){
        if (mode.equals("Scatter"))return new Position(81,1);
        return position;
    }
}
