package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "characters")
public class Mage implements Comparable<Mage>, Serializable {
    @Id
    private String name;

    private int level;

    @ManyToOne
    private Tower tower;

    public Mage(){

    }

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
    }

    public static MageBuilder builder() {
        return new MageBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && name.equals(mage.name) && Objects.equals(tower, mage.tower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, tower);
    }

    @Override
    public int compareTo(Mage other) {
        return 0;
    }

    public static class MageBuilder {
        private String name;
        private int level;
        private Tower tower;

        MageBuilder() {
        }

        public MageBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MageBuilder level(int level) {
            this.level = level;
            return this;
        }

        public MageBuilder tower(Tower tower) {
            this.tower = tower;
            return this;
        }

        public Mage build() {
            return new Mage(name, level, tower);
        }

        public String toString() {
            return "Mage.MageBuilder(name=" + this.name + ", level=" + this.level + ", tower=" + this.tower + ")";
        }
    }
}

