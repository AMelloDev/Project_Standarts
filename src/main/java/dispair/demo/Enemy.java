package dispair.demo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Enemy {
    private String name;
    private int lifePoints;
    private int damage;

    public void takeDamage(int damage) {
        this.lifePoints -= damage;
        if (lifePoints < 0)
            lifePoints = 0;
        System.out.println(name + " recebeu " + damage + " de dano! Vida: " + lifePoints);
    }

    public void attack(Player player) {
        player.takeDamage(damage);
        System.out.println(name + " atacou " + player.getNome() + " causando " + damage + " de dano!");
    }

    public boolean isDead() {
        return lifePoints <= 0;
    }
}