package dispair.demo;

public abstract class LevelUpHandler {
    protected LevelUpHandler next;

    public LevelUpHandler setNext(LevelUpHandler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(Player player);
}