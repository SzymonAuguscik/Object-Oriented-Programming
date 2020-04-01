package lab5;

public class MathParser {

    static void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());

    }

    static void diffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());

    }

    static void diffConst() {
        Constant c1 = new Constant(6);
        Constant c2 = new Constant(2);
        Variable x = new Variable("x");
        Node c = new Sum().add(c1).add(c2);
        Node dx = c.diff(x);

        System.out.print("c = ");
        System.out.println(c.toString());
        System.out.print("dc/dx = ");
        System.out.println(dx.toString());
    }

    public static void main (String [] args) {
        diffPoly();
        diffCircle();
        diffConst();
    }
}
