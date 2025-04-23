package hashTable;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public HashNode<K, V> getNext() {
            return next;
        }


        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    HashNode<K, V>[] hashArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        hashArray = new HashNode[M];
        size = 0;
    }

    public MyHashTable(int M) {
        this.M = M;
        hashArray = new HashNode[M];
        size = 0;

    }

    public int getBucketCount() {
        return M;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }


    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> currentNode = hashArray[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                currentNode.value = value;
            }
            currentNode = currentNode.next;
        }
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = hashArray[index];
        hashArray[index] = newNode;
        size++;

    }


    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = hashArray[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }


    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> currentNode = hashArray[index];
        HashNode<K,V> prev = null;
        while(currentNode!=null){
            if(currentNode.key.equals(key)){
                if(prev!=null){
                    prev.next = currentNode.next;
                } else{
                    hashArray[index] = currentNode.next;
                }
                size--;
                return currentNode.value;
            }
            prev = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }


    public boolean contains(V value) {
        for (int i = 0; i < M; i++){
            HashNode<K, V> currentNode = hashArray[i];
            while(currentNode!=null){
                if(currentNode.value.equals(value)){
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    public K getKey(V value){
        for(int i=0; i< M; i++){
            HashNode<K, V> currentNode = hashArray[i];
            while(currentNode!=null){
                if(currentNode.value.equals(value)){
                    return currentNode.key;
                }
                currentNode = currentNode.next;
            }
        }
        return null;
    }

}
