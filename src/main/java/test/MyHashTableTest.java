package test;

import hashTable.MyHashTable;
import model.Student;

import java.util.Random;


public class MyHashTableTest
{
    public void start(){
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(100000);
            MyTestingClass key = new MyTestingClass(rand.nextInt(10000));
            Student value = new Student("Student" + i, rand.nextInt(18,40));
            table.put(key, value);
        }

        for (int i = 0; i < table.getBucketCount(); i++) {
            System.out.println("Bucket " + i + ": " + table.getBucketSize(i) + " elements");
        }
    }

}
