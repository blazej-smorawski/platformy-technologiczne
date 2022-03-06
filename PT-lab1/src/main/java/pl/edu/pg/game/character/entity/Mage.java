package pl.edu.pg.game.character.entity;

import pl.edu.pg.game.character.repository.MageRepo;

import java.util.Objects;

public class Mage implements Comparable<Mage>{
    private int health;
    private int intelligence;
    private String name;
    private MageRepo apprentices;

    public Mage(String name, int health, int intelligence) {
        this.name = name;
        this.health = health;
        this.intelligence = intelligence;
        this.apprentices = new MageRepo();
    }

    @Override
    public String toString() {
        return new String("Mage{name='"+name+
                "', health="+health+" int="+intelligence+"}");
    }

    @Override
    public int hashCode() {
        return name.hashCode()+health*31-intelligence*17;
    }

    @Override
    public boolean equals(Object other) {
        if(other.getClass() != getClass()) {
            return false;
        }

        Mage otherMage = (Mage)other;

        return Objects.equals(name, otherMage.name);
    }

    @Override
    public int compareTo(Mage other) {
        return name.compareTo(other.getName());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MageRepo getApprentices() {
        return apprentices;
    }

    public void setApprentices(MageRepo apprentices) {
        this.apprentices = apprentices;
    }

    public int countApprentices() {
        int ret = 0;
        for (Mage apprentice : apprentices.findAll()) {
            ret += apprentice.countApprentices() + 1;
        }
        return ret;
    }
}
