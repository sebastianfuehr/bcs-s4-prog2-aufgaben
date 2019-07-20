package citizens;

public class City {

    private double amtCitizens;
    private String name;

    public City(String name, double amtCitizens) {
        this.name = name;
        this.amtCitizens = amtCitizens;
    }

    public void addAmtOfCitizens(double x) {
        amtCitizens += x;
    }

    public String getName() {
        return name;
    }

    public double getAmtCitizens() {
        return amtCitizens;
    }

}
