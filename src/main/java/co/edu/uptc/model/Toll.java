
package co.edu.uptc.model;


import java.io.Serializable;

public class Toll implements Serializable {

    //Respectivas variables que se leen del archivo
    private String origin;
    private String destinationCity;
    private String road;
    private String roadType;
    private String toll;
    private String category;
    private int cost;
    private String time;
    private int distance;
    private boolean inOperation;

    /**
     * @author monxvoll
     **/

    //Constructor de la clase
    public Toll(String homeTown, String destinationCity, String road, String roadType,
                String toll, String category, int cost, String time, int distance, boolean inOperation) {

        this.origin = homeTown;
        this.destinationCity = destinationCity;
        this.road = road;
        this.roadType = roadType;
        this.toll = toll;
        this.category = category;
        this.cost = cost;
        this.time = time;
        this.distance = distance;
        this.inOperation = inOperation;
    }

    //Getters
    public String getOrigin() {
        return origin;
    }


    public String getDestinationCity() {
        return destinationCity;
    }


    public String getRoad() {
        return road;
    }


    public String getRoadType() {
        return roadType;
    }


    public String getCategory() {
        return category;
    }



    public int getCost() {
        return cost;
    }



    public String getTime() {
        return time;
    }



    public int getDistance() {
        return distance;
    }



}