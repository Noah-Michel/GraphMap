package iut.sae.structureDonnees;

import java.util.LinkedList;

/**
 * Cette classe permet de générer des objets pour stocker les informations sur les {@link TNoeud} du graphe
 * @author Noah Michel et Aurelyen Morel
 * @version 1.0
 */
public class TNoeud {

    private String nom;
    private String type;
    private int posX;
    private int posY;
    private LinkedList<TLien> voisins;

    /**
     * Constructeur par défaut de la classe {@link TNoeud}
     */
    public TNoeud() {
        this.nom = "";
        this.type = "";
        this.posX = 0;
        this.posY = 0;
        this.voisins = null;
    }

    /**
     * Construteur qui permet de créer les {@link TNoeud} avec comme informations le {@link String type} et le {@link String nom}
     * @param type Correspond au {@link String type} du {@link TNoeud}
     * @param nom Correspond au {@link String nom} du {@link TNoeud}
     */
    public TNoeud(String type, String nom) {
        this.nom = nom;
        this.type = type;
        this.posX = 0;
        this.posY = 0;
        this.voisins = new LinkedList<>();
    }

    /**
     * @param nom Correspond au {@link String nom} du {@link TNoeud}
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param type Correspond au {@link String type} du {@link TNoeud}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param positionX Correspond au {@link int positionX} du {@link TNoeud}
     */
    public void setPosX(int positionX) {
        this.posX = positionX;
    }

    /**
     * @param positionY Correspond au {@link int positionY} du {@link TNoeud}
     */
    public void setPosY(int positionY) {
        this.posY = positionY;
    }

    /**
     * @param voisins Correspond au {@link LinkedList< TLien > voisins} du {@link TNoeud}
     */
    public void setVoisins(LinkedList<TLien> voisins) {
        this.voisins = voisins;
    }

    /**
     * Renvoie le {@link String nom} d'un {@link TNoeud}
     * @return Retourne le {@link String nom} du {@link TNoeud}
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le {@link String type} d'un {@link TNoeud}
     * @return Retourne le {@link String type} du {@link TNoeud}
     */
    public String getType() {
        return type;
    }

    /**
     * Renvoie le {@link int positionX} d'un {@link TNoeud}
     * @return Retourne le {@link int positionX} du {@link TNoeud}
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Renvoie le {@link int positionY} d'un {@link TNoeud}
     * @return Retourne le {@link int positionY} du {@link TNoeud}
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Renvoie le {@link LinkedList< TNoeud > voisins} d'un {@link TNoeud}
     * @return Retourne le {@link LinkedList< TNoeud > voisins} du {@link TNoeud}
     */
    public LinkedList<TLien> getVoisins() {
        return voisins;
    }

    /**
     * @return Retourne une {@link String} pour afficher les informations du {@link TNoeud}
     */
    @Override
    public String toString() {
        switch (type) {
            case "V":
                return nom + " est une ville\n" + "Les routes qui la rejoignent sont : " + voisins.toString().replace("[", "").replace("]", "").replace(",","");
            case "R":
                return nom + " est un restaurant\n" + "Les routes qui la rejoignent sont : " + voisins.toString().replace("[", "").replace("]", "").replace(",","");
            case "L":
                return nom + " est un centre de loisirs\n" + "Les routes qui la rejoignent sont : " + voisins.toString().replace("[", "").replace("]", "").replace(",","");
        }
        return "\n";
    }

    /**
     * Methode qui permet d'ajouter les nouveaux {@link TLien} dans les {@link LinkedList<TLien> voisins}
     * @param nouveauLien Correspond au nouveau {@link TLien} que l'on ajoute aux {@link LinkedList<TLien> voisins}
     */
    public void ajoutLien(TLien nouveauLien) {
        this.voisins.add(nouveauLien);
    }
}
