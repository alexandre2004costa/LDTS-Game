import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Enemies extends Element{

    public Enemies(int x,int y){super(x,y);}
    public void draw(TextGraphics graphics){
        char [][]prov = new char[][]{
                { ' ', '0',' ', '0',' '},
                {' ', ' ', '.', ' ', ' '},
                {' ', '|', '|', '|', ' '},
        };
        char [][]prov2 = new char[][]{
                { ' ', ' ', ' ', ' ',' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', '#', ' ', '#', ' '},
        };
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#E2000E"));
        drawTheStyle(prov,graphics);
    }
}
