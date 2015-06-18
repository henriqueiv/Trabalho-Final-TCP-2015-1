/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Note;
import org.jfugue.Controller;
import org.jfugue.Pattern;
import org.jfugue.Player;

/**
 *
 * @author valcanaia
 */
public class SoundManagerController {
    
    public Note note;
    public Controller controller;
    public Player player;
    public Pattern pattern;
    public static final int INITIAL_OCTAVE = 5;
    public static final int VOLUME_DIFF_RATE = 500;
    public static final int VOLUME_START = 10200;
    public static final int VOLUME_MIN = 8000;
    public static final int VOLUME_MAX = 16383;
    public static char lastChar;
    public static char lastValidNotePlayed;
    
    private static boolean vol = false;

//    private static final char DO_CHAR = 'C';
//    private static final char RE_CHAR = 'D';
//    private static final char MI_CHAR = 'E';
//    private static final char FA_CHAR = 'F';
//    private static final char SOL_CHAR = 'G';
//    private static final char LA_CHAR = 'A';
//    private static final char SI_CHAR = 'B';

    public SoundManagerController() {
        controller = new Controller();
        player = new Player();
    }
    
    public void playText(String str)
    {
        String digitValidChar = "0123456789";
        String strValidChar = "ABCDEFG";
        String vogal = "IOU";
        //String str = taTextContent.getText();
        String oct = "";    
        int volume = VOLUME_START;
        int octave = INITIAL_OCTAVE;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
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
                pattern = new Pattern(" X[Volume]=" + volume + " " + Character.toString(c) + oct);
                player.play(pattern);
                lastValidNotePlayed = c;
            }
            lastChar = c; // TESTE   
        }
    }
    
}
