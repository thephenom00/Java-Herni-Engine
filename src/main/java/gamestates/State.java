package gamestates;

import main.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Handles states
 * Changes the game state and handles audio playback
 */
public class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    /**
     * Changes the game state to the specified state and plays a certain music
     *
     * @param state state to change to
     * @param music indicates if the music is played or not
     */
    public void changeState(Gamestate state, boolean music) {
        switch (state) {
            case MENU:
                Gamestate.state = Gamestate.MENU;
                if (music) {
                    try {
                        game.getAudioController().playClip("src/main/java/audio/megalovania.wav");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                break;

            case PLAYING:
                Gamestate.state = Gamestate.PLAYING;
                if (music) {
                    try {
                        game.getAudioController().playClip("src/main/java/audio/megalovania.wav");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                break;

            case GAMEOVER:
                Gamestate.state = Gamestate.GAMEOVER;
                try {
                    game.getAudioController().playClip("src/main/java/audio/piano.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            case WIN:
                Gamestate.state = Gamestate.WIN;
                try {
                    game.getAudioController().playClip("src/main/java/audio/win.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }

}
