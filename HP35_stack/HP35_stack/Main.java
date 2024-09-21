public class Main {
    public static void main(String[] args) {
        Stack<Integer> s = new Static<>(10);  // Fixed size stack
        Stack<Integer> d = new Dynamic<>();  // Dynamic size stack

        s.push(1);
        s.push(2);
        System.out.println("Static Stack Pop: " + s.pop());

        d.push(3);
        d.push(4);
        d.push(5);
        d.push(6);
        d.push(7);
        d.push(8);
        System.out.println("Dynamic Stack Pop: " + d.pop());
    }
}
