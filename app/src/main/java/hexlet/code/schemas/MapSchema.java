package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapSchema extends BaseSchema {

    private boolean requiredStatus = false;
    private boolean sizeofStatus = false;

    private int sizeofAmount;

    @Override
    public boolean isValid(Object testObject) {
        List<Boolean> result = new ArrayList<>();

        if (!(testObject instanceof Map<?, ?>) && !(testObject == null)) {
            return false;
        }

        result.add(requiredCheck(testObject));
        result.add(sizeofCheck(testObject));

        return !result.contains(false);
    }

    public MapSchema required() {
        this.requiredStatus = true;

        return this;
    }

    private boolean requiredCheck(Object testObject) {
        if (requiredStatus) {
            return testObject != null;
        }

        return true;
    }

    public MapSchema sizeof(int size) {
        this.sizeofAmount = size;
        this.sizeofStatus = true;

        return this;
    }

    private boolean sizeofCheck(Object testObject) {
        Map data = (Map) testObject;

        if (sizeofStatus) {
            return data.size() == this.sizeofAmount;
        }

        return true;
    }
}
