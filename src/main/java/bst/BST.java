package bst;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.KeyValuePair<K, V>> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public BST() {
        size = 0;
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return;
        }
        Node current = root;
        Node parent = null;

        while (true) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = new Node(key, value);
                    return;
                }
            } else if (cmp > 0) {
                current = current.right;
                if (current == null) {
                    parent.right = new Node(key, value);
                    return;
                }
            } else {
                current.value = value;

            }
            return;
        }
    }

    public int getSize(){
        return size;
    }

    public V get(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;

            } else {
                return current.value;
            }
        }
        return null;
    }

    public void delete(K key) {
        Node current = root;
        Node parent = null;

        while (current != null && !current.key.equals(key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            return;
        }

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else if (parent.right == current) {
                parent.right = null;
            }
        } else if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;
            if (current == root) {
                root = child;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            Node predecessor = current.left;
            Node predecessorParent = current;

            while (predecessor.right != null) {
                predecessorParent = predecessor;
                predecessor = predecessor.right;
            }
            current.key = predecessor.key;
            current.value = predecessor.value;

            if (predecessorParent.left == predecessor) {
                predecessorParent.left = predecessor.left;
            } else {
                predecessorParent.right = predecessor.left;
            }

        }


    }

    public Iterator<KeyValuePair<K,V>> iterator() {
        return new Iterator<>() {
            private Stack<Node> stack = new Stack<>();
            private Node current = root;

            {
                pushLeft(root);
            }


            private void pushLeft(Node node) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public KeyValuePair<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Node node = stack.pop();
                K key = node.key;

                if (node.right != null) {
                    pushLeft(node.right);
                }
                return new KeyValuePair<>(node.key, node.value);
            }

        };

    }

    public static class KeyValuePair<K, V>{
        private final K key;
        private final V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
