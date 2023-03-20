package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class NumberSchema {

    private boolean requiredStatus = false;
    private boolean positiveStatus = false;
    private boolean rangeStatus = false;
    private int rangeMin;
    private int rangeMax;

    public boolean isValid(Object testObject) {
        List<Boolean> result = new ArrayList<>();

        if (!(testObject instanceof Integer)) {
            return false;
        }

        result.add(requiredCheck(testObject));
        result.add(positiveCheck(testObject));
        result.add(rangeCheck(testObject));

        return !result.contains(false);
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
        final int testNumber = (int) testObject;

        if (positiveStatus) {
            return testNumber > 0;
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
        final int testNumber = (int) testObject;
        final boolean minCheck = testNumber >= rangeMin;
        final boolean maxCheck = testNumber <= rangeMax;

        if (rangeStatus) {
            return minCheck && maxCheck;
        }

        return true;
    }

}
