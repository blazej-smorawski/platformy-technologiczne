package entities;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Builder
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
}

