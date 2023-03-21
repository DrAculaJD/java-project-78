package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapSchema extends BaseSchema {

    private boolean requiredStatus = false;
    private boolean sizeofStatus = false;
    private boolean shapeStatus = false;
    private int sizeofAmount;
    private Map<?, BaseSchema> shapeMap;

    public boolean isValid(Object testObject) {
        List<Boolean> result = new ArrayList<>();

        if (!(testObject instanceof Map<?, ?>) && !(testObject == null)) {
            return false;
        }

        result.add(requiredCheck(testObject));
        result.add(sizeofCheck(testObject));
        result.add(shapeCheck(testObject));

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
        Map<?, ?> map = (Map<?, ?>) testObject;

        if (sizeofStatus) {
            return map.size() == this.sizeofAmount;
        }

        return true;
    }

    public void shape(Map<?, BaseSchema> schemas) {
        this.shapeStatus = true;
        this.shapeMap = schemas;
    }

    private boolean shapeCheck(Object testObject) {
        List<Boolean> result = new ArrayList<>();

        if (shapeStatus) {
            final Set<?> keySet = shapeMap.keySet();
            final Map<?, ?> map = (Map<?, ?>) testObject;

            for (Object key: keySet) {
                if (map.containsKey(key)) {
                    result.add(shapeMap.get(key).isValid(map.get(key)));
                }
            }

            return !result.contains(false);
        }
        return true;
    }
}
