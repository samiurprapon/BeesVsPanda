package bvp.models;

/**
 * Per-level configuration: background, bee count, bee/bullet speed,
 * boss identity, boss toughness and the kill count that summons the boss.
 *
 * <p>Speed model mirrors the existing sleep-based movement
 * ({@code BeeMover} / {@code BulletFiring}): a smaller delay = faster.
 */
public class Level {

    private final int level;
    private final int beeCount;
    private final int beeDelayMs;
    private final int bulletDelayMs;
    private final int killsToSummon;
    private final int bossHp;
    private final int bossPoints;
    private final String bgPath;
    private final String bossName;
    private final String bossImagePath;

    public Level(int level, int beeCount, int beeDelayMs, int bulletDelayMs,
                 int killsToSummon, int bossHp, int bossPoints,
                 String bgPath, String bossName, String bossImagePath) {
        this.level = level;
        this.beeCount = beeCount;
        this.beeDelayMs = beeDelayMs;
        this.bulletDelayMs = bulletDelayMs;
        this.killsToSummon = killsToSummon;
        this.bossHp = bossHp;
        this.bossPoints = bossPoints;
        this.bgPath = bgPath;
        this.bossName = bossName;
        this.bossImagePath = bossImagePath;
    }

    public int getLevel() {
        return level;
    }

    public int getBeeCount() {
        return beeCount;
    }

    public int getBeeDelayMs() {
        return beeDelayMs;
    }

    public int getBulletDelayMs() {
        return bulletDelayMs;
    }

    public int getKillsToSummon() {
        return killsToSummon;
    }

    public int getBossHp() {
        return bossHp;
    }

    public int getBossPoints() {
        return bossPoints;
    }

    public String getBgPath() {
        return bgPath;
    }

    public String getBossName() {
        return bossName;
    }

    public String getBossImagePath() {
        return bossImagePath;
    }

    private static final Level[] LEVELS = {
            new Level(1, 6, 10, 3, 10, 5, 100,
                    "/drawables/layouts/ic_layout_1.png", "Queen Bee",
                    "/drawables/bees/ic_boss_1.png"),
            new Level(2, 7, 8, 3, 10, 10, 150,
                    "/drawables/layouts/ic_layout_2.png", "Hornet King",
                    "/drawables/bees/ic_boss_2.png"),
            new Level(3, 8, 7, 2, 10, 15, 200,
                    "/drawables/layouts/ic_layout_3.png", "Wasp Lord",
                    "/drawables/bees/ic_boss_3.png"),
            new Level(4, 9, 6, 2, 10, 20, 250,
                    "/drawables/layouts/ic_layout_4.png", "Killer Swarm",
                    "/drawables/bees/ic_boss_4.png"),
            new Level(5, 10, 5, 2, 10, 25, 300,
                    "/drawables/layouts/ic_layout_5.png", "Bee Emperor",
                    "/drawables/bees/ic_boss_5.png"),
    };

    /** Returns the config for level {@code n} (1-based), clamped to the available range. */
    public static Level getLevel(int n) {
        if (n < 1) {
            n = 1;
        }
        if (n > LEVELS.length) {
            n = LEVELS.length;
        }
        return LEVELS[n - 1];
    }

    public static int maxLevel() {
        return LEVELS.length;
    }
}
