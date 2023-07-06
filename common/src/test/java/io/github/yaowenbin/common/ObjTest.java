package io.github.yaowenbin.common;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
/**
 * @Author yaowenbin
 * @Date 2023/7/3
 */
public class ObjTest {
    Person person;

    @BeforeEach
    public void setup() {
        person = new Person();
    }

    @Test
    public void getOrDefault_returnVal_whenValNotNull() {
        person.id(3L);

        Object result = Obj.getOrDefault(person.id(), 2L);

        assertEquals(3L, result);
    }

    @Test
    public void getOrDefault_returnVal_whenValIsNull() {

        Object result = Obj.getOrDefault(person.id(), 2L);

        assertEquals(2L, result);
    }

    @Test
    public void getOrDefaultString_returnVal_whenValIsNull() {
        person.username("");

        Object result = Obj.getOrDefault(person.username(), "yaowb");

        assertEquals("yaowb", result);
    }

    @Test
    public void testGetOrDefault2() {
        person = Mockito.mock(Person.class);
        person.id(1L);

        Object result = Obj.getOrDefault(person.id(), () -> person.no());

        verify(person, times(0)).no();
    }
}

@Data
@Accessors(fluent = true)
class Person {
    private Long id;
    private Long no;
    private String username;
}

