package Controller;

import Model.Note;
import org.jfugue.Controller;
import org.jfugue.Pattern;
import org.jfugue.Player;

/**
 *
 * @author Henrique Valcanaia
 * @author Pedro Cantarutti
 */
public class SoundManagerController {

    private final Controller controller;
    private final Player player;
    private final String strValidChar = "ABCDEFG";
    private final String vogal = "IOU";
    private Pattern pattern;
    private Note note;
    private int volume = VOLUME_START;
    private int octave = INITIAL_OCTAVE;
    private String oct = "";

    // Constants
    private static final int INITIAL_OCTAVE = 5;
    private static final int VOLUME_DIFF_RATE = 500;
    private static final int VOLUME_START = 10200;
    private static final int VOLUME_MIN = 8000;
    private static final int VOLUME_MAX = 16383;

    private static char lastValidNotePlayed;

    public SoundManagerController() {
        controller = new Controller();
        player = new Player();
    }

    public void playText(String str) {
        String strPattern;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            strPattern = this.generateStringPattern(c);
            System.out.println(strPattern);
            if (strPattern.length() > 0) {
                player.play(new Pattern(strPattern));
            }
        }
    }

    private String generateStringPattern(char c) {
        if (Character.isDigit(c)) {
            boolean isEven = (c % 2 == 0);
            if (isEven) {
                if (this.octave < 9) {
                    this.octave++;
                }
            } else {
                if (this.octave > 1) {
                    this.octave--;
                }
            }
            this.oct = this.octave + "";
            return "";
        } else {
            System.out.println("oct: " + oct);
            c = Character.toUpperCase(c);
            switch (c) {
                case '!':
                case '.':
                case '?':
                    volume += VOLUME_DIFF_RATE * 3;
                    if (volume > VOLUME_MAX) {
                        volume = VOLUME_MAX;
                    }
                    break;
                case ',':
                case ';':
                    volume -= VOLUME_DIFF_RATE * 3;
                    if (volume < VOLUME_MIN) {
                        volume = VOLUME_MIN;
                    }
                    break;
                default: {
                    if (vogal.indexOf(c) >= 0) {
                        pattern = new Pattern(" X[Volume]=" + volume + " " + Character.toString(this.lastValidNotePlayed) + oct);
                        return pattern.toString();
                    }
                    if (strValidChar.indexOf(c) >= 0) {
                        pattern = new Pattern(" X[Volume]=" + volume + " " + Character.toString(c) + oct);
                        lastValidNotePlayed = c;
                        return pattern.toString();
                    }

                    return pattern.toString();
                }
            }
            return "";
        }
    }
}
