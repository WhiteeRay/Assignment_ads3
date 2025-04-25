package test;



public class MyTestingClass {
    private int id;

    public MyTestingClass(int id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        int num = 5;
        num = 47 * num + id;
        return num;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MyTestingClass that = (MyTestingClass) o;
        return id == that.id;
    }
}
