package analysis;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VbbStreamsTest {

    @Test
    public void testFindBVG() throws IOException {
        long bvgId =
                VbbStreams.findAgencyId("Berliner")
                        .orElseThrow(() -> new RuntimeException("BVG not found"));
        Assert.assertEquals(796, bvgId);
    }

    @Test
    public void testRoutesForAgency() throws IOException {
        long count = VbbStreams.routesForAgency(796L).count();
        Assert.assertEquals(206 + 25 + 10 + 6, count);
    }

    @Test
    public void testBike() throws IOException {
        boolean s42Bike = VbbStreams.canUseBikeOnRoute("S42");
        Assert.assertTrue(s42Bike);
    }

    @Test
    public void testGroupByTask() throws IOException {
        HashMap<String, Long> expectedStats = new HashMap<>();
        expectedStats.put("Bus Service", 206L);
        expectedStats.put("Tram Service", 25L);
        expectedStats.put("Urban Railway Service", 10L);
        expectedStats.put("Water Transport Service", 6L);
        Map<String, Long> actualStats = VbbStreams.routeStats(796);
        Assert.assertEquals(expectedStats, actualStats);
    }
}
