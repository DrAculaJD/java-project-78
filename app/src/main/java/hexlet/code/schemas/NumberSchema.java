package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class NumberSchema extends BaseSchema {

    private boolean requiredStatus = false;
    private boolean positiveStatus = false;
    private boolean rangeStatus = false;
    private int rangeMin;
    private int rangeMax;

    public boolean isValid(Object testObject) {
        List<Boolean> result = new ArrayList<>();

        if (!(testObject instanceof Integer) && !(testObject == null)) {
            return false;
        }

        result.add(requiredCheck(testObject));
        result.add(positiveCheck(testObject));
        result.add(rangeCheck(testObject));

        return !result.contains(false);
    }

    private boolean validNull() {
        return !requiredStatus;
    }

    public NumberSchema required() {
        this.requiredStatus = true;

        return this;
    }

    private boolean requiredCheck(Object testObject) {
        if (requiredStatus) {
            return testObject != null;
        }

        return true;
    }

    public NumberSchema positive() {
        this.positiveStatus = true;

        return this;
    }

    private boolean positiveCheck(Object testObject) {
        if (positiveStatus) {
            if (testObject == null) {
                return validNull();
            }
            return (int) testObject > 0;
        }

        return true;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;
        this.rangeStatus = true;

        return this;
    }

    private boolean rangeCheck(Object testObject) {

        if (rangeStatus) {
            if (testObject == null) {
                return validNull();
            }
            final int testNumber = (int) testObject;
            final boolean minCheck = testNumber >= rangeMin;
            final boolean maxCheck = testNumber <= rangeMax;
            return minCheck && maxCheck;
        }

        return true;
    }

}
