package RabbitMQ;

public class GameObject {
    private String name;
    private int score;
    private int health;

    // Default constructor
    public GameObject() {
    }

    // Parameterized constructor
    public GameObject(String name, int score, int health) {
        this.name = name;
        this.score = score;
        this.health = health;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}