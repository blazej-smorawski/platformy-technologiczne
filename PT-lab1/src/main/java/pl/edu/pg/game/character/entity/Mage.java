package pl.edu.pg.game.character.entity;

import pl.edu.pg.game.character.repository.MageRepo;

import java.util.Objects;

public class Mage implements Comparable<Mage>{
    private int level;
    private double power;
    private String name;
    private MageRepo apprentices;

    public Mage(String name, double power, int level) {
        this.setName(name);
        this.setPower(power);
        this.setLevel(level);
        this.apprentices = new MageRepo();
    }

    @Override
    public String toString() {
        return new String("Mage{name='"+ getName() +
                "', level="+ getLevel() +" power="+ getPower() +"}");
    }

    @Override
    public int hashCode() {
        return getName().hashCode()+ getLevel() *31-(int) getPower() *17;
    }

    @Override
    public boolean equals(Object other) {
        if(other.getClass() != getClass()) {
            return false;
        }

        Mage otherMage = (Mage)other;

        return Objects.equals(getName(), otherMage.getName());
    }

    @Override
    public int compareTo(Mage other) {
        int ret = getName().compareTo(other.getName());
        if(ret == 0) {
            ret = getLevel()-other.getLevel();
            if(ret == 0) {
                double diff = getPower()-other.getPower();
                if(diff>0) {
                    ret = 1;
                }
                else if(diff<0) {
                    ret = -1;
                }
            }
        }
        return ret;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
