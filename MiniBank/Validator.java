import java.util.regex.Pattern;

public class Validator {

    private static final Pattern mobile =
            Pattern.compile("^[6-9][0-9]{9}$");

    private static final Pattern email =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern pan =
            Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");

    private static final Pattern ifsc =
            Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String value) {
        return mobile.matcher(value).matches();
    }

    public static boolean isValidEmail(String value) {
        return email.matcher(value).matches();
    }

    public static boolean isValidPan(String value) {
        return pan.matcher(value).matches();
    }

    public static boolean isValidIfsc(String value) {
        return ifsc.matcher(value).matches();
    }
}