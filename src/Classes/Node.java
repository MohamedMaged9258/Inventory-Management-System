package Classes;

public class Node {
    Node next = null;
    Object data;

    public Node(Node next, Object data) {
        this.next = next;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }
}
