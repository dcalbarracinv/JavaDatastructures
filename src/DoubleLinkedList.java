import java.util.Iterator;

public class DoubleLinkedList <T> implements Iterable<T>{

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> aux = head;
            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T data = aux.data;
                aux = aux.next;
                return data;
            }
        };
    }

    private class Node<T>{
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }

    public void clear(){
        Node<T> aux = head;
        while (aux != null){
            Node<T> next = aux.next;
            aux.prev = aux.next = null;
            aux.data = null;
            aux = next;
        }
        head = tail = aux = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void addFirst(T element){
        if(isEmpty()){
            head = tail = new Node<T>(element, null, null);
        }else{
            Node<T> new_head = new Node<T>(element, null, head);
            head.prev = new_head;
            head = new_head;
        }
        size++;
    }

    public void addLast(T element){
        if(isEmpty()){
            head = tail = new Node<T>(element, null, null);
        }else{
            Node<T> new_tail = new Node<T>(element, tail, null);
            tail.next = new_tail;
            tail = new_tail;
        }
        size++;
    }

    public void add(T element){
        addLast(element);
    }

    public T showFirst(){
        if(isEmpty()){
            throw new RuntimeException("Empty list");
        }
        return head.data;
    }

    public T showLast(){
        if(isEmpty()){
            throw new RuntimeException("Empty list");
        }
        return tail.data;
    }

    public T removeFirst(){
        if(isEmpty()){
            throw new RuntimeException("Empty list");
        }

        T data = head.data;
        head.data = null;
        head = head.next;
        --size;

        if(isEmpty()){
            tail = null;
        }else{
            head.prev = null;
        }

        return data;
    }

    public T removeLast(){
        if(isEmpty()){
            throw new RuntimeException("Empty list");
        }

        T data = tail.data;
        tail.data = null;
        tail = tail.prev;
        --size;

        if(isEmpty()){
            head = null;
        }else{
            tail.next = null;
        }

        return data;
    }

    private T remove(Node<T> node){
        if(node.next == null){
            return removeLast();
        }

        if(node.prev == null){
            return removeFirst();
        }

        T data = node.data;
        node.data = null;

        node.prev.next = node.next;
        node.next.prev = node.prev;

        node = node.prev = node.next = null;

        return data;
    }

    public T removeAt(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> aux;

        if(index < size/2){
            for(i = 0, aux = head; i != index; i++){
                aux = aux.next;
            }
        }else{
            for(i = size - 1, aux = tail; i != index; i--){
                aux = aux.prev;
            }
        }

        return remove(aux);
    }

    public boolean remove(Object object){

        Node<T> aux = head;

        if(object == null){
            for(aux = head; aux != null; aux = aux.next){
                if(aux.data == null){
                    remove(aux);
                    return true;
                }
            }
        }else{
            for(aux = head; aux != null; aux = aux.next){
                if(aux.data == object){
                    remove(aux);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object object){
        int i = 0;
        Node<T> node = head;

        if(object == null){
            for(node = head; node != null; node = node.next, i++){
                if(node.data == null){
                    return i;
                }
            }
        }else{
            for(node = head; node != null; node = node.next, i++){
                if(node.data.equals(object)){
                    return i;
                }
            }
        }

        return -1;
    }

    public boolean contains(Object object){
        return indexOf(object) != -1;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        Node<T> aux = head;

        while(aux != null){
            stringBuilder.append(aux.data + ", ");
            aux = aux.next;
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

}
