package dispair.demo;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HistoryStarter {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private LevelUpHandler levelUpChain;

    public HistoryStarter(LevelUpHandler levelUpChain) {
        this.levelUpChain = levelUpChain;
    }

    public void start() {
        System.out.println("Bem-vindo ao RPG Terminal!\n");

        System.out.print("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();

        Player.PlayerClass playerClass = escolherClasse();

        Player player = criarJogador(nome, playerClass);

        // save in singleton
        GameInstance.getInstance().setCharacter(player);
        GameInstance.getInstance().setWorldLevel("Floresta Sombria");

        System.out.println("\n✨ Bem-vindo " + player.getNome() + " o " + player.getPlayerClass() + "!");

        fornecerArmaInicial(player);

        List<Enemy> inimigos = List.of(
                Enemy.builder().name("Goblin").lifePoints(15).damage(4).build(),
                Enemy.builder().name("Orc").lifePoints(25).damage(6).build(),
                Enemy.builder().name("Rei Esqueleto").lifePoints(30).damage(8).build());

        for (int i = 0; i < inimigos.size(); i++) {
            Enemy enemy = inimigos.get(i);

            System.out.println("\n🌍 Local Atual: " + GameInstance.getInstance().getWorldLevel());
            System.out.println("⚔ Um inimigo apareceu: " + enemy.getName());

            batalha(player, enemy);

            if (player.getLifePoints() <= 0) {
                System.out.println("\n💀 Você foi derrotado... Fim de jogo.");
                return;
            }

            System.out.println("\n🏆 Você derrotou " + enemy.getName() + "!");

            // somente se não for o último inimigo
            if (i != inimigos.size() - 1) {
                levelUpChain.handle(player);
            }

            GameInstance.getInstance().setWorldLevel("Avançando na masmorra (posição " + (i + 1) + ")");
        }
        System.out.println("\n🎉 PARABÉNS " + player.getNome() + "!!!");
        System.out.println("Você venceu todos os inimigos e se tornou uma lenda!");
    }

    private Player.PlayerClass escolherClasse() {
        System.out.println("Escolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");

        while (true) {
            System.out.print("Sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            return switch (escolha) {
                case 1 -> Player.PlayerClass.Guerreiro;
                case 2 -> Player.PlayerClass.Mago;
                case 3 -> Player.PlayerClass.Arqueiro;
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    yield null;
                }
            };
        }
    }

    private Player criarJogador(String nome, Player.PlayerClass playerClass) {
        return Player.builder()
                .nome(nome)
                .playerClass(playerClass)
                .playerLevel((byte) 1)
                .lifePoints(30)
                .bag(new Bag())
                .weapon(null)
                .build();
    }

    private void fornecerArmaInicial(Player player) {
        Weapon weapon = switch (player.getPlayerClass()) {
            case Guerreiro -> Weapon.builder().typeWeapon(Weapon.TypeWeapon.espada).damage(5).build();
            case Mago -> Weapon.builder().typeWeapon(Weapon.TypeWeapon.cajado)
                    .magicTypeDamage(Weapon.MagicTypeDamage.values()[random.nextInt(3)])
                    .damage(6).build();
            case Arqueiro -> Weapon.builder().typeWeapon(Weapon.TypeWeapon.arco).damage(4).build();
        };

        player.setWeapon(weapon);
        System.out.println("🔰 Você recebeu uma arma inicial: " + weapon.getTypeWeapon());
    }

    private void batalha(Player player, Enemy enemy) {
        while (enemy.getLifePoints() > 0 && player.getLifePoints() > 0) {

            System.out.println("\nVida Player: " + player.getLifePoints() +
                    " | Vida Inimigo: " + enemy.getLifePoints());

            System.out.println("Escolha sua ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Beber Poção");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                player.getWeapon().useItem(player, enemy);
            } else if (escolha == 2) {
                usarPocao(player);
            } else {
                System.out.println("Escolha inválida.\n");
                continue;
            }

            if (enemy.getLifePoints() > 0) {
                enemy.attack(player);
            }
        }
    }

    private void usarPocao(Player player) {
        if (player.getBag().getItems().isEmpty()) {
            System.out.println("Você não tem poções!");
            return;
        }

        System.out.println("\nEscolha uma poção:");
        for (int i = 0; i < player.getBag().getItems().size(); i++) {
            System.out.println((i + 1) + " - " + player.getBag().getItems().get(i).getName());
        }

        System.out.print("Opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > player.getBag().getItems().size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Item item = player.getBag().getItems().remove(escolha - 1);
        item.useItem(player);
    }
}
