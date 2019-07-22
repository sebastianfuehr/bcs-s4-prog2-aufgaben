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
     * TODO Returns a String with the longest headsign
     *
     * @return the longest headsign
     * @throws IOException
     */
    public static String findLongestHeadsign() throws IOException {
        Table<Trip> trips = trips();

        /*
        Implementierung von Table.Aggregation<T, R>() als anonyme Klasse um Table.reduce benutzen zu können.
         */
        return trips.reduce(new Table.Aggregation<Trip, String>() {
            @Override
            public String initial() {return "";}

            @Override
            public String apply(String acc, Trip next) {
                return next.headsign.length() > acc.length() ? next.headsign : acc;
            }
        });
    }

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
