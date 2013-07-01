package edu.hm.dropwizard;

import static org.junit.Assert.*;

import com.yammer.metrics.core.HealthCheck;
import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONResponse;
import edu.hm.model.analyze.AnalyzeConverter;
import edu.hm.model.bookings.BookingFactory;
import edu.hm.palo.IPaloControl;
import edu.hm.palo.PaloAccess;
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

	private static final float ACCURACY = 1f / 10000f;

    private IPaloControl palo;

    public TestPaloMock() {
      palo = new PaloAccess();
    }

    @Test
    public void simpleTest() {
        JSONResponse response = AnalyzeConverter.convert(palo.compute(BookingFactory.create(getDummyRequest())));

		assertEquals("Palo seems not to work properly.", 20f, response.getActivity(), 20f * ACCURACY);
		assertEquals("Palo seems not to work properly.", 16f, response.getFaktActivity(), 16f * ACCURACY);
        assertEquals("Palo seems not to work properly.", 0.8f, response.getEfficiency(), 0.8f * ACCURACY);
        assertEquals("Palo seems not to work properly.", 48000f, response.getTotalQuantity(), 48000f * ACCURACY);
        assertEquals("Palo seems not to work properly.", 2480f, response.getCosts(), 2480f * ACCURACY);
        assertEquals("Palo seems not to work properly.", 45520f, response.getBenefit(), 45520f * ACCURACY);
        assertEquals("Palo seems not to work properly.", 0.1f, response.getIllnessRate(), 0.1f * ACCURACY);
    }

    private JSONRequest getDummyRequest() {
        JSONChild[] childs = new JSONChild[4];
        int currentIndex = 0;

        JSONChild child = new JSONChild(10, "Hans", 2, 8, 1, 2013, "PCS", "A-Team", "Dropwizard", true, 150, 5000);
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
