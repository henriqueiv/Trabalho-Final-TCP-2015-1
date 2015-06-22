package Model;

/**
 *
 * @author Henrique Valcanaia
 * @author Pedro Cantarutti
 */
public class Note {

    private char note;
    
    public Note(char note) {
        // TODO: verificar onde colocar alguns teste p/ validacao
        this.note = note;
    }
    
    public char getNote() {
        return note;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
