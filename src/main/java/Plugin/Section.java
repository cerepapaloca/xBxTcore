package Plugin;

public interface Section {
    void enable();
    void disable();
    void reloadConfig();
    String getName();
}
