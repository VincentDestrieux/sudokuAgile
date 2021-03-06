package com.miage.master.myapplication.algo;

import android.util.Pair;

import com.miage.master.myapplication.model.Grid;
import com.miage.master.myapplication.model.PairCoord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vincent  Destrieux on 26/04/2017.
 */

public class TripletsAlgorithm extends VirtualAlgorithm {

    private Grid processing;

    public TripletsAlgorithm() {
        super();
        this.processing = new Grid();
    }

    public TripletsAlgorithm(MovingPattern pattern) {
        super(pattern);
        this.processing = new Grid();
    }


    public void resolveEnLigne() {

        //Passage de chaque ligne
        for (int i = 0; i < 9; i++) {
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            remplirMapEnLigne(i, map);

            //Passage de chaque colonne
            for (int j = 0; j < 9; j++) {
                processusTriplets(i, j, map);
            }
        }
    }

    public void resolveEnColonne() {

        //Passage de chaque colonne
        for (int i = 0; i < 9; i++) {
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            remplirMapEnColonne(i, map);
            remplirMapEnColonne(i, map);
            //Passage de chaque ligne
            for (int j = 0; j < 9; j++) {
                processusTriplets(j, i, map);
            }
        }
    }

    private void resolveEnCarre() {
        for (int i = 0; i < 9; i++) {
            initialiseMapCarre(i);

        }
    }

    private void initialiseMapCarre(int i) {
        PairCoord p;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        switch (i) {
            case 0:
                p = new PairCoord(1,1);
                remplirMapEnCarre(map,p);
                break;
            case 1:
                p = new PairCoord(4,1);
                remplirMapEnCarre(map,p);
                break;
            case 2:
                p = new PairCoord(7,1);
                remplirMapEnCarre(map,p);
                break;
            case 3:
                p = new PairCoord(1,4);
                remplirMapEnCarre(map,p);
                break;
            case 4:
                p = new PairCoord(4,4);
                remplirMapEnCarre(map,p);
                break;
            case 5:
                p = new PairCoord(7,4);
                remplirMapEnCarre(map,p);
                break;
            case 6:
                p = new PairCoord(1,7);
                remplirMapEnCarre(map,p);
                break;
            case 7:
                p = new PairCoord(4,7);
                remplirMapEnCarre(map,p);
                break;
            case 8:
                p = new PairCoord(7,7);
                remplirMapEnCarre(map,p);
                break;
            default:
        }
    }


    private void processusTriplets(int i, int j, Map<Integer, ArrayList<Integer>> map) {
        if (map.get(j).size() == 3) {
            //Permet de reparcourir la map de gauche a droite
            for (int it = 0; it < 9; it++) {
                //S'il y a plus de 2 valeurs dans la map
                if (map.get(it).size() > 2 && it != j) {
                    if (map.get(it).size() == 4) {
                        if (map.get(it).containsAll(map.get(j))) {
                            ArrayList<Integer> quadruplets = map.get(it);
                            ArrayList<Integer> triplets = map.get(j);

                            //Permet de supprimer les éléments en commun
                            quadruplets.removeAll(triplets);
                            PairCoord pairCoord = new PairCoord(it + 1, i + 1);
                            int resultat = quadruplets.get(0);
                            System.out.println(pairCoord + " " + resultat + " // i= " + i + "; it = " + it);
                            //On modifie la valeur dans la grille
                            //Bugg a corriger pour colonne :
                            // il écrit sur des cases deja écrite
                            if (processing.getDigit(pairCoord) == 0) {
                                processing.setDigit(pairCoord, resultat);
                            }
                        }
                    }
                }
            }
        }
    }

    private void remplirMapEnLigne(int i, Map<Integer, ArrayList<Integer>> map) {
        for (int key = 0; key < 9; key++) {
            key = key * i;
            map.put(key, processing.getPossibleGrid().valeurPossibilitiesGrid(i, key));
            //S'il n'y a qu'une solution de base on la mets dans la grille
            if (map.get(key).size() == 1 && processing.getDigit(new PairCoord(key + 1, i + 1)) == 0) {
                processing.setDigit(new PairCoord(key + 1, i + 1), map.get(key).get(0));
            }
        }
    }

    private void remplirMapEnColonne(int i, Map<Integer, ArrayList<Integer>> map) {
        for (int key = 0; key < 9; key++) {
            map.put(key, processing.getPossibleGrid().valeurPossibilitiesGrid(key, i));
            //S'il n'y a qu'une solution de base on la mets dans la grille
            if (map.get(key).size() == 1 && processing.getDigit(new PairCoord(i + 1, key + 1)) == 0) {
                processing.setDigit(new PairCoord(i + 1, key + 1), map.get(key).get(0));
            }
        }
    }

    private void remplirMapEnCarre(Map<Integer, ArrayList<Integer>> map, PairCoord p) {
        int indice = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map.put(indice, processing.getPossibleGrid().valeurPossibilitiesGrid(i, j));
                //S'il n'y a qu'une solution de base on la mets dans la grille
                if (map.get(indice).size() == 1 && processing.getDigit(new PairCoord(j + 1, i + 1)) == 0) {
                    processing.setDigit(new PairCoord(j + 1, i + 1), map.get(indice).get(0));
                }
                indice++;
            }
        }
    }

    @Override
    public Grid compute(Grid grid) {
        Grid resultat;
        setProcessing(grid);
        //resolveEnLigne();
        //resolveEnColonne();
        resolveEnCarre();
        resultat = getProcessing();
        return resultat;
    }

    public Grid getProcessing() {
        return processing;
    }

    public void setProcessing(Grid processing) {
        this.processing = processing;
    }

}
