package analysis;

import org.apache.commons.csv.CSVRecord;

/**
 * Entity class for a single trip.
 */
public class Trip {
    public final String routeId;
    public final long serviceId;
    public final long id;
    public final String headsign;
    public final String shortName;
    public final byte directionId;
    public final String blockId;
    public final long shapeId;
    public final boolean wheelchairAccessible;
    public final boolean bikesAllowed;

    public Trip(CSVRecord record) {
        this.routeId = record.get(0);
        this.serviceId = Long.parseLong(record.get(1));
        this.id = Long.parseLong(record.get(2));
        this.headsign = record.get(3);
        this.shortName = record.get(4);
        this.directionId = Byte.parseByte(record.get(5));
        this.blockId = record.get(6);
        this.shapeId = Long.parseLong(record.get(7));
        this.wheelchairAccessible = Integer.parseInt(record.get(8)) != 0;
        this.bikesAllowed = Integer.parseInt(record.get(9)) != 0;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "routeId='" + routeId + '\'' +
                ", serviceId=" + serviceId +
                ", id=" + id +
                ", headsign='" + headsign + '\'' +
                ", shortName='" + shortName + '\'' +
                ", directionId=" + directionId +
                ", blockId=" + blockId +
                ", shapeId=" + shapeId +
                ", wheelchairAccessible=" + wheelchairAccessible +
                ", bikesAllowed=" + bikesAllowed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (serviceId != trip.serviceId) return false;
        if (id != trip.id) return false;
        if (directionId != trip.directionId) return false;
        if (blockId != trip.blockId) return false;
        if (shapeId != trip.shapeId) return false;
        if (wheelchairAccessible != trip.wheelchairAccessible) return false;
        if (bikesAllowed != trip.bikesAllowed) return false;
        if (routeId != null ? !routeId.equals(trip.routeId) : trip.routeId != null) return false;
        if (headsign != null ? !headsign.equals(trip.headsign) : trip.headsign != null) return false;
        return shortName != null ? shortName.equals(trip.shortName) : trip.shortName == null;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
