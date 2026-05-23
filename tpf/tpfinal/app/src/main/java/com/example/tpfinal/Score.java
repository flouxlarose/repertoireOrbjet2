package com.example.tpfinal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Score {
    private int pointageTotal;
    private String datePartie;
    private int nbMotsTrouves;

    public Score (int pointageTotal){
        this.pointageTotal = pointageTotal;
        datePartie = dateAuj();
    }

    public Score (int pointageTotal, int nbMotsTrouves){
        this.pointageTotal = pointageTotal;
        this.nbMotsTrouves = nbMotsTrouves;
    }

    private String dateAuj(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.FRENCH);
        return now.format(format);
    }
}
