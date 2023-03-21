package hexlet.code.schemas;

public abstract class BaseSchema {

    public abstract boolean isValid(Object testObject);

    public abstract BaseSchema required();

}
