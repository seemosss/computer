public class LinkedListDeque <T> {
    private Node sentinel;
    private int size;

    public class Node{
        public T first;
        public Node prev;
        public Node next;

        public Node(T x){
            first = x;
            next = this;
            prev = this;
        }
    }

    public LinkedListDeque(){
        size = 0;
        sentinel = new Node((T)"0");
    }

    public void addFirst(T item){
        Node n = new Node(item);
        Node temp = sentinel.next;
        sentinel.next = n;
        n.prev = sentinel;
        temp.prev = n;
        n.next = temp;
        size += 1;
    }

    public void addLast(T item){
        Node n = new Node(item);
        Node temp = sentinel.prev;
        sentinel.prev = n;
        n.next = sentinel;
        temp.next = n;
        n.prev = temp;
        size += 1;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p =sentinel.next;
        while(p != sentinel){
            System.out.print(p.first + " ");
            p = p.next;
        }
    }

    public T removeFirst(){
        size -= 1;
        Node temp = sentinel.next;
        if(temp == sentinel)
            return null;
        else {
            sentinel.next = temp.next;
            temp.next.prev = sentinel;
            return temp.first;
        }
    }

    public T removeLast(){
        size -= 1;
        Node temp = sentinel.prev;
        if(temp == sentinel)
            return null;
        else {
            sentinel.prev = temp.prev;
            temp.prev.next = sentinel;
            return temp.first;
        }
    }

    public T get(int index){
        if (size < index)
            return null;
        int i = 0;
        Node temp = sentinel.next;
        while(i < index){
            temp = temp.next;
            i++;
        }
        return temp.first;
    }

    public T helper(int index, Node temp){
        if(index == 0)
            return temp.first;
        else
            return helper(index - 1, temp.next);
    }

    public T getRecursive(int index){
        if (size < index)
            return null;
        return helper(index, sentinel.next);
    }
}
