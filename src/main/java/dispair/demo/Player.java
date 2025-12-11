package dispair.demo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Player {
    private String nome;
    private PlayerClass playerClass;
    private byte playerLevel;
    private int lifePoints;
    private Bag bag;
    private Weapon weapon;

    public enum PlayerClass {
        Guerreiro, Mago, Arqueiro
    }

    public void useItem(Item item) {
        item.useItem(this);
    }

    public void takeDamage(int damage) {
        this.lifePoints -= damage;
        if (lifePoints < 0)
            lifePoints = 0;
        System.out.println(nome + " tomou " + damage + " de dano!");
    }

    public void heal(int heal) {
        this.lifePoints += heal;
        System.out.println(nome + " recuperou " + heal + " pontos de vida!");
    }
}