package iut.sae.ihm;

import iut.sae.structureDonnees.TNoeud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Cette classe permet de generer un {@link JPanel} avec le dessin du graphe
 * @author Noah Michel et Aurelyen Morel
 * @version 1.0
 */
public class Dessin extends javax.swing.JPanel{

// <editor-fold defaultstate="collapsed" desc="    ATTRIBUTS">
    /**
     * Correspond à la liste des points à dessiner sur l'objet {@link Dessin}
     */
    private final LinkedList<TNoeud> listeNoeud;

    /**
     * Correspond à la dimension du panneau
     */
    private final Dimension dimension;

    private int taille;

    private ArrayList<String> typeAAfficher;

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="    CONSTRUCTOR">
    /**
     * Crée un objet {@link Dessin}
     * @param dimension Correspond à la {@link Dimension dimension} du panneau
     * @param listeNoeud Correspond à la {@link LinkedList<TNoeud> listeNoeud}
     */
    public Dessin(Dimension dimension, LinkedList<TNoeud> listeNoeud, ArrayList<String> typeAAfficher) {
        this.dimension = dimension;
        this.listeNoeud = listeNoeud;
        this.taille = 15;
        this.typeAAfficher = typeAAfficher;

        placerPoint();

        setLayout(null);
        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (TNoeud noeud : listeNoeud) {
                    if ((e.getX() > noeud.getPosX() && e.getX() < noeud.getPosX() + taille) && (e.getY() > noeud.getPosY() && e.getY() < noeud.getPosY() + taille))
                        JOptionPane.showMessageDialog(null, noeud, "Informations sur " + noeud.getNom(), JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
// </editor-fold>

    /**
     * Méthode pour générer aléatoirement la position de chaque {@link TNoeud} et de vérifier s'ils se superposent
     */
    private void placerPoint() {
        int index = 0;

        // Tant qu'il y a un Paint à lire dans la liste, alors...
        while (index < listeNoeud.size()) {

            // On récupère cet objet Paint
            int iteration = 0;

            listeNoeud.get(index).setPosX(new Random().nextInt(dimension.width - taille));
            listeNoeud.get(index).setPosY(new Random().nextInt(dimension.height - taille));

            while (iteration < index + 1) {
                while ((Math.sqrt(Math.pow(listeNoeud.get(index).getPosX() - listeNoeud.get(iteration).getPosX(), 2) + Math.pow(listeNoeud.get(index).getPosY() - listeNoeud.get(iteration).getPosY(), 2))) < taille * 1.5F && !listeNoeud.get(index).equals(listeNoeud.get(iteration))) {
                    listeNoeud.get(index).setPosX(new Random().nextInt(dimension.width - taille));
                    listeNoeud.get(index).setPosY(new Random().nextInt(dimension.height - taille));
                    iteration = 0;
                }
                iteration++;
            }
            index++;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="    METHODES PUBLICS">
    /**
     * Méthode pour dessiner sur le {@link Dessin}
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {

        // On caste le Graphics g en Graphics2D. C'est indispensable si l'on souhaite rendre certains Paint lisses ou pas
        Graphics2D g2d = (Graphics2D) g;

        // On définit la couleur qui sera utilisé pour peindre l'arrière plan du panneau
        g2d.setColor(Color.WHITE);

        // On dessine l'arrière plan. C'est un rectangle (blanc du coup) qui fait la taille du panneau
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setStroke(new BasicStroke(1));
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        ArrayList<String> dejaVisite = new ArrayList<>();
        for (int i = 0; i < listeNoeud.size(); i++) {
            TNoeud noeudDepart = listeNoeud.get(i), noeudArrive = new TNoeud();
            for (int j = 0; j < noeudDepart.getVoisins().size(); j++) {
                dejaVisite.add(noeudDepart.getNom());
                String destination = null;
                if (typeAAfficher.contains(noeudDepart.getVoisins().get(j).getType())){
                    destination = noeudDepart.getVoisins().get(j).getDestination();
                }
                for (TNoeud noeud : listeNoeud) {
                    if (noeud.getNom().equals(destination) && !dejaVisite.contains(destination)) {
                        noeudArrive = noeud;
                    }
                }
                if(destination != null) {
                    switch (noeudDepart.getVoisins().get(j).getType()) {
                        case "A":
                            g2d.setColor(Color.BLUE);
                            break;
                        case "N":
                            g2d.setColor(Color.RED);
                            break;
                        case "D":
                            g2d.setColor(Color.GREEN);
                            break;
                    }
                }
                if(typeAAfficher.contains(noeudArrive.getType())) {
                    g2d.draw(new Line2D.Float(noeudDepart.getPosX() + (taille / 2), noeudDepart.getPosY() + (taille / 2), noeudArrive.getPosX() + (taille / 2), noeudArrive.getPosY() + (taille / 2)));
                }
            }
        }
        for (TNoeud noeud : listeNoeud) {
            switch (noeud.getType()) {
                case "V":
                    g2d.setColor(Color.ORANGE);
                    break;
                case "R":
                    g2d.setColor(Color.PINK);
                    break;
                case "L":
                    g2d.setColor(Color.MAGENTA);
                    break;
            }
            g2d.fillOval(noeud.getPosX(), noeud.getPosY(), taille, taille);
        }
// </editor-fold>
    }
}