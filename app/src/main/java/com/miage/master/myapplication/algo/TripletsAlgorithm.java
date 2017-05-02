package com.miage.master.myapplication.algo;

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

        //Passage de chaque ligne
        for (int i = 0; i < 9; i++) {
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            remplirMapEnColonne(i, map);

            //Passage de chaque colonne
            for (int j = 0; j < 9; j++) {
                processusTriplets(j, i, map);
            }
        }
    }

    //##############################################################################
    //Reste a faire si c'est un singleton avant mettre la grille a jour dedans évite les fausses manip.
    // ###########################################################################

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
                            PairCoord pairCoord = new PairCoord(it+1, i+1);
                            int resultat = quadruplets.get(0);
                            System.out.println(pairCoord + " " + resultat + " // i= " + i + "; it = " + it);
                            //On modifie la valeur dans la grille
                            processing.setDigit(pairCoord, resultat);
                        }
                    }
                }
            }
        }
    }

    private void remplirMapEnLigne(int i, Map<Integer, ArrayList<Integer>> map) {
        for (int key = 0; key < 9; key++) {
            map.put(key, processing.getPossibleGrid().valeurPossibilitiesGrid(i, key));
        }
    }

    private void remplirMapEnColonne(int i, Map<Integer, ArrayList<Integer>> map) {
        for (int key = 0; key < 9; key++) {
            map.put(key, processing.getPossibleGrid().valeurPossibilitiesGrid(key, i));
        }
    }

    @Override
    public Grid compute(Grid grid) {
        Grid resultat = new Grid();
        setProcessing(grid);
        resolveEnLigne();
        //resolveEnColonne();
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
