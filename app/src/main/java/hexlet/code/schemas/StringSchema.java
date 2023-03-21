package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema {

    private boolean notEmptyTest = false;
    private int minLengthTest;
    private boolean minLengtsStatus = false;
    private String containsTest;
    private boolean contaisStatus = false;

    public boolean isValid(Object testObject) {
        List<Boolean> result = new ArrayList<>();

        if (!(testObject instanceof String) && testObject != null) {
            return false;
        }

        final String testString = getTestString(testObject);

        result.add(requiredCheck(testString));
        result.add(sizeCheck(testString));
        result.add(contaisCheck(testString));

        return !result.contains(false);
    }

    private String getTestString(Object testObject) {
        if (testObject == null) {
            return "";
        }

        return (String) testObject;
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
