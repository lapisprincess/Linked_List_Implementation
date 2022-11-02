public class ThirdTest {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.add("dopey");
        list.add("dock");
        list.add("doughy");
        list.add("dorky");
        list.resetHopCount();

        for (int i = list.size() - 1; i >= 0; i--) {
            list.remove(list.size()-1);
        }
        System.out.println("Hops taken to traverse list in reverse order: " +
                           list.getHopCount());
    }
}
