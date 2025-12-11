package dispair.demo;

import java.util.Random;

public class UpgradeWeaponHandler extends LevelUpHandler {

    private final Random random = new Random();

    @Override
    public void handle(Player player) {
        Weapon old = player.getWeapon();
        int base = (old != null) ? old.getDamage() : 2;

        Weapon newWeapon = switch (player.getPlayerClass()) {
            case Guerreiro -> Weapon.builder()
                    .typeWeapon(random.nextBoolean() ? Weapon.TypeWeapon.espada : Weapon.TypeWeapon.lança)
                    .damage(base + random.nextInt(4) + 1)
                    .build();
            case Mago -> Weapon.builder()
                    .typeWeapon(random.nextBoolean() ? Weapon.TypeWeapon.cajado : Weapon.TypeWeapon.livro)
                    .magicTypeDamage(
                            Weapon.MagicTypeDamage.values()[random.nextInt(Weapon.MagicTypeDamage.values().length)])
                    .damage(base + random.nextInt(6) + 2)
                    .build();
            case Arqueiro -> Weapon.builder()
                    .typeWeapon(random.nextBoolean() ? Weapon.TypeWeapon.arco : Weapon.TypeWeapon.besta)
                    .damage(base + random.nextInt(4) + 1)
                    .build();
        };

        player.setWeapon(newWeapon);
        System.out.println("Nova arma: " + newWeapon.getName() + " (dano até " + newWeapon.getDamage() + ")");

        if (next != null)
            next.handle(player);
    }
}
