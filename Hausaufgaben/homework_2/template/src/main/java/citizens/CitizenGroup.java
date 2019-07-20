package citizens;

/**
 * Task 2.2.2
 * Represents a citizen entity.
 */
public class CitizenGroup {

    private int district,
            city,
            sex,
            agegrp_min,
            agegrp_max;
    private double occurrences;
    private String district_name,
            city_name;
    private char state;

    public CitizenGroup(int district, String district_name, int city, String city_name, int sex, char state,
                        int agegrp_min, int agegrp_max, double occurrences) {
        this.district = district;
        this.city = city;
        this.sex = sex;
        this.agegrp_min = agegrp_min;
        this.agegrp_max = agegrp_max;
        this.occurrences = occurrences;
        this.district_name = district_name;
        this.city_name = city_name;
        this.state = state;
    }

    public String toString() {
        return (district + " "
                + district_name + " "
                + city + " "
                + city_name + ""
                + sex + " "
                + state + " "
                + agegrp_min + "_" + agegrp_max + " "
                + occurrences);
    }

    public int getDistrict() {
        return district;
    }

    public int getCity() {
        return city;
    }

    public int getSex() {
        return sex;
    }

    public int getAgegrp_min() {
        return agegrp_min;
    }

    public int getAgegrp_max() {
        return agegrp_max;
    }

    public double getOccurrences() {
        return occurrences;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public char getState() {
        return state;
    }
}
