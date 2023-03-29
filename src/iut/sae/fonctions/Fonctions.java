package iut.sae.fonctions;

import iut.sae.structureDonnees.TLien;
import iut.sae.structureDonnees.TNoeud;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Cette classe contient toutes les méthodes dont nous pouvons éventuellement avoir recours. Cela sert à manipuler le graphe
 * @author Noah Michel et Morel Aurelyen
 * @version 1.0
 */
public class Fonctions {
    private LinkedList<TNoeud> listeNoeuds;

    public Fonctions() {
        this.listeNoeuds = null;
    }

    public Fonctions(LinkedList<TNoeud> list) {
        this.listeNoeuds = list;
    }

    public void setListeNoeuds(LinkedList<TNoeud> listeNoeuds) {
        this.listeNoeuds = listeNoeuds;
    }

    public LinkedList<TNoeud> getListeNoeuds() {
        return listeNoeuds;
    }

    /**
     * Cette methode nous permet de récupérer une liste composée de noeud du type voulu.
     * @param type Permet de prendre le {@link String type} du {@link TNoeud} que l'on veut afficher, soit V pour ville, R pour restaurant ou L pour loisirs.
     * @return  Retourne une liste des {@link TNoeud} du type choisie.
     */
    public ArrayList<String> afficherTypeNoeud(String type){
        ArrayList<String> reponse = new ArrayList<>();
        for (TNoeud elListe : listeNoeuds) {
            try {
                if (elListe.getType().equals(type)) {
                    reponse.add(elListe.getNom());
                }
            } catch (NullPointerException ignored) {
            }

        }
        return reponse;
    }

    /**
     * Cette méthode nous permet de connaitre le nombre de noeud d'un type voulu.
     * @param type Permet de prendre le {@link String type} du {@link TNoeud} que l'on veut afficher, soit V pour ville, R pour restaurant ou L pour loisirs.
     * @return  Retourne le nombre de noeud du type voulu.
     */
    public int afficherNombreNoeud(String type){
        int number = 0;
        for(int index = 0; listeNoeuds.size() > index; index++) {
            TNoeud elListe = listeNoeuds.get(index);
            try {
                if (elListe.getType().equals(type)) {
                    number++;
                }
                index++;
            } catch (NullPointerException ignored) {
            }
        }
        return number;
    }

    /**
     * Cette méthode permet de récupérer une liste composée de liens avec le type voulu.
     * @param type Permet de prendre le {@link String type} du {@link TNoeud} que l'on veut afficher, soit A pour autoroute, D pour départementale ou N pour Nationale.
     * @return  Retourne la liste des liens du type voulu.
     */
    public ArrayList<String> afficherTypeLien(String type){
        ArrayList<String> reponse = new ArrayList<>();
        ArrayList<String> dejaVisite = new ArrayList<>();
        for (TNoeud elListe : listeNoeuds) {
            dejaVisite.add(elListe.getNom());
            for (int nbVoisins = 0; elListe.getVoisins().size() > nbVoisins; nbVoisins++) {
                TLien elListeLien = elListe.getVoisins().get(nbVoisins);
                if (elListeLien.getType().equals(type) && type.equals("N") && !dejaVisite.contains(elListeLien.getDestination())) {
                    reponse.add(elListeLien.getDestination());
                } else if (elListeLien.getType().equals(type) && type.equals("D") && !dejaVisite.contains(elListeLien.getDestination())) {
                    reponse.add(elListeLien.getDestination());
                } else if (elListeLien.getType().equals(type) && type.equals("A") && !dejaVisite.contains(elListeLien.getDestination())) {
                    reponse.add(elListeLien.getDestination());
                }
            }
        }
        return reponse;
    }

    /**
     * Cette méthode permet récupérer directement la liste des noeud de type V (ville) sans passer par la méthode {@link #afficherTypeNoeud(String type) afficherTypeNoeud()}.
     * @return Retourne une liste de noeud de type ville.
     */
    public ArrayList<String> afficherVille(){
        return afficherTypeNoeud("V");
    }

    /**
     * Cette méthode permet récupérer directement la liste des noeud de type R (restaurant) sans passer par la méthode {@link #afficherTypeNoeud(String type) afficherTypeNoeud()}.
     * @return Retourne une liste de noeud de type restaurant.
     */
    public ArrayList<String> afficherRestaurant(){
        return afficherTypeNoeud("R");
    }

    /**
     * Cette méthode permet récupérer directement la liste des noeud de type L (Loisir) sans passer par la méthode {@link #afficherTypeNoeud(String type) afficherTypeNoeud()}.
     * @return Retourne une liste de noeud de type loisir.
     */
    public ArrayList<String> afficherLoisir(){
        return afficherTypeNoeud("L");
    }

