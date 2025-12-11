package dispair.demo;

public class GainLifeHandler extends LevelUpHandler {

    private final int extraLife;

    public GainLifeHandler(int extraLife) {
        this.extraLife = extraLife;
    }

    @Override
    public void handle(Player player) {
        player.setLifePoints(player.getLifePoints() + extraLife);
        System.out.println("+" + extraLife + " vida!");
        if (next != null)
            next.handle(player);
    }
}