package com.example.tpfinal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Score {
    private int pointageTotal;
    private String datePartie;

    public Score (int pointageTotal){
        this.pointageTotal = pointageTotal;
        datePartie = dateAuj();
    }

    private String dateAuj(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.FRENCH);
        return now.format(format);
    }

    public int getPointageTotal() {
        return pointageTotal;
    }

    public void setPointageTotal(int pointageTotal) {
        this.pointageTotal = pointageTotal;
    }

    public String getDatePartie() {
        return datePartie;
    }

    public void setDatePartie(String datePartie) {
        this.datePartie = dateAuj();
    }
}
