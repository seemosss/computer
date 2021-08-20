public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextfirst;
    private int nextlast;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextfirst = 0;
        nextlast = 1;
    }

    private void resize(int num) {
        T[] a = (T[]) new Object[num];
        int start = nextfirst + 1;
        for (int i = 0; i < size; i++) {
            start = start % items.length;
            a[i] = items[start];
            start++;
        }
        items = a;
        nextlast = size;
        nextfirst = num - 1;
    }

    public void addFirst(T item) {
        items[nextfirst] = item;
        nextfirst -= 1;
        if (nextfirst < 0) {
            nextfirst += items.length;
        }
        size += 1;
        if (size == items.length) {
            resize(2 * size);
        }
    }

    public void addLast(T item) {
        items[nextlast] = item;
        nextlast += 1;
        if (nextlast >= items.length) {
            nextlast -= items.length;
        }
        size += 1;
        if (size == items.length) {
            resize(2 * size);
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int start = nextfirst + 1;
        for(int i = 0; i < size; i++) {
            start = start % items.length;
            System.out.print(items[start] + " ");
            start++;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextfirst = (nextfirst + 1) % items.length;
        T num = items[nextfirst];
        size -= 1;
        if (items.length >= 16 && 4 * size < items.length) {
            resize(items.length / 2);
        }
        return num;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextlast = (nextlast + items.length - 1) % items.length;
        T num = items[nextlast];
        size -= 1;
        if (items.length >= 16 && 4 * size < items.length) {
            resize(items.length / 2);
        }
        return num;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[(index + nextfirst + 1) % (items.length)];
    }
}
