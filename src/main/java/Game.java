import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    public Screen screen;
    public Terminal terminal;
    private Mapa mapa = new Mapa(110,40);
    private static final int FPS = 10;
    private static final long FRAME_DURATION = 1000 / FPS;
    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(110, 40);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }
    private void draw() throws IOException {
        screen.clear();
        mapa.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException {
        long lastFrameTime = System.currentTimeMillis();
        while (true){
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastFrameTime;
            if (elapsedTime >= FRAME_DURATION) {
                draw();
                KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null){
                    KeyType keyType = keyStroke.getKeyType();
                    if (keyType == KeyType.Escape) {
                        break;
                    }
                    mapa.readInput(keyStroke);
                }
                lastFrameTime = currentTime;
            }
    }
        screen.close();
}}
