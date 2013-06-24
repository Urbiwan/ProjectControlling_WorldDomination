package edu.hm.dropwizard;

import static org.junit.Assert.*;

import com.yammer.metrics.core.HealthCheck;
import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONResponse;
import edu.hm.model.analyze.AnalyzeConverter;
import edu.hm.model.bookings.BookingFactory;
import edu.hm.palo.IPaloControl;
import edu.hm.palo.PaloMock;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 24.06.13
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
public class TestPaloMock {

    private IPaloControl palo;

    public TestPaloMock() {
      palo = new PaloMock();
    }

    @Test
    public void simpleTest() {
        JSONResponse response = AnalyzeConverter.convert(palo.compute(BookingFactory.create(getDummyRequest())));

        assertEquals("Palo seems not to work properly.",response.getActifity(), 20f,0f);
        assertEquals("Palo seems not to work properly.", response.getFaktActivity(), 16f, 0);
        assertEquals("Palo seems not to work properly.", response.getEfficiency(), 0.8f, 0);
        assertEquals("Palo seems not to work properly.", response.getTotalQuantity(), 48000f, 0);
        assertEquals("Palo seems not to work properly.", response.getCosts(), 2480f, 0);
        assertEquals("Palo seems not to work properly.", response.getBenefit(), 45520f, 0);
        assertEquals("Palo seems not to work properly.", response.getIllnessRate(), 0.1f, 0);
    }

    private JSONRequest getDummyRequest() {
        JSONChild[] childs = new JSONChild[4];
        int currentIndex = 0;

        JSONChild child = new JSONChild(10, "Hans", 2, 8, 1,2013, "PCS", "A-Team", "Dropwizard", true, 150, 5000);
        childs[currentIndex++] = child;

        child = new JSONChild(10, "Hans", 2, 4, 1, 2013, "PCS", "A-Team", "Draw", false, 70, 300);
        childs[currentIndex++] = child;

        child = new JSONChild(20, "Fritz", 5, 8, 1, 2013, "PCS", "A-Team", "Palo", true, 100, 1000);
        childs[currentIndex++] = child;

        child = new JSONChild(30, "Kurt", 1, 2, 1, 2013, "COMMON", "COMPANY", "KRANK", false, 100, 900);
        childs[currentIndex++] = child;

        return  new JSONRequest(childs);
    }

}
