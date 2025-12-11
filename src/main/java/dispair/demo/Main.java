package dispair.demo;

public class Main {
public static void main(String[] args) {
// monta cadeia
LevelUpHandler life = new GainLifeHandler(5);
LevelUpHandler potion = new GivePotionHandler();
LevelUpHandler weapon = new UpgradeWeaponHandler();


life.setNext(potion).setNext(weapon);


HistoryStarter starter = new HistoryStarter(life);
starter.start();
}
}