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
    private final String backgroundColor = "#000000";
    private final String wallsColor = "#385A81";
    private final String coinsColor = "#959043";
    private final int mouthFrequency = 8;
    private int tempM = 0;
    private final int enemiesMoveFrequency = 8;
    private int tempMF = 0;
    private List<Monster> monsters;
    private char[][] map;

    public Mapa(int w , int h) throws IOException {
        width = w;
        height = h;
        map = loadMapFromFile("map.txt");
        monsters.add(new RedMonster(45,25));
        monsters.add(new BlueMonster(45,25));
        monsters.add(new OrangeMonster(45,25));
        monsters.add(new PinkMonster(45,25));
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.setForegroundColor(TextColor.Factory.fromString("#2A0069"));
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                if (map[row][col] == '.') {
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'P') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(wallsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == '0') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(coinsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#CC0066"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
                }}
        graphics.fillRectangle(new TerminalPosition(81, 1), new TerminalSize(1, 1), ' ');
        player.draw(graphics);
        for (Monster k : monsters)k.draw(graphics);
        if(mouthFrequency == tempM){
            player.mouthOpen = !player.mouthOpen;
            tempM = 0;
        }
        if(enemiesMoveFrequency == tempMF){
            for (Monster k : monsters)k.move(k.target(player.position),map);
            tempMF = 0;
        }
        tempM++;
        tempMF++;
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
    public char[][] loadMapFromFile(String filename) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int numRows = height;
            int numCols = width;
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
