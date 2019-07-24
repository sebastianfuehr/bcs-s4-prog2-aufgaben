package analysis;

import database.Table;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class VbbStreams {

    /**
     * Finds the id for a particular {@link Agency}
     *
     * @param agencyName the agency's name
     * @return An optional long, which is defined if an agency was found, if not it's undefined.
     */
    public static OptionalLong findAgencyId(String agencyName) {
        try {
            return OptionalLong.of(VbbStreams.agencyStream()
                                                .filter(a -> a.name.contains(agencyName))
                                                .findFirst()
                                                .get()
                                                .id);
        } catch (IOException | NoSuchElementException e) {
            System.out.println(String.format("Element id for %s not found. Returned empty OptionalLong.", agencyName));
            return OptionalLong.empty();
        }
    }

    /**
     * Finds routes and their type {@link RouteAndType} from an ID of an {@link Agency}.
     *
     * @param agencyId the agency's Id
     * @return a stream of routes and their type
     */
    public static Stream<RouteAndType> routesForAgency(long agencyId) {
        try {
            return VbbStreams.routeStream()
                    .filter(r -> r.agencyId == agencyId)
                    .map(RouteAndType::new);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Finds out if all trips for a given route have the feature {@link Trip#bikesAllowed}
     *
     * @param routeName
     * @return true if all trips of a route have bike allowed
     */
    public static boolean canUseBikeOnRoute(String routeName) {
        try {
            return VbbStreams.tripStream()
                    .filter(t -> t.routeId.equals(routeName))
                    .allMatch(t -> t.bikesAllowed);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Long> routeStats(long agencyId) {
        try {
            return VbbStreams.routeStream()
                    .filter(r -> r.agencyId == agencyId)
                    .map(r -> r.type)
                    .collect(Collectors.groupingBy(
                            RouteTypes::getNameForRouteType, Collectors.counting()
                    ));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Repreents a route and type of a route.
     */
    public static class RouteAndType {
        private final String name;
        private final String type;

        @Override
        public String toString() {
            return "RouteAndType{" +
                    "name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }

        /**
         * Creates an instance based on a {@link Route}
         *
         * @param route
         */
        public RouteAndType(Route route) {
            this.name = route.shortName;
            this.type = RouteTypes.getNameForRouteType(route.type);
        }
    }

    /**
     * Returns a stream of {@link Trip}
     *
     * @return a stream of trips
     * @throws IOException
     */
    public static Stream<Trip> tripStream() throws IOException {
        return streamFromIterable(parseReader(getReaderForResource("trips.txt")), Trip::new);
    }

    /**
     * Returns a stream of {@link Agency}
     *
     * @return a stream of agencies
     * @throws IOException
     */
    public static Stream<Agency> agencyStream() throws IOException {
        return streamFromIterable(parseReader(getReaderForResource("agency.txt")), Agency::new);
    }

    /**
     * Returns a stream of {@link Route}
     *
     * @return a stream routes
     * @throws IOException
     */
    public static Stream<Route> routeStream() throws IOException {
        return streamFromIterable(parseReader(getReaderForResource("routes.txt")), Route::new);
    }

    private static <T> Table<T> getTable(String resource, Function<CSVRecord, T> factory) throws IOException {
        Reader readerForResource = getReaderForResource(resource);
        Iterable<CSVRecord> records =
                CSVFormat.RFC4180.withDelimiter(',')
                    .withFirstRecordAsHeader()
                    .parse(readerForResource);
        List<T> ts = streamFromIterable(records, factory).collect(Collectors.toList()); // Materialize
        return new Table<>(ts);
    }

    private static Iterable<CSVRecord> parseReader(Reader reader) throws IOException {
        return CSVFormat.RFC4180
                .withFirstRecordAsHeader()
                .parse(reader);
    }

    private static <T> Stream<T> streamFromIterable(Iterable<CSVRecord> iterable, Function<CSVRecord, T> factory) {
        return StreamSupport.stream(iterable.spliterator(), false).map(factory);
    }

    private static Reader getReaderForResource(String name) throws FileNotFoundException, UnsupportedEncodingException {
        URL resource = VbbStreams.class.getClassLoader().getResource(name);
        URL url = Objects.requireNonNull(resource);
        String decode = URLDecoder.decode(url.getFile(), "UTF-8");
        return new FileReader(new File(decode));
    }
}
