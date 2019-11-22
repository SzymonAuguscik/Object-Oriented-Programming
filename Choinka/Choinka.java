public class Choinka {
    double height;
    String name;

    Choinka(double h, String s) {
        this.height = h;
        this.name = s;
    }

    void orderData(Choinka[][] t, Choinka[] d) {
        int c1 = 0, c2 = 0, c3 = 0;
        for (Choinka x : d) {
            switch (x.name) {
                case "jodla":
                    t[0][c1] = x;
                    c1++;
                    break;
                case "swierk":
                    t[1][c2] = x;
                    c2++;
                    break;
                case "sosna":
                    t[2][c3] = x;
                    c3++;
                    break;
            }
        }
    }

    Choinka[][] sortBubble(Choinka[] data) {
        int c1 = 0, c2 = 0, c3 = 0;
        int max;
        for (Choinka x : data) {
            switch (x.name) {
                case "jodla":
                    c1++;
                    break;
                case "swierk":
                    c2++;
                    break;
                case "sosna":
                    c3++;
                    break;
            }
        }

        if (c1 > c2) {
            max = Math.max(c1, c3);
        }
        else {
            max = Math.max(c2, c3);
        }

        Choinka[][] table = new Choinka[3][max];

        orderData(table,data);

        for (int i = 0; i < c1; i++)
            for (int j = 0; j < i; j++)
                if (table[0][i].height > table[0][j].height) {
                    double tmp;
                    tmp = table[0][i].height;
                    table[0][i].height = table[0][j].height;
                    table[0][j].height = tmp;
                }

        for (int i = 0; i < c2; i++)
            for (int j = 0; j < i; j++)
                if (table[1][i].height > table[1][j].height) {
                    double tmp;
                    tmp = table[1][i].height;
                    table[1][i].height = table[1][j].height;
                    table[1][j].height = tmp;
                }

        for (int i = 0; i < c3; i++)
            for (int j = 0; j < i; j++)
                if (table[2][i].height > table[2][j].height) {
                    double tmp;
                    tmp = table[2][i].height;
                    table[2][i].height = table[2][j].height;
                    table[2][j].height = tmp;
                }

        return table;
    }
}
