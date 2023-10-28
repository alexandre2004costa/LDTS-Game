import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

import java.io.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private int width;
    private int height;
    private Player player = new Player(20,20);
    private String backgroundColor = "#28006E";
    private int mouthFrequency = 10;

    public Mapa(int w , int h){
        width = w;
        height = h;
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.setForegroundColor(TextColor.Factory.fromString("#131DAB"));
        //graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        char[][] map = loadMapFromFile("map.txt");
        for (int row = 0; row < 50; row++) {
            for (int col = 0; col < 190; col++) {
                graphics.fillRectangle(new TerminalPosition(col,row),new TerminalSize(1,1),map[row][col]);
            }
        }
        player.draw(graphics);
        if(mouthFrequency == 0){
            player.mouthOpen = !player.mouthOpen;
            mouthFrequency = 10;
        }
        mouthFrequency--;
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
            player.move("down");}
    }
    public static char[][] loadMapFromFile(String filename) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int numRows = 50;
            int numCols = 200;
            char[][] map = new char[numRows][numCols];
            int row = 0;
            boolean readingFile = true;
            while (readingFile) {
                line = reader.readLine();
                char[] chars = line.toCharArray();
                for (int col = 0; col < chars.length; col++) {
                    if (chars[col] == '!') {
                        readingFile = false;
                        break;
                    } else if (chars[col] == '|') {
                        break;
                    } else {
                        map[row][col] = chars[col];
                    }
                }
                row++;
            }
            reader.close();
            return map;
         }
}
