import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Monster extends Element{
    public String mode = "Scatter";
    protected String movingDirection = "null";

    private boolean rotate180 = false;
    public Monster(int x,int y){super(x,y);}
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (mode.equals("fright"))graphics.setBackgroundColor(TextColor.Factory.fromString("#0013C3"));
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
    public void move(Position p,char[][]map,TextGraphics graphics,String color){
        int x = this.position.getX();
        int y = this.position.getY();
        boolean t = true;
        boolean b = true;
        boolean e = true;
        boolean d = true;
        if (map[y - 1][x] == 'P' || map[y - 1][x + 1] == 'P' || map[y - 1][x + 2] == 'P' || map[y - 1][x + 3] == 'P' || map[y - 1][x + 4] == 'P') t = false;
        if (map[y + 3][x] == 'P' || map[y + 3][x + 1] == 'P' || map[y + 3][x + 2] == 'P' || map[y + 3][x + 3] == 'P' || map[y + 3][x + 4] == 'P') b = false;
        if (map[y][x - 1] == 'P' || map[y + 1][x - 1] == 'P' || map[y + 2][x - 1] == 'P') e = false;
        if (map[y][x + 5] == 'P' || map[y + 1][x + 5] == 'P' || map[y + 2][x + 5] == 'P') d = false;
        if (mode.equals("fright")){
            if(!rotate180){
                if (movingDirection.equals("up")){
                    position = moveDown();
                    movingDirection = "down";
                }else if(movingDirection.equals("down")){
                    position = moveUp();
                    movingDirection = "up";
                }else if(movingDirection.equals("left")){
                    position = moveRight();
                    movingDirection = "right";
                }else{
                    position = moveLeft();
                    movingDirection = "left";
                }
                rotate180 = true;
            }else{
                boolean flag = true;
                while(flag){
                    Random random = new Random();
                    int randomNumber = random.nextInt(4);
                    if (randomNumber == 0 && t && !movingDirection.equals("down")){
                        position = moveUp();
                        movingDirection = "up";
                        flag = false;
                    }else if(randomNumber == 1 && b && !movingDirection.equals("up")){
                        position = moveDown();
                        movingDirection = "down";
                        flag = false;
                    }else if(randomNumber == 2 && d && !movingDirection.equals("left")){
                        position = moveRight();
                        movingDirection = "right";
                        flag = false;
                    }else if(randomNumber == 3 && e && !movingDirection.equals("right")){
                        position = moveLeft();
                        movingDirection = "left";
                        flag = false;
                    }
            }}
        }else {
            graphics.setBackgroundColor(TextColor.Factory.fromString(color));
            graphics.fillRectangle(new TerminalPosition(p.getX(), p.getY()), new TerminalSize(1, 1), ' ');
            double topo = 2000000000;
            double baixo = 2000000000;
            double esq = 2000000000;
            double dir = 2000000000;
            if (e) esq = distance(new Position(x - 1, y), p);
            if (d) dir = distance(new Position(x + 1, y), p);
            if (b) baixo = distance(new Position(x, y + 1), p);
            if (t) topo = distance(new Position(x, y - 1), p);
            double minv = 20000000;
            if (d && dir <= minv && !movingDirection.equals("left")) minv = dir;
            if (b && baixo <= minv && !movingDirection.equals("up")) {
                minv = baixo;
            }
            if (e && esq <= minv && !movingDirection.equals("right")) {
                minv = esq;
            }
            if (t && topo <= minv && !movingDirection.equals("down")) {
                minv = topo;
                position = moveUp();
                movingDirection = "up";
            } else if (minv == esq) {
                position = moveLeft();
                movingDirection = "left";
            } else if (minv == baixo) {
                position = moveDown();
                movingDirection = "down";
            } else {
                position = moveRight();
                movingDirection = "right";
            }
        }
    }
}
