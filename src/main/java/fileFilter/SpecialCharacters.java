package fileFilter;

public enum SpecialCharacters {
    NEW_LINE('\n'),

    COMMA(','),

    QUOTES('\"'),

    APOSTROPHE('\''),

    START_TAG('<'),

    CLOSE_TAG('>'),

    SLASH('/');


    private char value;

    private SpecialCharacters(char value){
        this.value = value;
    }

    public char toChar()

    {
        return this.value;
    }
}
