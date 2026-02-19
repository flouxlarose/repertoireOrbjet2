package com.example.examen_1;

import java.util.ArrayList;

public class Groupe {
    private ArrayList<Evaluation> groupeEtu;

    public Groupe() {
        groupeEtu = new ArrayList<>();
    }

    public ArrayList<Evaluation> getGroupeEtu() {
        return groupeEtu;
    }

    public void setGroupeEtu(ArrayList<Evaluation> groupeEtu) {
        this.groupeEtu = groupeEtu;
    }

    public void addEtu (Evaluation etu){
        groupeEtu.add(etu);
    }

    public int nbreEtu() {
        return groupeEtu.size();
    }

    public int meilleurMatricule() {
        int meilleurMat = 0;
        int meilleurSer = 0;
        for (int i = 0; i < groupeEtu.size(); i++){
            if ((groupeEtu.get(i).getNbreService()) > meilleurSer ){
                meilleurMat = groupeEtu.get(i).getMatricule();
                meilleurSer = groupeEtu.get(i).getNbreService();
            }
        }
        return meilleurMat;
    }
}
