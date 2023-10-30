import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class OrangeMonster extends Monster{
    public OrangeMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#E28914"));
        super.draw(graphics);
    }
    public Position target(Position position){
        if (mode.equals("Scatter") || distance(position,this.position)<=8)return new Position(2,56);
        return position;
    }
}
