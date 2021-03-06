import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PudelkoTest {

    @Test
    void testSortBubble() {
        Pudelko m = new Pudelko(1,2,3);
        Pudelko n = new Pudelko(1,6,10);
        Pudelko o = new Pudelko(1,1,2);
        Pudelko p = new Pudelko(3,3,3);
        Pudelko []data = new Pudelko[]{m,n,o,p};
        m.sortBubble(data);

        assertEquals(data[0].volume,2);
        assertEquals(data[1].volume,6);
        assertEquals(data[2].volume,27);
        assertEquals(data[3].volume,60);
    }

    @Test
    void testSetVolume() {
        Pudelko m = new Pudelko(1,2,3);
        Pudelko n = new Pudelko(1,6,10);
        Pudelko o = new Pudelko(1,1,2);
        Pudelko p = new Pudelko(3,3,3);

        assertEquals(m.volume,6);
        assertEquals(n.volume,60);
        assertEquals(o.volume,2);
        assertEquals(p.volume,27);
    }
}
