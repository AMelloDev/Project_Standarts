package dispair.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameInstance {

    private static GameInstance instance;
    private String worldLevel;
    private Player character;

    private GameInstance() {
    }

    public static GameInstance getInstance() {
        if (instance == null) {
            instance = new GameInstance();
        }
        return instance;
    }
}