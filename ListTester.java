public class ListTester {
    public static int sumUp(MyList<Integer> ls) {
        int sum = 0;
        for (int i = 0; i < ls.size(); i++) {
            sum += ls.get(i);
        }
        return sum;    
    }

    public static int sumDown(MyList<Integer> ls) {
        int sum = 0;
        for (int i = ls.size() - 1; i >= 0; i--) {
            sum += ls.get(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Making list...");
        SinglyLinkedList<Integer> ls = new SinglyLinkedList<Integer>();
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            ls.add(0,i);
        }
        System.out.println("Making list took " + ls.getHopCount() + " hops");

        ls.resetHopCount();
        sum = sumUp(ls);
        System.out.print("Sum up: " + sum + ", ");
        System.out.println(ls.getHopCount() + " hops");
        ls.resetHopCount();
        sum = sumDown(ls);
        System.out.print("Sum down: " + sum + ", ");
        System.out.println(ls.getHopCount() + " hops");
    }
}
