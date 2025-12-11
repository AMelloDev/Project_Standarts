package dispair.demo;

import lombok.Builder;


@Builder
public class Potion implements Item {


private TypePotion typePotion;


public enum TypePotion { pequena, media, grande }


public int getHealAmount() {
return switch (typePotion) {
case pequena -> 10;
case media -> 15;
case grande -> 25;
};
}


@Override
public String getName() {
return "Poção(" + typePotion.name() + ")";
}


@Override
public void useItem(Player user) {
int heal = getHealAmount();
user.heal(heal);
System.out.println(user.getNome() + " usou " + getName() + " e recuperou " + heal + " vida.");
}
}