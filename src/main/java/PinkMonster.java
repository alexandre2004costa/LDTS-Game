import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PinkMonster extends Monster{
    public PinkMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#F349B5"));
        graphics.fillRectangle(new TerminalPosition(2,0),new TerminalSize(1,1),' ');
        super.draw(graphics);
    }
    public Position target(Position position){
        if (mode.equals("Scatter"))return new Position(7,3);
        return position;
    }
}
