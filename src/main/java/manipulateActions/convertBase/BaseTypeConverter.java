package manipulateActions.convertBase;

public class BaseTypeConverter {
    public int convertStringToInt(String number) {
        switch (number) {
            case ("b"):
                return 2;
            case ("o"):
                return 8;
            case ("h"):
                return 16;
            default:
                throw new IllegalArgumentException("Invalid base to change");
        }
    }
}
