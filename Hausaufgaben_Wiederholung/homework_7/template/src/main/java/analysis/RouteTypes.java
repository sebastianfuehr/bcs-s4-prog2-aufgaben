package analysis;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains {@link RouteTypes#getNameForRouteType(int)}, which
 * can convert a {@link Route#type} to a meaningful String.
 *
 */
public class RouteTypes {

    /**
     * Converts a route type {@link Route#type} to a meaningful String.
     *
     * @param routeType the {@link Route#type}
     * @return a String representation of the route type
     */
    public static String getNameForRouteType(int routeType) {
        return routeTypeToString.getOrDefault(routeType, String.format("Unknown route type: %d", routeType));
    }

    private static Map<Integer, String> routeTypeToString =
            Collections.unmodifiableMap(new HashMap<Integer, String>(){{ // don't do this at home
                // Basic
                put(0, "Tram, Streetcar, Light rail");
                put(1, "Subway, Metro");
                put(2, "Rail");
                put(3, "Bus");
                put(4, "Ferry");
                put(5, "Cable Car");
                put(6, "Gondola, Suspended cable car");
                put(7, "Funicular");

                // Extended
                put(100, "Railway Service");
                put(101, "High Speed Rail Service");
                put(102, "Long Distance Trains");
                put(103, "Inter Regional Rail Service");
                put(104, "Car Transport Rail Service");
                put(105, "Sleeper Rail Service");
                put(106, "Regional Rail Service");
                put(107, "Tourist Railway Service");
                put(108, "Rail Shuttle (Within Complex);");
                put(109, "Suburban Railway");
                put(110, "Replacement Rail Service");
                put(111, "Special Rail Service");
                put(112, "Lorry Transport Rail Service");
                put(113, "All Rail Services");
                put(114, "Cross-Country Rail Service");
                put(115, "Vehicle Transport Rail Service");
                put(116, "Rack and Pinion Railway");
                put(117, "Additional Rail Service");
                put(200, "Coach Service");
                put(201, "International Coach Service");
                put(202, "National Coach Service");
                put(203, "Shuttle Coach Service");
                put(204, "Regional Coach Service");
                put(205, "Special Coach Service");
                put(206, "Sightseeing Coach Service");
                put(207, "Tourist Coach Service");
                put(208, "Commuter Coach Service");
                put(209, "All Coach Services");
                put(300, "Suburban Railway Service");
                put(400, "Urban Railway Service");
                put(401, "Metro Service");
                put(402, "Underground Service");
                put(403, "Urban Railway Service");
                put(404, "All Urban Railway Services");
                put(405, "Monorail");
                put(500, "Metro Service");
                put(600, "Underground Service");
                put(700, "Bus Service");
                put(701, "Regional Bus Service");
                put(702, "Express Bus Service");
                put(703, "Stopping Bus Service");
                put(704, "Local Bus Service");
                put(705, "Night Bus Service");
                put(706, "Post Bus Service");
                put(707, "Special Needs Bus");
                put(708, "Mobility Bus Service");
                put(709, "Mobility Bus for Registered Disabled");
                put(710, "Sightseeing Bus");
                put(711, "Shuttle Bus");
                put(712, "School Bus");
                put(713, "School and Public Service Bus");
                put(714, "Rail Replacement Bus Service");
                put(715, "Demand and Response Bus Service");
                put(716, "All Bus Services");
                put(800, "Trolleybus Service");
                put(900, "Tram Service");
                put(901, "City Tram Service");
                put(902, "Local Tram Service");
                put(903, "Regional Tram Service");
                put(904, "Sightseeing Tram Service");
                put(905, "Shuttle Tram Service");
                put(906, "All Tram Services");
                put(1000, "Water Transport Service");
                put(1001, "International Car Ferry Service");
                put(1002, "National Car Ferry Service");
                put(1003, "Regional Car Ferry Service");
                put(1004, "Local Car Ferry Service");
                put(1005, "International Passenger Ferry Service");
                put(1006, "National Passenger Ferry Service");
                put(1007, "Regional Passenger Ferry Service");
                put(1008, "Local Passenger Ferry Service");
                put(1009, "Post Boat Service");
                put(1010, "Train Ferry Service");
                put(1011, "Road-Link Ferry Service");
                put(1012, "Airport-Link Ferry Service");
                put(1013, "Car High-Speed Ferry Service");
                put(1014, "Passenger High-Speed Ferry Service");
                put(1015, "Sightseeing Boat Service");
                put(1016, "School Boat");
                put(1017, "Cable-Drawn Boat Service");
                put(1018, "River Bus Service");
                put(1019, "Scheduled Ferry Service");
                put(1020, "Shuttle Ferry Service");
                put(1021, "All Water Transport Services");
                put(1100, "Air Service");
                put(1101, "International Air Service");
                put(1102, "Domestic Air Service");
                put(1103, "Intercontinental Air Service");
                put(1104, "Domestic Scheduled Air Service");
                put(1105, "Shuttle Air Service");
                put(1106, "Intercontinental Charter Air Service");
                put(1107, "International Charter Air Service");
                put(1108, "Round-Trip Charter Air Service");
                put(1109, "Sightseeing Air Service");
                put(1110, "Helicopter Air Service");
                put(1111, "Domestic Charter Air Service");
                put(1112, "Schengen-Area Air Service");
                put(1113, "Airship Service");
                put(1114, "All Air Services");
                put(1200, "Ferry Service");
                put(1300, "Telecabin Service");
                put(1301, "Telecabin Service");
                put(1302, "Cable Car Service");
                put(1303, "Elevator Service");
                put(1304, "Chair Lift Service");
                put(1305, "Drag Lift Service");
                put(1306, "Small Telecabin Service");
                put(1307, "All Telecabin Services");
                put(1400, "Funicular Service");
                put(1401, "Funicular Service");
                put(1402, "All Funicular Service");
                put(1500, "Taxi Service");
                put(1501, "Communal Taxi Service");
                put(1502, "Water Taxi Service");
                put(1503, "Rail Taxi Service");
                put(1504, "Bike Taxi Service");
                put(1505, "Licensed Taxi Service");
                put(1506, "Private Hire Service Vehicle");
                put(1507, "All Taxi Services");
                put(1600, "Self Drive");
                put(1601, "Hire Car");
                put(1602, "Hire Van");
                put(1603, "Hire Motorbike");
                put(1604, "Hire Cycle");
                put(1700, "Miscellaneous Service");
                put(1701, "Cable Car");
                put(1702, "Horse-drawn Carriage");
            }});
}
