import java.util.Random;

public class Matrix {
    public double [] data;
    public int rows;
    public int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    public Matrix(double[][] d) {
        int counter = 0;
        for (double[] i : d) {
            if (counter < i.length) {
                counter = i.length;
            }
        }

        this.rows = d.length;
        this.cols = counter;
        data = new double[this.rows*this.cols];

        for (int i = 0; i < d.length; i++) {
            int j;
            for (j = 0; j < d[i].length; j++)
            {
                this.data[j+i*counter] = d[i][j];
            }
            for (; j < counter - d[i].length; j++){
                this.data[j+i*counter] = 0;
            }
        }
    }

    public double get(int r, int c) {
        return this.data[c+r*this.cols];
    }
    public void set(int r, int c, double value) {
        data[c+r*this.cols] = value;
    }

    public double [][] asArray() {
        double [][] newdata = new double[rows][cols];
        for (int i = 0; i < this.rows; i++)
            if (this.cols >= 0)
                System.arraycopy(this.data, i * this.cols, newdata[i], 0, this.cols);
        return newdata;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i = 0; i < rows; i++){
            buf.append("[");
            for (int j = 0; j < cols; j++) {
                buf.append(data[j+i*cols]);
                if (j == cols - 1)
                    buf.append("]");
                if (!(j == cols - 1 && i == rows - 1))
                    buf.append(",");
            }
        }
        buf.append("]");
        return buf.toString();
    }

    public void print() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.data[j+i*this.cols]);
                System.out.print(" ");
            }
            System.out.print('\n');
        }
    }

    public void reshape(int newRows, int newCols) {
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        this.rows = newRows;
        this.cols = newCols;
    }

    public int [] shape() {
        int [] sh = new int[2];
        sh[0] = this.rows;
        sh[1] = this.cols;
        return sh;
    }

    public Matrix add(Matrix m) {
        Matrix n = new Matrix(this.rows, this.cols);
        if (this.rows == m.rows && this.cols == m.cols) {
            for (int i = 0; i < this.data.length; i++)
                n.data[i] = this.data[i] + m.data[i];
        }
        return n;
    }

    public Matrix sub(Matrix m) {
        Matrix n = new Matrix(this.rows, this.cols);
        if (this.rows == m.rows && this.cols == m.cols) {
            for (int i = 0; i < this.data.length; i++)
                n.data[i] = this.data[i] - m.data[i];
        }
        return n;
    }

    public Matrix mul(Matrix m) {
        Matrix n = new Matrix(this.rows, this.cols);
        if (this.rows == m.rows && this.cols == m.cols) {
            for (int i = 0; i < this.data.length; i++)
                n.data[i] = this.data[i] * m.data[i];
        }
        return n;
    }

    public Matrix div(Matrix m) {
        Matrix n = new Matrix(this.rows, this.cols);
        if (this.rows == m.rows && this.cols == m.cols) {
            for (int i = 0; i < this.data.length; i++)
                n.data[i] = this.data[i] / m.data[i];
        }
        return n;
    }

    public Matrix add(double w) {
        Matrix n = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.data.length; i++)
            n.data[i] = this.data[i] + w;
        return n;
    }

    public Matrix sub(double w) {
        Matrix n = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.data.length; i++)
            n.data[i] = this.data[i] - w;
        return n;
    }

    public Matrix mul(double w) {
        Matrix n = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.data.length; i++)
            n.data[i] = this.data[i] * w;
        return n;
    }

    public Matrix div(double w) {
        Matrix n = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.data.length; i++)
            n.data[i] = this.data[i] / w;
        return n;
    }

    public Matrix dot(Matrix m) {
        if (this.cols != m.rows) {
            System.out.print("Can't multiply matrices, incorrect sizes!"+'\n');
            return this;
        }

        else {
            Matrix n = new Matrix (this.rows,m.cols);
            for (int i = 0; i < n.rows; i++)
                for (int j = 0; j < n.cols; j++) {
                    n.set(i,j,0);
                    for (int g = 0; g < this.cols;g++)
                        n.data[j+i*n.cols] += this.data[i*this.cols+g]*m.data[g*m.cols+j];
                }
            return n;
        }
    }

    public double frobenius() {
        double d = 0;
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                d += this.get(i,j)*this.get(i,j);
        return d;
    }

    public static Matrix random(int x, int y) {
        Matrix m = new Matrix(x,y);
        Random r = new Random();
        for (int i = 0; i < m.rows; i++)
            for (int j = 0; j < m.cols; j++)
                m.set(i,j,(r.nextInt(20)-10));
        return m;
    }

    public static Matrix eye(int x) {
        Matrix n = new Matrix(x,x);
        for (int i = 0; i < n.rows; i++)
            for (int j = 0; j < n.cols; j++) {
                if (i == j)
                    n.set(i,j,1);
                else
                    n.set(i,j,0);
            }
        return n;
    }

    public static void main (String [] args){
        Matrix m = new Matrix(new double[][]{{1,2,3,4,10},{5,6,4,1},{7,8},{9,2,4}}); //manual tests

        m.print();

        System.out.print('\n');

        double [][] d = m.asArray();

        for (double[] i : d) {
            for (double j : i) {
                System.out.print(j);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print('\n');

        System.out.print(m.get(0,0));
        System.out.print('\n');
        m.set(0,0,6);
        System.out.print(m.get(0,0));
        System.out.print('\n');
        System.out.print('\n');

        System.out.print(m.toString());
        System.out.print('\n');
        System.out.print('\n');

        System.out.printf("%d %d",m.shape()[0],m.shape()[1]);
        System.out.print('\n');
        System.out.print('\n');
        m.reshape(5,4);
        m.print();
        System.out.print('\n');
        System.out.printf("%d %d",m.shape()[0],m.shape()[1]);
        System.out.print('\n');
        System.out.print('\n');

        m.reshape(4,5);
        Matrix n = new Matrix(new double[][]{{-1,-2,-3,-4,-10},{-5,-6,-4,-1},{-7,-8},{-9,-2,-4}});
        Matrix o = m.add(n);
        Matrix p = m.sub(n);
        Matrix r = m.mul(n);
        Matrix s = m.div(n);

        m.print();
        System.out.print('\n');
        n.print();
        System.out.print('\n');
        o.print();
        System.out.print('\n');
        p.print();
        System.out.print('\n');
        r.print();
        System.out.print('\n');
        s.print();
        System.out.print('\n');
        System.out.print('\n');

        m = m.add(2);
        n = n.sub(5);
        o = o.mul(2);
        p = p.div(10);

        m.print();
        System.out.print('\n');
        n.print();
        System.out.print('\n');
        o.print();
        System.out.print('\n');
        p.print();
        System.out.print('\n');
        r.print();
        System.out.print('\n');
        s.print();
        System.out.print('\n');
        System.out.print('\n');

        p = new Matrix (new double [][] {{2,2,3},{1,0,-4}});
        r = new Matrix (new double [][] {{2,1},{3,0},{-1,1}});
        s = p.dot(r);
        s.print();
        System.out.print('\n');
        System.out.print('\n');

        System.out.print(s.frobenius());
        System.out.print('\n');
        System.out.print('\n');

        s = Matrix.eye(4);
        s.print();
        System.out.print('\n');

        s = Matrix.random(2,3);
        s.print();
    }
}
