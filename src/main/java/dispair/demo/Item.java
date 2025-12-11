package dispair.demo;

public interface Item {

    String getName();

    void useItem(Player user);

    default void useItem(Player user, Enemy target) {
        useItem(user);
    }
}