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
    private Player player = new Player(35,31);
    private Enemies enemie = new Enemies(10,25);
    private String backgroundColor = "#000000";
    private int mouthFrequency = 8;
    private int enemiesMoveFrequency = 8;
    char[][] map;

    public Mapa(int w , int h) throws IOException {
        width = w;
        height = h;
        map = loadMapFromFile("map.txt");
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.setForegroundColor(TextColor.Factory.fromString("#2A0069"));
        for (int row = 0; row < 57; row++) {
            for (int col = 0; col < 91; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                if (map[row][col] == '.') {
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'P') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#385A81"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == '0') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#959043"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#CC0066"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
                }}
        graphics.fillRectangle(new TerminalPosition(81, 1), new TerminalSize(1, 1), ' ');
        player.draw(graphics);
        enemie.draw(graphics);
        if(mouthFrequency == 0){
            player.mouthOpen = !player.mouthOpen;
            mouthFrequency = 8;
        }
        mouthFrequency--;
        if(enemiesMoveFrequency == 0){
            enemie.move(player.position,map);
            enemiesMoveFrequency = 8;
        }
        enemiesMoveFrequency--;
    }

    public void readInput(KeyStroke keyStroke) {
        KeyType keyType = keyStroke.getKeyType();

        if (keyType == KeyType.ArrowRight){
            if(canMove("right"))player.move("right");
        } else if (keyType == KeyType.ArrowLeft) {
            if(canMove("left"))player.move("left");
        } else if (keyType == KeyType.ArrowUp) {
            if(canMove("up"))player.move("up");
        } else if (keyType == KeyType.ArrowDown) {
            if(canMove("down"))player.move("down");}
    }
    private boolean canMove(String direction){
        int x = player.getX();
        int y = player.getY();
        switch (direction){
            case "up":
                return map[y-1][x] != 'P'&&map[y-1][x+1] != 'P'&&map[y-1][x+2] != 'P'&&map[y-1][x+3] != 'P'&&map[y-1][x+4] != 'P';
            case "down":
                return map[y+3][x] != 'P'&&map[y+3][x+1] != 'P'&&map[y+3][x+2] != 'P'&&map[y+3][x+3] != 'P'&&map[y+3][x+4] != 'P';
            case "left":
                return map[y][x-1] != 'P'&&map[y+1][x-1] != 'P'&&map[y+2][x-1] != 'P';
            case "right":
                return map[y][x+5] != 'P'&&map[y+1][x+5] != 'P'&&map[y+2][x+5] != 'P';
        }
        return true;
    }
    public static char[][] loadMapFromFile(String filename) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int numRows = 57;
            int numCols = 91;
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
                    } else if (chars[col] == '#') {
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
