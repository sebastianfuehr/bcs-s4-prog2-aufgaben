package database;

import org.apache.commons.csv.CSVRecord;

/**
 * Entity class for a single trip.
 */
public class Trip {
    public final String route_id;
    public final long service_id;
    public final long id;
    public final String headsign;
    public final String shortName;
    public final byte direction_id;
    public final String block_id;
    public final long shape_id;
    public final boolean wheelchair_accessible;
    public final boolean bikes_allowed;

    public Trip(CSVRecord record) {
        this.route_id = record.get(0);
        this.service_id = Long.parseLong(record.get(1));
        this.id = Long.parseLong(record.get(2));
        this.headsign = record.get(3);
        this.shortName = record.get(4);
        this.direction_id = Byte.parseByte(record.get(5));
        this.block_id = record.get(6);
        this.shape_id = Long.parseLong(record.get(7));
        this.wheelchair_accessible = Integer.parseInt(record.get(8)) != 0;
        this.bikes_allowed = Integer.parseInt(record.get(9)) != 0;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "route_id='" + route_id + '\'' +
                ", service_id=" + service_id +
                ", id=" + id +
                ", headsign='" + headsign + '\'' +
                ", shortName='" + shortName + '\'' +
                ", direction_id=" + direction_id +
                ", block_id=" + block_id +
                ", shape_id=" + shape_id +
                ", wheelchair_accessible=" + wheelchair_accessible +
                ", bikes_allowed=" + bikes_allowed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (service_id != trip.service_id) return false;
        if (id != trip.id) return false;
        if (direction_id != trip.direction_id) return false;
        if (block_id != trip.block_id) return false;
        if (shape_id != trip.shape_id) return false;
        if (wheelchair_accessible != trip.wheelchair_accessible) return false;
        if (bikes_allowed != trip.bikes_allowed) return false;
        if (route_id != null ? !route_id.equals(trip.route_id) : trip.route_id != null) return false;
        if (headsign != null ? !headsign.equals(trip.headsign) : trip.headsign != null) return false;
        return shortName != null ? shortName.equals(trip.shortName) : trip.shortName == null;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
