/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author valcanaia
 */
public class Note {

    private char note;
    
    public Note(char note) {
        // TODO: verificar onde colocar alguns teste p/ validacao
        this.note = note;
    }
    
    public char getNote()
    {
        return note;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
