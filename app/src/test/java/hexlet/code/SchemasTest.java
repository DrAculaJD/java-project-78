package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SchemasTest {

    @Test
    public void stringSchemaTest() {

        Validator v = new Validator();

        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid("what does the fox say")).isTrue();
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
    }
}
