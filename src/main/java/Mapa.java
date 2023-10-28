import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private int width;
    private int height;
    private Player player = new Player(20,20);
    private String backgroundColor = "#28006E";
    private List<Bala> balas = new ArrayList<>();
    public Mapa(int w , int h){
        width = w;
        height = h;
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        player.draw(graphics);
        List<Bala> balasParaRemover = new ArrayList<>();
        for (Bala k : balas){
            if(k.getX() > width || k.getX() < 0 ||k.getY() > height || k.getY() < 0)balasParaRemover.add(k);
            k.draw(graphics);
            k.move();
        }
        balas.removeAll(balasParaRemover);

    }

    public void readInput(KeyStroke keyStroke) {
        KeyType keyType = keyStroke.getKeyType();

        if (keyType == KeyType.ArrowRight){
            player.move("right");
        } else if (keyType == KeyType.ArrowLeft) {
            player.move("left");
        } else if (keyType == KeyType.ArrowUp) {
            player.move("up");
        } else if (keyType == KeyType.ArrowDown) {
            player.move("down");
        } else if (keyType == KeyType.Enter){
            balas.add(new Bala(player.getX(), player.getY(),player.facingDirection));
            System.out.println(balas.size());
        }
    }
}
