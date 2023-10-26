import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    protected Position position;
    public Element(){
        position = new Position(0,0);
    }
    public Element (int x , int y){
        position = new Position(x,y);
    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public void setX(int x) {position.setX(x);}
    public void setY(int y) {position.setY(y);}
    public Position moveUp(){return new Position(position.getX(), position.getY()-1);}
    public Position moveDown(){return new Position(position.getX(), position.getY()+1);}
    public Position moveLeft(){return new Position(position.getX()-1, position.getY());}
    public Position moveRight(){return new Position(position.getX()+1, position.getY());}
    public void draw(TextGraphics graphics,String cb) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(cb));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(1, 1), ' ');
    }
    public Position getPosition() {
        return new Position(position.getX(), position.getY());
    }

}
