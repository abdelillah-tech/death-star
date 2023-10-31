package org.alghom.deathstar.domain.models;

import java.util.ArrayList;
import java.util.List;

public class TravelResult implements Comparable<TravelResult> {
    private int temptation;
    private boolean isSucceeded;
    private final List<PlanetDistancePair> planets = new ArrayList<>();
    public TravelResult() {}

    public TravelResult(TravelResult travelResult) {
        this.temptation = travelResult.getTemptation();
        this.isSucceeded = travelResult.isSucceeded();
        this.planets.addAll(travelResult.getPlanets());
    }

    public TravelResult(int temptation, boolean isSucceeded) {
        this.temptation = temptation;
        this.isSucceeded = isSucceeded;
    }

    public int getTemptation() {
        return temptation;
    }

    public void setTemptation(int temptation) {
        this.temptation = temptation;
    }

    public boolean isSucceeded() {
        return isSucceeded;
    }

    public void setSucceeded(boolean succeeded) {
        isSucceeded = succeeded;
    }

    public List<PlanetDistancePair> getPlanets() {
        return planets;
    }

    @Override
    public int compareTo(TravelResult o) {
        if (this.isSucceeded() && !o.isSucceeded()) return 1;
        if (!this.isSucceeded() && !o.isSucceeded()) return 0;
        if (!this.isSucceeded() && o.isSucceeded()) return -1;
        return Integer.compare(o.temptation, this.temptation);
    }

    @Override
    public String toString() {
        return "TravelResult{" +
                "temptation=" + temptation +
                ", isSucceeded=" + isSucceeded +
                ", planets=" + planets +
                '}';
    }
}
