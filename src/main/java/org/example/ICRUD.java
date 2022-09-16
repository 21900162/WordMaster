package org.example;

public interface ICRUD {
    public Object add();
    public void update();
    public int delete(Object obj);
    public void select(int id);

}
