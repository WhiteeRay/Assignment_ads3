package test;

import java.util.Objects;

public class MyTestingClass {
    private String name;
    private int id;

    public MyTestingClass(String name, int id) {
        this.name = name;
        this.id = id;
    }


    @Override
    public int hashCode() {
        int result = 5;
        result = 47 * result +(name!=null ? name.hashCode(): 0);
        result = 47 * result + id;
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MyTestingClass that = (MyTestingClass) o;
        return id == that.id && Objects.equals(name, that.name);
    }
}
