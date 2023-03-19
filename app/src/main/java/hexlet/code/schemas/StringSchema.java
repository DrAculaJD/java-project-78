package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema {

    boolean notEmptyTest = false;
    int minLengthTest;
    boolean minLengtsStatus = false;
    String containsTest;
    boolean contaisStatus = false;

    public boolean isValid(Object testObject) {
        List<Boolean> result = new ArrayList<>();

        if (testObject instanceof Integer) {
            return false;
        }

        final String testString = (String) testObject;

        result.add(requiredCheck(testString));
        result.add(sizeCheck(testString));
        result.add(contaisCheck(testString));

        //System.out.println(result);
        return !result.contains(false);
    }

    public void required() {
        this.notEmptyTest = true;
    }

    public void minLength(int length) {
        this.minLengthTest = length;
        this.minLengtsStatus = true;
    }

    public void contains(String substring) {
        this.containsTest = substring;
        this.contaisStatus = true;
    }

    private boolean requiredCheck(String testString) {
        if (notEmptyTest) {
            if (testString ==null) {
                return false;
            }
            return testString.isEmpty();
        }

        return true;
    }

    private boolean sizeCheck(String testString) {
        if (minLengtsStatus) {
            return testString.length() >= minLengthTest;
        }

        return true;
    }

    private boolean contaisCheck(String testString) {
        if (contaisStatus) {
            return testString.contains(containsTest);
        }

        return true;
    }
}
