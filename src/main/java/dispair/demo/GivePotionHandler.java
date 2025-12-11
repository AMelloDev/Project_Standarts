package dispair.demo;

import java.util.Random;

public class GivePotionHandler extends LevelUpHandler {

    private final Random random = new Random();

    @Override
    public void handle(Player player) {
        Potion.TypePotion[] values = Potion.TypePotion.values();
        Potion.TypePotion selected = values[random.nextInt(values.length)];

        Potion potion = Potion.builder()
                .typePotion(selected)
                .build();

        player.getBag().addItem(potion);
        System.out.println("Você recebeu uma " + potion.getName());

        if (next != null)
            next.handle(player);
    }
}