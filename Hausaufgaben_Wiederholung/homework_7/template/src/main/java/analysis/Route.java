package analysis;

import org.apache.commons.csv.CSVRecord;

public class Route {
    public final String id;
    public final long agencyId;
    public final String shortName;
    public final String longName;
    public final int type;
    public final String color;
    public final String textColor;
    public final String desc;

    public Route(CSVRecord record) {
        this.id = record.get(0);
        this.agencyId = Long.parseLong(record.get(1));
        this.shortName = record.get(2);
        this.longName = record.get(3);
        this.type = Integer.parseInt(record.get(4));
        this.color = record.get(5);
        this.textColor = record.get(6);
        this.desc = record.get(7);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", agencyId=" + agencyId +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", type=" + type +
                ", color='" + color + '\'' +
                ", textColor='" + textColor + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (agencyId != route.agencyId) return false;
        if (type != route.type) return false;
        if (id != null ? !id.equals(route.id) : route.id != null) return false;
        if (shortName != null ? !shortName.equals(route.shortName) : route.shortName != null) return false;
        if (longName != null ? !longName.equals(route.longName) : route.longName != null) return false;
        if (color != null ? !color.equals(route.color) : route.color != null) return false;
        if (textColor != null ? !textColor.equals(route.textColor) : route.textColor != null) return false;
        return desc != null ? desc.equals(route.desc) : route.desc == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
