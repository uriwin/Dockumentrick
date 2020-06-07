package manipulateActions;

public class StringEncloser implements IManipulateAction{

    private String stringToEnclose;
    private String encloseString;
    private String currentData;

    public StringEncloser(String stringToEnclose, String encloseString) {
        this.stringToEnclose = stringToEnclose;
        this.encloseString = encloseString;
        this.currentData = "";
    }

    public String manipulateDataAction(String data) {
        if (data.equals(stringToEnclose))
            return this.encloseString.concat(data).concat(this.encloseString);
        return data;
    }

    public boolean isByteRequiredForAction(int data) {
        if (currentData.length() < stringToEnclose.length()) {
            if (stringToEnclose.charAt(currentData.length()) == (char) data) {
                currentData += (char) data;
                return true;
            }
        }
        currentData = "";
        return false;
    }
}
