import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public abstract class Monster extends Element{
    public String mode = "Scatter";
    protected String movingDirection = "null";
    public Monster(int x,int y){super(x,y);}
    public abstract Position target(Position position);

    public void draw(TextGraphics graphics){
        char [][]prov = new char[][]{
                { ' ', '0',' ', '0',' '},
                {' ', ' ', '.', ' ', ' '},
                {' ', '|', '|', '|', ' '},
        };
        drawTheStyle(prov,graphics);
    }
    protected double distance(Position p, Position p1){
        double difX = p1.getX()+2.5 - p.getX();
        double difY = p1.getY()+1 - p.getY();
        return Math.sqrt(difX * difX + difY * difY);
    }
    public void move(Position p,char[][]map){
        int x = this.position.getX();
        int y = this.position.getY();
        boolean t = true;
        boolean b = true;
        boolean e = true;
        boolean d = true;
        double topo = 2000000000;
        double baixo = 2000000000;
        double esq = 2000000000;
        double dir = 2000000000;
        if (map[y-1][x] == 'P'|| map[y-1][x+1] == 'P' || map[y-1][x+2] == 'P' || map[y-1][x+3] == 'P' || map[y-1][x+4] == 'P')t = false;
        if (map[y+3][x] == 'P'|| map[y+3][x+1] == 'P' || map[y+3][x+2] == 'P' || map[y+3][x+3] == 'P' || map[y+3][x+4] == 'P')b = false;
        if (map[y][x-1] == 'P'|| map[y+1][x-1] == 'P' || map[y+2][x-1] == 'P')e = false;
        if (map[y][x+5] == 'P'|| map[y+1][x+5] == 'P' || map[y+2][x+5] == 'P')d = false;
        if (e)esq = distance(new Position(x-1,y),p);
        if (d)dir = distance(new Position(x+1,y),p);
        if (b)baixo = distance(new Position(x,y+1),p);
        if (t)topo = distance(new Position(x,y-1),p);
        double minv = 20000000;
        if (d && dir <= minv && !movingDirection.equals("left"))minv = dir;
        if (b && baixo <= minv && !movingDirection.equals("up")){
            minv = baixo;
        }
        if (e && esq <= minv && !movingDirection.equals("right")){
            minv = esq;
        }
        if (t && topo <= minv && !movingDirection.equals("down")){
            minv = topo;
            position = moveUp();
            movingDirection = "up";
        }
        else if(minv == esq){
            position = moveLeft();
            movingDirection = "left";
        }
        else if(minv == baixo){
            position = moveDown();
            movingDirection = "down";
        }else{
            position = moveRight();
            movingDirection = "right";
        }




    }
}
