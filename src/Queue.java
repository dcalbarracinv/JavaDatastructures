import java.util.Iterator;

public class Queue<T> implements Iterable<T>{

    private DoubleLinkedList<T> list = new DoubleLinkedList<T>();

    public Queue(){}

    public Queue(T element){
        list.addLast(element);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public T showFirst(){
        if(isEmpty()){
            throw new RuntimeException("Empty Queue");
        }
        return list.showFirst();
    }

    public T removeFirst(){
        if(isEmpty()){
            throw new RuntimeException("Empty Queue");
        }
        return list.removeFirst();
    }

    public void addBack(T element){
        list.addLast(element);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
