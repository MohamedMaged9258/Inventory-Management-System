package Classes;

public class Queue {
    Node front, rear = null;

    public Queue() {

    }

    public void enqueue(Object object){
        Node node = new Node(null, object);
        if (isEmpty()){
            front = node;
            rear = node;
        }else {
            rear.next = node;
            rear = node;
        }
    }

    public Object dequeue(){
        Node node = front;
        if (isEmpty()){
            return "Queue is Empty";
        } else if (front == rear) {
            front = null;
            rear = null;
        }else {
            front = front.next;
        }
        return node;
    }

    public boolean isEmpty(){
        if (front == null && rear == null){
            return true;
        }else return false;
    }
}
