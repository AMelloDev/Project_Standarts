package dispair.demo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Builder
@Getter
@Setter
public class Weapon implements Item {

    private TypeWeapon typeWeapon;
    private MagicTypeDamage magicTypeDamage;
    private int damage;

    @Override
    public String getName() {
        return typeWeapon.name();
    }

    @Override
    public void useItem(Player player) {
        System.out.println(player.getNome() + " está segurando: " + getName());
    }

    @Override
    public void useItem(Player player, Enemy target) {
        Random random = new Random();
        int dano = random.nextInt(damage) + 1; 
        target.takeDamage(dano);
        System.out.println(player.getNome() + " atacou " + target.getName() + " com " + getName()
                + " causando " + dano + " de dano!");
    }

    public enum TypeWeapon { espada, lança, cajado, livro, arco, besta }
    public enum MagicTypeDamage { FIRE, ICE, THUNDER }
}
