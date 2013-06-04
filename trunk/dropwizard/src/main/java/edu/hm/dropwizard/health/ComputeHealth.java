package edu.hm.dropwizard.health;

import com.yammer.metrics.core.HealthCheck;
import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.core.response.JSONResponse;
import edu.hm.model.analyze.AnalyzeConverter;
import edu.hm.model.bookings.BookingFactory;
import edu.hm.palo.IPaloControl;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 02.06.13
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
public class ComputeHealth extends HealthCheck {
    private IPaloControl palo;

    public ComputeHealth(IPaloControl palo) {
        super("ComputeHealth");

        this.palo = palo;
    }

    @Override
    protected Result check() throws Exception {
        JSONResponse response = AnalyzeConverter.convert(palo.compute(BookingFactory.create(getDummyRequest())));

        if(response.getActifity() != 20f)
            return Result.unhealthy("Palo seems not to work properly.");
        if(response.getFaktActivity() != 16f)
            return Result.unhealthy("Palo seems not to work properly.");
        if(response.getEfficiency() != 0.8f)
            return Result.unhealthy("Palo seems not to work properly.");
        if(response.getTotalQuantity() != 48000f)
            return Result.unhealthy("Palo seems not to work properly.");
        if(response.getCosts() != 2480f)
            return Result.unhealthy("Palo seems not to work properly.");
        if(response.getBenefit() != 45520f)
            return Result.unhealthy("Palo seems not to work properly.");
        if(response.getIllnessRate() != 0.1f)
            return Result.unhealthy("Palo seems not to work properly.");

        return Result.healthy();
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