/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Note;

/**
 *
 * @author valcanaia
 */
public class SoundManagerController {
    
    private static final char DO_CHAR = 'C';
    private static final char RE_CHAR = 'D';
    private static final char MI_CHAR = 'E';
    private static final char FA_CHAR = 'F';
    private static final char SOL_CHAR = 'G';
    private static final char LA_CHAR = 'A';
    private static final char SI_CHAR = 'B';

    public SoundManagerController() {
        
    }
    
    public static Note getNoteFromChar(char c){
        char cCaseInsensitive = Character.toUpperCase(c);
        Note n = new Note();
        switch(cCaseInsensitive){
            case DO_CHAR:{
                
                break;
            }
        }
        return n;
    }
    
}
