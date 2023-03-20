package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SchemasTest {

    @Test
    public void stringSchemaTest() {

        Validator v = new Validator();

        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        var expected = schema.required().isValid("what does the fox say");
        assertThat(expected).isTrue();

        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(5)).isFalse();
        assertThat(schema.isValid("")).isFalse();

        var expected1 = schema.contains("wh").isValid("what does the fox say"); // true
        assertThat(expected1).isTrue();
        var expected2 = schema.contains("what").isValid("what does the fox say"); // true
        assertThat(expected2).isTrue();
        var expected3 = schema.contains("whatthe").isValid("what does the fox say"); // false
        assertThat(expected3).isFalse();

        var expected4 = schema.isValid("what does the fox say"); // false
        assertThat(expected4).isFalse();

        schema = v.string();
        var expected5 = schema.minLength(8).isValid("what does the fox say");
        assertThat(expected5).isTrue();
        var expected6 = schema.isValid("what");
        assertThat(expected6).isFalse();
    }

    @Test
    public void numberSchemaTest() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.positive().isValid(null)).isTrue();

        var expected = schema.required().isValid(null);
        assertThat(expected).isFalse();

        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();

        var expected1 = schema.range(5, 10).isValid(5);
        assertThat(expected1).isTrue();

        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    public void mapSchemaTest() {

        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.required().isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap())).isTrue();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();

        assertThat(schema.sizeof(2).isValid(data)).isFalse();

        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();

    }
}
