public class Test {
    public static void main(String[] args) {
        SinglyLinkedList<Double> ll = new SinglyLinkedList<>();
        ll.add(5.0);
        ll.add(10.0);
        ll.add(15.0);
        ll.add(20.0);
        ll.add(25.0);
        System.out.println(ll.getHopCount());
        System.out.println(ll.toString());

        ll.resetHopCount();
        ll.remove(1);
        System.out.println(ll.getHopCount());

        ll.reverse();
        System.out.println(ll.getHopCount());
        ll.resetHopCount();
        System.out.println(ll.toString());
        for (int i = 0; i < 4; i++) {
            ll.remove(0);
        }
        System.out.println(ll.toString());
        System.out.println(ll.getHopCount());
    }
}
