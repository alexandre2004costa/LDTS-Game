import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PinkMonster extends Monster{
    public PinkMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#F349B5"));
        super.draw(graphics);
    }
    public Position target(Position position,String direction){
        if (mode.equals("Scatter"))return new Position(7,3);
        if (direction.equals("up"))return new Position(position.getX()-4, position.getY()-4);
        if (direction.equals("down"))return new Position(position.getX(), position.getY()+4+3);
        if (direction.equals("left"))return new Position(position.getX()-4, position.getY());
        if (direction.equals("right"))return new Position(position.getX()+4+5, position.getY());
        return position;
    }
}
