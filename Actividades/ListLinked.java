package Actividades;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListLinked<E> implements Iterable<E> {
	private Node<E> head;

    public ListLinked() {
        head = null;
    }

    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> aux = head;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = newNode;
        }
    }

    public boolean remove(E data) {
        if (head == null) return false;
        if (head.data.equals(data)) {
            head = head.next;
            return true;
        }

        Node<E> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean contains(E data) {
        Node<E> aux = head;
        while (aux != null) {
            if (aux.data.equals(data)) return true;
            aux = aux.next;
        }
        return false;
    }

    public E get(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Índice negativo");
        Node<E> current = head;
        int count = 0;
        while (current != null) {
            if (count == index) return current.data;
            count++;
            current = current.next;
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango");
    }

    public int size() {
        int count = 0;
        Node<E> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(head);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> aux = head;
        while (aux != null) {
            sb.append(aux.data.toString());
            sb.append(" -> ");
            aux = aux.next;
        }
        sb.append("null");
        return sb.toString();
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private static class ListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public ListIterator(Node<E> start) {
            current = start;
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E temp = current.data;
            current = current.next;
            return temp;
        }
    }
}
