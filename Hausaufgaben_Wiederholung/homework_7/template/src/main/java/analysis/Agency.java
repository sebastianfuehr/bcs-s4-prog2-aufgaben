package analysis;

import org.apache.commons.csv.CSVRecord;

public class Agency {
    public final long id;
    public final String name;
    public final String url;
    public final String timezone;
    public final String lang;
    public final String phone;

    public Agency(CSVRecord record) {
        this.id = Long.parseLong(record.get(0));
        this.name = record.get(1);
        this.url = record.get(2);
        this.timezone = record.get(3);
        this.lang = record.get(4);
        this.phone = record.get(5);
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", timezone='" + timezone + '\'' +
                ", lang='" + lang + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agency agency = (Agency) o;

        if (id != agency.id) return false;
        if (name != null ? !name.equals(agency.name) : agency.name != null) return false;
        if (url != null ? !url.equals(agency.url) : agency.url != null) return false;
        if (timezone != null ? !timezone.equals(agency.timezone) : agency.timezone != null) return false;
        if (lang != null ? !lang.equals(agency.lang) : agency.lang != null) return false;
        return phone != null ? phone.equals(agency.phone) : agency.phone == null;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
