package deque;

import java.util.Iterator;

public class LinkedListDeque<T> {
    private final ListNode sentinel;
    private int size;

    public class ListNode {
        ListNode prev;
        ListNode next;
        T item;

        public ListNode(T item, ListNode prev, ListNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        size = 0;
    }

    public void addFirst(T item) {
        ListNode newNode = new ListNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        size++;
    }

    public void addLast(T item) {
        ListNode newNode = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode cur = sentinel.next;
        while (cur.next != sentinel) {
            System.out.print(cur.item.toString() + " ");
            cur = cur.next;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        T value = sentinel.next.item;
        if (sentinel.next != sentinel) {
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
        }
        return value;
    }

    public T removeLast() {
        T value = sentinel.prev.item;
        if (sentinel.prev != sentinel) {
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size--;
        }
        return value;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        ListNode cur = sentinel.next;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        return cur.item;
    }

    public T getLast() { return sentinel.prev.item; }
    public T getFirst() { return sentinel.next.item; }

    public T getRecursive(int index) {
      if (index >= size || index < 0) {
          return null;
      }
      return _getRecursive(sentinel.next, index);
    }

    private T _getRecursive(ListNode cur, int index) {
        if (cur == sentinel || index == 0) {
            return cur.item;
        }
        return _getRecursive(cur.next, index - 1);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private ListNode it;

        public LinkedListDequeIterator() {
            it = sentinel.next;
        }

        public boolean hasNext() {
            return it.next != sentinel;
        }

        public T next() {
            T returnItem = it.item;
            it = it.next;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (! (o instanceof LinkedListDeque)) {
            return false;
        }
        if (((LinkedListDeque<?>) o).size != size){
            return false;
        }

        @SuppressWarnings("unchecked")
        ListNode o_cur = ((LinkedListDeque<T>) o).sentinel.next;
        ListNode cur = sentinel.next;

        while (o_cur != ((LinkedListDeque<?>) o).sentinel && cur != sentinel) {
            if (!cur.item.equals(o_cur.item)) {
                return false;
            }
            o_cur = o_cur.next;
            cur = cur.next;
        }

        return true;
    }

}
