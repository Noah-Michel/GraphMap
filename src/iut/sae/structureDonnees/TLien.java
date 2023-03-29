package iut.sae.structureDonnees;

/**
 * Cette classe permet de générer des objets pour stocker les informations sur les {@link TLien} du graphe
 * @author Noah Michel et Aurelyen Morel
 * @version 1.0
 */
public class TLien {
    private String type;
    private int distance;
    private String destination;

    /**
     * Constructeur par défaut de la classe {@link TLien}
     */
    public TLien() {
        this.type = "";
        this.distance = 0;
        this.destination = null;
    }


    /**
     * Construteur qui permet de créer les {@link TLien} avec comme informations le {@link String type}, le {@link String distance} et le {@link String destination}
     * @param type Correspond au {@link String type} du {@link TLien}
     * @param distance Correspond au {@link String distance} du {@link TLien}
     * @param destination Correspond au {@link String destination} du {@link TLien}
     * @throws NumberFormatException Pour éviter les erreurs de type au moment de la conversion {@link String distance} de {@link String} en {@link int}
     */
    public TLien(String type, String distance, String destination) throws NumberFormatException{
        this.type = type;
        this.distance = Integer.parseInt(distance);
        this.destination = destination;
    }

    /**
     * @param type Correspond au {@link String type} du {@link TLien}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param distance Correspond au {@link String distance} du {@link TLien}
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @param destination Correspond au {@link String destination} du {@link TLien}
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Renvoie le {@link String type} d'un {@link TLien}
     * @return Retourne le {@link String type} du {@link TLien}
     */
    public String getType() {
        return type;
    }

    /**
     * Renvoie le {@link int distance} d'un {@link TLien}
     * @return Retourne le {@link int distance} du {@link TLien}
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Renvoie le {@link String destination} d'un {@link TLien}
     * @return Retourne le {@link String destination} du {@link TLien}
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @return Retourne une {@link String} pour afficher les informations du {@link TLien}
     */
    @Override
    public String toString() {
        switch (type) {
            case "A":
                return  "\nUne autoroute de " + distance + " km en direction de " + destination;
            case "D":
                return  "\nUne départementale de " + distance + " km en direction de " + destination;
            case "N":
                return "\nUne nationale de " + distance + " km en direction de " + destination;
        }
        return "\n";
    }
}
