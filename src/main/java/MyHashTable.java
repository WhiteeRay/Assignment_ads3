public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] hashArray;
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

    private int hash(K key){
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K,V> head = hashArray[index];
        while(head != null){
            if(head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key,value);
        newNode.next = hashArray[index];
        hashArray[index] = newNode;
        size++;

    }

    public V get(K key) {
        int index= hash(key);
        HashNode<K, V> head = hashArray[index];

        while(head!=null){
            if(head.key.equals(key)){
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int index=  hash(key);
        HashNode<K, V> head = hashArray[index];
        HashNode<K, V> prev = null;

        while(head!=null){
            if(head.key.equals(key)){
                if(prev != null){
                    prev.next = head.next;
                } else{
                    hashArray[index] = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;

    }

    public boolean contains(V value) {
        for(int i = 0; i< M; i++){
            HashNode<K, V> head = hashArray[i];
            while(head!= null){
                if(head.value.equals(value)){
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for(int i =0 ; i< M; i++){
            HashNode<K, V> head = hashArray[i];
            while(head!=null){
                if(head.value.equals(value)){
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }


}
