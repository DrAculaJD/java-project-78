package hexlet.code.schemas;

public abstract class BaseSchema {

    public boolean isValid(Object testObject) {
        return true;
    }

    public BaseSchema required() {
        return this;
    }

}
