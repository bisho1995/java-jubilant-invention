package t1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleClassTest {
    SimpleClass simpleClass;

    @Before
    public void setUp() throws Exception {
        simpleClass = mock(SimpleClass.class);
    }

    @Test(expected = Exception.class)
    public void whenXXXThrowError(){
        when(simpleClass.getNumber()).thenThrow(Exception.class);

        simpleClass.getNumber();
    }
}