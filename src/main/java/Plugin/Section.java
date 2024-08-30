package Plugin;

public interface Section extends Reloadable{
    void enable();
    void disable();
    String getName();
}
