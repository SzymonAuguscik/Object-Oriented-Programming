import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChoinkaTest {

    @Test
    void sortBubble() {
        Choinka a1 = new Choinka(12.12,"jodla");
        Choinka a2 = new Choinka(140.12,"sosna");
        Choinka a3 = new Choinka(92.41,"jodla");
        Choinka a4 = new Choinka(81.21, "swierk");
        Choinka a5 = new Choinka(81.12, "jodla");
        Choinka a6 = new Choinka(149.18, "swierk");

        Choinka[] d = new Choinka[] {a1,a2,a3,a4,a5,a6};
        Choinka[][] t = new Choinka[3][3];

        t = a1.sortBubble(d);

        assertEquals(t[0][0].height,92.41);
        assertEquals(t[0][1].height,81.12);
        assertEquals(t[0][2].height,12.12);
        assertEquals(t[1][0].height,149.18);
        assertEquals(t[1][1].height,81.21);
        assertEquals(t[2][0].height,140.12);
    }

    @Test
    void orderData() {
        Choinka a1 = new Choinka(12.12,"jodla");
        Choinka a2 = new Choinka(140.12,"sosna");
        Choinka a3 = new Choinka(92.41,"jodla");
        Choinka a4 = new Choinka(81.21, "swierk");
        Choinka a5 = new Choinka(81.12, "jodla");
        Choinka a6 = new Choinka(149.18, "swierk");

        Choinka[] d = new Choinka[] {a1,a2,a3,a4,a5,a6};
        Choinka[][] t = new Choinka[3][3];

        a1.orderData(t,d);

        assertEquals(t[0][0].name, "jodla");
        assertEquals(t[0][1].name, "jodla");
        assertEquals(t[0][2].name, "jodla");
        assertEquals(t[1][0].name, "swierk");
        assertEquals(t[1][1].name, "swierk");
        assertEquals(t[2][0].name, "sosna");
    }
}
