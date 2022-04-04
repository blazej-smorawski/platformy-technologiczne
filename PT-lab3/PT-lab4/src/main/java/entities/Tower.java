package entities;

import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "towers")

public class Tower implements Comparable<Tower>, Serializable {
    @Id
    private String name;
    private int height;
    @OneToMany
    private List<Mage> mages;

    public Tower() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tower tower = (Tower) o;
        return height == tower.height && Objects.equals(name, tower.name) && Objects.equals(mages, tower.mages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, mages);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Mage> getMages() {
        return mages;
    }

    public void setMages(List<Mage> mages) {
        this.mages = mages;
    }

    @Override
    public int compareTo(Tower o) {
        return 0;
    }
}
