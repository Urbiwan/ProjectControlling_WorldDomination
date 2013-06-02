package edu.hm.dropwizard.health;

import com.yammer.metrics.core.HealthCheck;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 02.06.13
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
public class ComputeHealth extends HealthCheck {
    public ComputeHealth() {
        super("ComputeHealth");
    }

    @Override
    protected Result check() throws Exception {
        // TODO implement real logic here.
        //    return Result.unhealthy("template doesn't include a name");
        return Result.healthy();
    }
}