package com.example.sqlite_th2_de1.model;

import java.io.Serializable;

public class TourAndID implements Serializable {
    int id;
    Tour tour;

    public TourAndID () {
    }

    public TourAndID(int id, Tour tour) {
        this.id = id;
        this.tour = tour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "TourAndID{" +
                "id=" + id +
                ", tour=" + tour +
                '}';
    }
}
