package t1;

import lombok.Setter;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class MyPriorityQueueTest {
    private static class Element {
        @Setter
        int id;
        Element(int id) {
            this.id = id;
        }
    }

    @Test
    public void test1() {
        PriorityQueue<Element> pq = new PriorityQueue<>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.id - o2.id;
            }
        });
        Element e1 = new Element(10);
        Element e2 = new Element(1);
        Element e3 = new Element(20);

        pq.add(e1);
        pq.add(e2);
        pq.add(e3);

        assertNotNull(pq);

        e1.setId(100);

        assertNotNull(pq);
    }

    @Test
    public void testTreeMap1() {
        TreeMap<Element, Integer> treeMap = new TreeMap<>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.id - o2.id;
            }
        });
        Element e1 = new Element(10);
        Element e2 = new Element(1);
        Element e3 = new Element(20);

        treeMap.put(e1, 1);
        treeMap.put(e2, 2);
        treeMap.put(e3, 3);

        assertNotNull(treeMap);

        treeMap.remove(e2);
        e2.setId(100);
        treeMap.put(e2,2);

        assertNotNull(treeMap);
    }
}