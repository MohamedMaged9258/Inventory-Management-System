package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrderQueue {
    Queue orderQueue = new Queue();

    public OrderQueue(Queue orderQueue) {
        this.orderQueue = orderQueue;
    }
    public OrderQueue() {
    }
    public static Queue loadOrderQueue(LinkedList ordersList){
        Queue queue = new Queue();
        Node current = ordersList.getHead();
        if (current != null){
            while (current.next!=null){
                queue.enqueue(current.data);
                current = current.next;
            }
            queue.enqueue(current.data);
        }

        return queue;
    }
    public void enqueue(Order order){
        orderQueue.enqueue(order);
    }
    public LinkedList fromQueueToLinkedList(){
        Queue queue = orderQueue;
        LinkedList orderLinkedList = new LinkedList();
        Object object = queue.dequeue();
        while (object instanceof Node){
            orderLinkedList.insert(object);
            object = queue.dequeue();
        }
        return orderLinkedList;
    }
}
