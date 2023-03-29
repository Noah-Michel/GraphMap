package iut.sae.fichier;

import iut.sae.structureDonnees.TLien;
import iut.sae.structureDonnees.TNoeud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

/**
 * Cette classe permet de découper et répartir les informations du csv dans la {@link LinkedList<TNoeud>}
 */
public class Entree {
    private LinkedList<TNoeud> listeNoeuds;

    /**
     * Gestion des flux d'entrée
     * @param nomFichier Correspond au nom du fichier que l'on doit ouvrir
     */
    public Entree(File nomFichier) {
        try{
            java.io.InputStream is = new java.io.FileInputStream(nomFichier);
            listeNoeuds = insertNoeud(is);
            is.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Le fichier n'existe pas !");
        }
        catch(IOException e){
            System.out.println("Erreur d'entrée sortie !");
        }
    }

    /**
     * @return Retourne la {@link LinkedList<TNoeud> listeNoeuds}
     */
    public LinkedList<TNoeud> getListeNoeuds() {
        return listeNoeuds;
    }

    /**
     * @param listeNoeuds Correspond au {@link LinkedList<TNoeud> listeNoeuds} du {@link Entree@
     */
    public void setListeNoeuds(LinkedList<TNoeud> listeNoeuds) {
        this.listeNoeuds = listeNoeuds;
    }

    /**
     * Insère les {@link TNoeud} dans la {@link LinkedList<TNoeud> listeNoeud}
     * @param is Correspond au flux d'entrée du fichier
     * @return Renvoie la {@link LinkedList<TNoeud>} remplie des informations du fichier
     * @throws IOException Pour eviter les erreurs de flux, on remonte les erreurs qui sont gérés dans {@link Entree}
     */
    private LinkedList<TNoeud> insertNoeud(InputStream is) throws IOException {
        int unsignedByte;
        String champ;
        LinkedList<TNoeud> listeNoeuds = new LinkedList<>();
        while ((unsignedByte = is.read()) > -1) {
            champ="";
            while ((char) unsignedByte != ';') {
                champ+= (char) unsignedByte;
                unsignedByte=is.read();
            }

            String[] champSepare = champ.split(":");
            String[] infoNoeudDepart = champSepare[0].split(",");
            String[] infoLien = champSepare[1].split(",");
            String[] infoNoeudArrive = champSepare[3].split(",");
            listeNoeuds.add(new TNoeud(infoNoeudDepart[0], infoNoeudDepart[1]));

            try {
                listeNoeuds.getLast().ajoutLien(new TLien(infoLien[0], infoLien[1], infoNoeudArrive[1]));
            } catch (NumberFormatException ex) {
                System.out.println("Erreur dans l'insertion des données");
            }
            unsignedByte = is.read();

            while ((char) unsignedByte != '\n'){
                while ((char) unsignedByte == ';'){
                    unsignedByte = is.read();
                }
                if ((char) unsignedByte == '\r'){
                    unsignedByte = is.read();
                    continue;
                }
                champ = "";
                while ((char) unsignedByte != ';'){
                    if ((char) unsignedByte == '\r'){
                        break;
                    }
                    champ+=((char) unsignedByte);
                    unsignedByte=is.read();
                }
                champSepare = champ.split(":");
                infoLien = champSepare[0].split(",");
                infoNoeudArrive = champSepare[2].split(",");
                try {
                    listeNoeuds.getLast().ajoutLien(new TLien(infoLien[0], infoLien[1], infoNoeudArrive[1]));
                } catch (NumberFormatException ex) {
                    System.out.println("Erreur dans l'insertion des données");
                }
                unsignedByte = is.read();
            }
        }
        return listeNoeuds;
    }
}
