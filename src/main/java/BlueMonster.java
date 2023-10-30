import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BlueMonster extends Monster{
    public BlueMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#12E4E4"));
        super.draw(graphics);
    }
    public Position target(Position position,String direction,Position red){
        if (mode.equals("Scatter"))return new Position(82,56);
        Position middleP;
        if (direction.equals("up"))middleP =  new Position(position.getX()-2, position.getY()-2);
        else if (direction.equals("down"))middleP = new Position(position.getX(), position.getY()+2+3);
        else if (direction.equals("left"))middleP = new Position(position.getX()-2, position.getY());
        else middleP = new Position(position.getX()+2+5, position.getY());
        int Vx = red.getX() - middleP.getX();
        int Vy = red.getY() - middleP.getY();
        return new Position(middleP.getX() - Vx,middleP.getY() - Vy );
    }
}
