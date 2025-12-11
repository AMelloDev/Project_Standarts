package dispair.demo;

import java.util.ArrayList;
import java.util.List;

public class Bag implements Item {

    private final String itemName = "Bag";
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " adicionado à bag.");
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String getName() {
        return itemName;
    }

    @Override
    public void useItem(Player player) {
        System.out.println("Você abriu a bag. Itens: " + items.size());
    }
}