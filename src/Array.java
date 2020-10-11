import java.util.Iterator;

@SuppressWarnings("unchecked")
public class Array <T> implements Iterable <T> {

    private T [] arr;
    private int len = 0;
    private int capacity = 0;

    public Array() {
        this.capacity = 10;
    }

    public Array(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size(){
        return len;
    }

    public boolean isEmpty(){
        return len == 0;
    }

    public T get(int index){
        return arr[index];
    }

    public void set(int index, T element){
        arr[index] = element;
    }

    public void clear(){
        for(int i = 0; i < arr.length; i++){
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T element){
        if(len + 1 >= capacity){
            if(capacity == 0){
                capacity = 1;
            }else{
                capacity *= 2;
            }
            T[] new_arr = (T[]) new Object[capacity * 2];
            for(int i = 0; i < len; i++){
                new_arr[i] = arr[i];
            }
            arr = new_arr;
        }
        arr[len++] = element;
    }

    public T removeAt(int index){
        if(index < 0 || index >= len){
            throw new IndexOutOfBoundsException();
        }
        T data = arr[index];
        T[] new_arr = (T[]) new Object[len-1];
        for(int i = 0,j = 0; i < len; i++, j++){
            if(i == index){
                --j;
            }else{
                new_arr[j] = arr[i];
            }
        }
        arr = new_arr;
        capacity = --len;

        return data;
    }

    public boolean remove(Object object){
        for(int i = 0; i < len; i++){
            if(arr[i] == object){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object object){
        for(int i = 0; i < len; i++){
            if(arr[i].equals(object)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object object){
        return indexOf(object) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString(){
        if(len == 0){
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(len).append('[');
        for(int i = 0; i < len - 1; i++){
            stringBuilder.append(arr[i] + ", ");
        }
        return stringBuilder.append(arr[len-1] + "]").toString();
    }

}
