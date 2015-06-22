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
    
    private Note note;
    private Controller controller;
    private Player player;
    private Pattern pattern;
    
    // Constants
    private static final int INITIAL_OCTAVE = 5;
    private static final int VOLUME_DIFF_RATE = 500;
    private static final int VOLUME_START = 10200;
    private static final int VOLUME_MIN = 8000;
    private static final int VOLUME_MAX = 16383;
    
    private static char lastValidNotePlayed;
    
    private static boolean vol = false;


    public SoundManagerController() {
        controller = new Controller();
        player = new Player();
    }
    
    public void playText(String str) {
        String strValidChar = "ABCDEFG";
        String vogal = "IOU";
        int volume = VOLUME_START;
        int octave = INITIAL_OCTAVE;
        String oct = "";
        for (int i = 0; i < str.length(); i++) { 
            char c = str.charAt(i);
//            player.play(generatePattern(c));

            if (Character.isDigit(c)) {
                boolean isEven = (c % 2 == 0);
                if (isEven) {
                    if (octave < 9) {
                        octave++;
                    }
                } else {
                    if (octave > 1) {
                        octave--;
                    }
                }
                oct = octave + "";
            }
            System.out.println("oct: " + oct);
            c = Character.toUpperCase(c);   
            switch (c){
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
                default:
                    break;
            }
            if (vogal.indexOf(c) >= 0) {
                pattern = new Pattern(" X[Volume]=" + volume + " " + Character.toString(lastValidNotePlayed) + oct);
                player.play(pattern);
            }
            if (strValidChar.indexOf(c) >= 0) {
                pattern = new Pattern(" X[Volume]=" + volume + " " + Character.toString(lastValidNotePlayed) + oct);
                player.play(pattern);
                lastValidNotePlayed = c;
            }
        }
    }
    
        // TODO: Verificar usabilidade
    public Pattern generatePattern(char c) {
        String strValidChar = "ABCDEFG";
        String vogal = "IOU";
        int volume = VOLUME_START;
        int octave = INITIAL_OCTAVE;
        String oct = "";
        if (Character.isDigit(c)) {
            boolean isEven = (c % 2 == 0);
            if (isEven) {
                if (octave < 9) {
                    octave++;
                }
            } else {
                if (octave > 1) {
                    octave--;
                }
            }
            oct = octave + "";
        }
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
            default:
                break;
        }
        if (vogal.indexOf(c) >= 0) {
            pattern = new Pattern(" X[Volume]=" + volume + " " + Character.toString(lastValidNotePlayed) + oct);
            return pattern;
        }
        if (strValidChar.indexOf(c) >= 0) {
            pattern = new Pattern(" X[Volume]=" + volume + " " + Character.toString(lastValidNotePlayed) + oct);
            lastValidNotePlayed = c;
            return pattern;
        }

        return pattern = new Pattern("");
    }
}
