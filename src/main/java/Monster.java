import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

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
        double difX = p1.getX() - p.getX();
        double difY = p1.getY() - p.getY();
        return Math.sqrt(difX * difX + difY * difY);
    }
    public void move(Position p,char[][]map){
        int x = this.position.getX();
        int y = this.position.getY();
        double topo = 10000;
        double baixo = 10000;
        double esq = 10000;
        double dir = 10000;
        if (map[y][x-1] != 'P'&&map[y+1][x-1] != 'P'&&map[y+2][x-1] != 'P')esq = distance(new Position(x-1,y),p);
        if (map[y][x+5] != 'P'&&map[y+1][x+5] != 'P'&&map[y+2][x+5] != 'P')dir = distance(new Position(x+1,y),p);
        if (map[y+3][x] != 'P'&&map[y+3][x+1] != 'P'&&map[y+3][x+2] != 'P'&&map[y+3][x+3] != 'P'&&map[y+3][x+4] != 'P')baixo = distance(new Position(x,y+1),p);
        if (map[y-1][x] != 'P'&&map[y-1][x+1] != 'P'&&map[y-1][x+2] != 'P'&&map[y-1][x+3] != 'P'&&map[y-1][x+4] != 'P')topo = distance(new Position(x,y-1),p);
        if (topo == Math.min(topo,Math.min(baixo,Math.min(dir,esq))) && movingDirection != "baixo"){
            this.position = moveUp();
            movingDirection = "topo";
        }
        else if (esq == Math.min(esq,Math.min(baixo,dir))&& movingDirection != "dir"){
            this.position = moveLeft();
            movingDirection = "esq";
        }
        else if (baixo == Math.min(baixo,dir)&& movingDirection != "topo"){
            this.position = moveDown();
            movingDirection = "baixo";
        }
        else if (dir == Math.min(baixo,dir) && movingDirection != "esq"){
            this.position = moveRight();
            movingDirection = "dir";
        }else if (map[y-1][x] != 'P'&&map[y-1][x+1] != 'P'&&map[y-1][x+2] != 'P'&&map[y-1][x+3] != 'P'&&map[y-1][x+4] != 'P'){
            this.position = moveUp();
            movingDirection = "topo";
        }else if (map[y][x-1] != 'P'&&map[y+1][x-1] != 'P'&&map[y+2][x-1] != 'P'){
            this.position = moveLeft();
            movingDirection = "esq";
        } else if (map[y+3][x] != 'P'&&map[y+3][x+1] != 'P'&&map[y+3][x+2] != 'P'&&map[y+3][x+3] != 'P'&&map[y+3][x+4] != 'P') {
            this.position = moveDown();
            movingDirection = "baixo";
        }else{
            this.position = moveRight();
            movingDirection = "dir";
        }

    }
}
