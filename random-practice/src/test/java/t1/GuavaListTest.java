package t1;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GuavaListTest {
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1, 2, 3,4,5,6);
        List<List<Integer>> partitions = Lists.partition(list, 2);

        Assert.assertEquals(partitions.size(), 3);
    }
}
