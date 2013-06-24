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
public class PaloAvailable extends HealthCheck {
    private IPaloControl palo;

    public PaloAvailable(IPaloControl palo) {
        super("PaloAvailable");

        this.palo = palo;
    }

    @Override
    protected Result check() throws Exception {
        if(palo.available())
            return Result.healthy();

        return Result.unhealthy("Palo is not available.");
    }
}