package database;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Tables {

    /**
     *
     * @return the longest headsign
     * @throws IOException
     */
    public static String findLongestHeadsign() throws IOException {
        Table<Trip> trips = trips();

        Trip longestTrip = trips.reduce(new Table.Aggregation<Trip, Trip>() {

            // der zweite Trip in der Aggregation hätte auch ein String
            // sein können, dann wären die Rückgabetypen von initial
            // und apply ebenfalls String
            @Override
            public Trip initial() {
                return trips.getFirstElement();
            }

            @Override
            public Trip apply(Trip accumulator, Trip next) {
                return (next.headsign.length() > accumulator.headsign.length()) ? next : accumulator;
            }
        });
        return longestTrip.headsign;
    }
    /*
     * Lösung Aufgabenblatt 2 mit Hilfe von reduce:
     *
     * public Trip initial() {
     *     return grpList.getFirst();
     * }
     *
     * public CitizenGroup apply(CitizenGroup acc, CitizenGroup next) {
     *     return (next.
     */

    /**
     * Creates the trips table
     *
     * @return a {@link Table<Trip>}
     * @throws IOException
     */
    public static Table<Trip> trips() throws IOException {
        return getTable("trips.txt", Trip::new);
    }

    private static <T> Table<T> getTable(String resource, Function<CSVRecord, T> factory) throws IOException {
        Reader readerForResource = getReaderForResource(resource);
        Iterable<CSVRecord> records =
                CSVFormat.RFC4180
                    .withFirstRecordAsHeader()
                    .parse(readerForResource);
        List<T> ts = StreamSupport.stream(records.spliterator(), false)
                .map(factory)
                .collect(Collectors.toList()); // Materialize
        return new Table<>(ts);
    }

    private static Reader getReaderForResource(String name) throws FileNotFoundException {
        return new FileReader(new File(Objects.requireNonNull(
                Tables.class.getClassLoader()
                        .getResource(name)).getFile()
        ));
    }
}