    /**
     * Cette méthode permet récupérer directement la liste des liens de type D (départementale) sans passer par la méthode {@link #afficherTypeLien(String type) afficherTypeLien()}.
     * @return Retourne une liste de lien de type départementale.
     */
    public ArrayList<String> afficherDepart(){
        return afficherTypeLien("D");
    }

    /**
     * Cette méthode permet récupérer directement la liste des liens de type N (Nationale) sans passer par la méthode {@link #afficherTypeLien(String type) afficherTypeLien()}.
     * @return Retourne une liste de lien de type nationale.
     */
    public ArrayList<String> afficherNational(){
        return afficherTypeLien("N");
    }

    /**
     * Cette méthode permet récupérer directement la liste des liens de type A (autoroute) sans passer par la méthode {@link #afficherTypeLien(String type) afficherTypeLien()}.
     * @return Retourne une liste de lien de type autoroute.
     */
    public ArrayList<String> afficherAutoroute(){
        return afficherTypeLien("A");
    }

    /**
     * Cette méthode permet de récupérer tous les noeuds du graphe
     * @return Retourne une liste de tous les noeuds du graphe
     */
    public ArrayList<String> listerNoeud(){
        ArrayList<String> reponse = new ArrayList<>();
        for(int index = 0; listeNoeuds.size() > index; index++){
            TNoeud elListe = listeNoeuds.get(index);
            reponse.add(elListe.getNom() + " " + elListe.getType() + "\n");
            index++;
        }
        return reponse;
    }

    /**
     * Cette méthode permet de récupérer tous les liens du graphe
     * @return Retourne une liste de tous les liens du graphe
     */
    public ArrayList<String> listerLien(){
        ArrayList<String> reponse = new ArrayList<>();
        for(int index = 0; listeNoeuds.size() > index; index++){
            TNoeud elListe = listeNoeuds.get(index);
            for(int nbVoisins = 0; elListe.getVoisins().size() > nbVoisins; nbVoisins++){
                TLien  elListelien = elListe.getVoisins().get(nbVoisins);
                reponse.add("De " + elListe.getNom() + " à " + elListelien.getDestination() + " " + elListelien.getType() + " " + elListelien.getDistance() + "\n");
            }
        }
        return reponse;
    }

    /**
     * Cette méthode permet de récupérer une liste de noeuds situés à 1 distance d'un noeud choisi.
     * @param noeudRecherche représente le noeud choisi.
     * @return retourne une liste de noeud.
     */
    public ArrayList<String> distance1(String noeudRecherche){
        int index = 0;
        ArrayList<String> resultat = new ArrayList<>();
        TNoeud elListe = listeNoeuds.get(index);
        while(!Objects.equals(elListe.getNom(), noeudRecherche)) {
            elListe = listeNoeuds.get(index);
            index++;
        }
        resultat.add(elListe.getNom());
        for(int nbVoisins = 0; elListe.getVoisins().size() > nbVoisins; nbVoisins++){
            TLien elListelien = elListe.getVoisins().get(nbVoisins);
            resultat.add(elListelien.getDestination());
        }
        return resultat;
    }

    /**
     * Cette méthode permet de récupérer une liste de noeuds d'un type voulu situés à 1 distance d'un noeud choisi.
     * @param noeudRecherche Représente le noeud choisi.
     * @param typeRecherche  Représente le type des noeuds recherché, V, R ou L.
     * @return Retourne une liste de noeud avec le type voulu.
     */
    public ArrayList<String> distance1(String noeudRecherche, String typeRecherche){
        int index = 0;
        ArrayList<String> resultat = new ArrayList<>();
        TNoeud elListe = listeNoeuds.get(index);
        while(!Objects.equals(elListe.getNom(), noeudRecherche)) {
            elListe = listeNoeuds.get(index);
            index++;
        }
        if(elListe.getType().equals(typeRecherche)) {
            resultat.add(elListe.getNom());
        }
        for(int nbVoisins = 0; elListe.getVoisins().size() > nbVoisins; nbVoisins++){
            TLien elListelien = elListe.getVoisins().get(nbVoisins);
            index = 0;
            TNoeud elliste2 = listeNoeuds.get(index);
            while(!Objects.equals(elliste2.getNom(), elListelien.getDestination())) {
                elliste2 = listeNoeuds.get(index);
                index++;
            }
            if (elliste2.getType().equals(typeRecherche))
                resultat.add(elListelien.getDestination());
        }

        return resultat;
    }

