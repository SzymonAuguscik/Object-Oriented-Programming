import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void testMatrixRowsAndCols() {
        Matrix m = new Matrix(4,5);
        assertEquals(m.shape()[0],4);
        assertEquals(m.shape()[1],5);
    }

    @Test
    void testMatrixDoubleArray() {
        Matrix m = new Matrix(new double[][] {{1,2},{1,3,4},{2,5}});
        double [][] n = m.asArray();
        for (double[] i : n)
            assertEquals(i.length,3);
        assertEquals(m.data[2],0);
        assertEquals(m.data[8],0);
    }

    @Test
    void testAsArray() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{5,6,1},{9,2,1},{-2,-3,1}});
        double[][] n = m.asArray();

        for(int i = 0; i < n.length; i++)
            for (int j = 0; j < i; j++)
                assertEquals(n[i].length,n[j].length);
    }

    @Test
    void testGet() {
        Matrix m = new Matrix(new double[][] {{1,2},{3,4}});
        assertEquals(m.get(1,1),4);
    }

    @Test
    void testSet() {
        Matrix m = new Matrix(new double[][] {{1,2},{3,4}});
        m.set(1,1,5);
        assertEquals(m.get(1,1),5);
    }

    @Test
    void testToString() {
        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double arr[][]=new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    @Test
    void testReshape() {
        Matrix m = new Matrix(new double[][]{{1,2,8},{5,6,1,2},{9,2}});
        try {
            m.reshape(2,5);
        }

        catch(Exception e) {
            assertEquals(e.getMessage(),"3 x 4 matrix can't be reshaped to 2 x 5");
        }
    }

    @Test
    void testAddMatrix() {
        Matrix m = new Matrix(new double[][] {{1,2},{3,4}});
        Matrix n = new Matrix(new double[][] {{-1,-2},{-3,-4}});
        Matrix p = m.add(n);
        assertEquals(p.frobenius(),0);
    }

    @Test
    void testSubMatrix() {
        Matrix m = new Matrix(new double[][] {{1,2},{3,4}});
        Matrix p = m.sub(m);
        assertEquals(p.frobenius(),0);
    }

    @Test
    void testMulMatrix() {
        Matrix m = new Matrix(new double[][] {{2,4},{8,16}});
        Matrix n = new Matrix(new double[][] {{0.5,0.25},{0.125,0.0625}});
        Matrix p = m.mul(n);
        assertEquals(p.frobenius(),p.cols*p.rows);
    }

    @Test
    void testDivMatrix() {
        Matrix m = new Matrix(new double[][] {{1,2},{3,4}});
        Matrix p = m.div(m);
        assertEquals(p.frobenius(),p.cols*p.rows);
    }

    @Test
    void testAddDouble() {
        Matrix m = new Matrix(new double[][] {{1,1},{1,1}});
        m = m.add(-1);
        assertEquals(m.frobenius(),0);
    }

    @Test
    void testSubDouble() {
        Matrix m = new Matrix(new double[][] {{1,1},{1,1}});
        m = m.sub(1);
        assertEquals(m.frobenius(),0);
    }

    @Test
    void testMulDouble() {
        Matrix m = new Matrix(new double[][] {{0.2,0.2},{0.2,0.2}});
        m = m.mul(5);
        assertEquals(m.frobenius(),m.cols*m.rows);
    }

    @Test
    void testDivDouble() {
        Matrix m = new Matrix(new double[][] {{5,5},{5,5}});
        m = m.div(5);
        assertEquals(m.frobenius(),m.cols*m.rows);
    }

    @Test
    void testDot() {
        Matrix m = new Matrix(new double[][] {{2,2,3},{1,0,-4}});
        Matrix n = new Matrix(new double[][] {{2,1},{3,0},{-1,1}});
        Matrix p = m.dot(n);
        assertEquals(p.data[0],7);
        assertEquals(p.data[1],5);
        assertEquals(p.data[2],6);
        assertEquals(p.data[3],-3);
    }

    @Test
    void testEye() {
        Matrix m = Matrix.eye(4);
        assertEquals(m.frobenius(),4);
    }
}
