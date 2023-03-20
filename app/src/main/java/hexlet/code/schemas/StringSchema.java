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

        if (!(testObject instanceof String)) {
            return false;
        }

        final String testString = (String) testObject;

        result.add(requiredCheck(testString));
        result.add(sizeCheck(testString));
        result.add(contaisCheck(testString));

        return !result.contains(false);
    }

    public StringSchema required() {
        this.notEmptyTest = true;

        return this;
    }

    public StringSchema minLength(int length) {
        this.minLengthTest = length;
        this.minLengtsStatus = true;

        return this;
    }

    public StringSchema contains(String substring) {
        this.containsTest = substring;
        this.contaisStatus = true;

        return this;
    }

    private boolean requiredCheck(String testString) {
        if (notEmptyTest) {
            if (testString == null) {
                return false;
            }
            return testString.length() > 0;
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