    /**
     * Cette méthode permet de récupérer une liste de noeuds situés à 2 distance d'un noeud choisi.
     * @param noeud_recherche Représente le noeud choisi.
     * @return Retourne une liste de noeud.
     */
    public ArrayList<String> distance2(String noeud_recherche){
        ArrayList<String> listeDistance1 = distance1(noeud_recherche), result = new ArrayList<>(), listeTemporaire = new ArrayList<>();
        while (!listeDistance1.isEmpty()){
            listeTemporaire = distance1(listeDistance1.get(0));
            listeDistance1.remove(0);
            while (!listeTemporaire.isEmpty()){
                if (/*!Objects.equals(listeTemporaire.get(0), noeud_recherche) && */!result.contains(listeTemporaire.get(0))) {
                    result.add(listeTemporaire.get(0));
                }
                listeTemporaire.remove(0);
            }
        }
        return result;
    }

    /**
     * Cette méthode permet de savoir si deux noeud mis en argument sont à 2 distance l'un de l'autre ou pas.
     * @param premierNoeudRecherche Représente le premier noeud voulu.
     * @param secondNoeudRecherche  Représente le deuxième noeud voulu.
     * @return boolean true si les deux noeuds sont à 2 distance, false sinon.
     */
    public boolean distance2OuPas(String premierNoeudRecherche, String secondNoeudRecherche){
        ArrayList<String> listeDistance2 = distance2(premierNoeudRecherche);
        return listeDistance2.contains(secondNoeudRecherche);
    }

    /**
     * Cette méthode permet de récupérer une liste de noeud d'un type voulu situés a 2 distance d'un noeud choisi.
     * @param noeudRecherche Représente le noeud choisi.
     * @param typeRecherche  Représente le type des noeuds recherché, V, R ou L.
     * @return Retourne une liste de noeud avec le type voulu.
     */
    public ArrayList<String> distance2(String noeudRecherche, String typeRecherche){
        ArrayList<String> listeDistance1 = distance1(noeudRecherche), result = new ArrayList<>(), listeTemporaire;
        while (!listeDistance1.isEmpty()){
            listeTemporaire = distance1(listeDistance1.get(0), typeRecherche);
            listeDistance1.remove(0);
            while (!listeTemporaire.isEmpty()){
                if (!Objects.equals(listeTemporaire.get(0), noeudRecherche) && !result.contains(listeTemporaire.get(0))) {
                    result.add(listeTemporaire.get(0));
                }
                listeTemporaire.remove(0);
            }
        }
        return result;
    }

    /**
     * Cette methode permet de savoir si une ville A est plus ou moins ouverte qu'une ville B
     * @param premiereVille représente la ville A
     * @param secondeVille représente la ville B
     * @return int 0 si la ville A est la plus ouverte, 1 si c'est la ville B et 2 si c'est égal
     */
    public int plusMoinsOuverte(String premiereVille, String secondeVille){
        int reponse = 2;
        ArrayList<String> liste_a = distance2(premiereVille, "V");
        ArrayList<String> liste_b = distance2(secondeVille, "V");
        if(liste_a.size() > liste_b.size()) reponse = 0;
        else if (liste_a.size() < liste_b.size()) reponse = 1;
        return reponse;
    }

    /**
     * Cette methode permet de savoir si une ville A est plus ou moins gastronomique qu'une ville B
     * @param premiereVille représente la ville A
     * @param secondeVille représente la ville B
     * @return int 0 si la ville A est la plus gastronomique, 1 si c'est la ville B et 2 si c'est égal
     */
    public int plusMoinsGastro(String premiereVille, String secondeVille){
        int reponse = 2;
        ArrayList<String> liste_a = distance2(premiereVille, "R");
        ArrayList<String> liste_b = distance2(secondeVille, "R");
        if(liste_a.size() > liste_b.size()) reponse = 0;
        else if (liste_a.size() < liste_b.size()) reponse = 1;
        return reponse;
    }

    /**
     * Cette methode permet de savoir si une ville A est plus ou moins culturelle qu'une ville B
     * @param premierVille représente la ville A
     * @param secondeVille représente la ville B
     * @return int 0 si la ville A est la plus culturelle, 1 si c'est la ville B et 2 si c'est égal
     */
    public int plusMoinsCulturelle(String premierVille, String secondeVille){
        int reponse = 2;
        ArrayList<String> liste_a = distance2(premierVille, "L");
        ArrayList<String> liste_b = distance2(secondeVille, "L");
        if(liste_a.size() > liste_b.size()) reponse = 0;
        else if (liste_a.size() < liste_b.size()) reponse = 1;
        return reponse;
    }
}