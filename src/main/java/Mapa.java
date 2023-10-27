import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.SGR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private int width;
    private int height;
    //private Nave nave = new Nave(20,20);

    private Lake lake = new Lake(0,10,8);
    private List<Bala> balas = new ArrayList<>();
    public Mapa(int w , int h){
        width = w;
        height = h;
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#A91818"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        lake.draw(graphics);
        //nave.draw(graphics);
        List<Bala> balasParaRemover = new ArrayList<>();
        for (Bala k : balas){
            if(k.getX() > width || k.getX() < 0 ||k.getY() > height || k.getY() < 0)balasParaRemover.add(k);
            k.draw(graphics);
            k.move();
        }
        balas.removeAll(balasParaRemover);
    }

    /*public void readInput(KeyStroke keyStroke) {
        KeyType keyType = keyStroke.getKeyType();

        if (keyType == KeyType.ArrowRight){
            nave.move("right");
        } else if (keyType == KeyType.ArrowLeft) {
            nave.move("left");
        } else if (keyType == KeyType.ArrowUp) {
        nave.move("up");
        } else if (keyType == KeyType.ArrowDown) {
        nave.move("down");
        } else if (keyType == KeyType.Enter){
            balas.add(new Bala(nave.getX(), nave.getY(),nave.facingDirection));
            System.out.println(balas.size());
        }
    }*/
}
