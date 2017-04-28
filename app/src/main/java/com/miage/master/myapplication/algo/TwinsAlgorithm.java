package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.Grid;
import com.miage.master.myapplication.model.PairCoord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vincent  Destrieux on 27/04/2017.
 */

public class TwinsAlgorithm extends VirtualAlgorithm {

    private Grid processing;

    public TwinsAlgorithm() {
        super();
        this.processing = new Grid();
    }

    public TwinsAlgorithm(MovingPattern pattern) {
        super(pattern);
        this.processing = new Grid();
    }


    public void resolve(Grid grille) {

        setProcessing(grille);

        //Passage de chaque ligne
        for (int i = 0; i < 9; i++) {
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();

            remplirMap(i, map);

            //Passage de chaque colonne
            for (int j = 0; j < 9; j++) {
                if (map.get(j).size() == 2) {
                    //Permet de reparcourir la map de gauche a droite
                    for (int it = 0; it < 9; it++) {
                        //S'il y a plus de 2 valeurs dans la map
                        if (map.get(it).size() > 1 && it != j) {
                            if (map.get(it).size() == 3) {
                                if (map.get(it).containsAll(map.get(j))) {
                                    ArrayList<Integer> quadruplets = map.get(it);
                                    ArrayList<Integer> triplets = map.get(j);

                                    //Permet de supprimer les éléments en commun
                                    quadruplets.removeAll(triplets);
                                    PairCoord pairCoord = new PairCoord(it, i);
                                    int resultat = quadruplets.get(0);
                                    System.out.println(pairCoord + " " + resultat);
                                    //On modifie la valeur dans la grille
                                    processing.setDigit(pairCoord, resultat);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void remplirMap(int i, Map<Integer, ArrayList<Integer>> map) {
        for (int key = 0; key < 9; key++) {
            map.put(key, processing.getPossibleGrid().valeurPossibilitiesGrid(i, key));
        }
    }

    @Override
    public Grid compute(Grid grid) {
        Grid resultat = new Grid();
        resolve(grid);
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
