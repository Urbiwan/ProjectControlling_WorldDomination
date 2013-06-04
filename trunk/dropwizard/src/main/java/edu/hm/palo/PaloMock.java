package edu.hm.palo;

import edu.hm.model.analyze.impl.Data;
import edu.hm.model.bookings.IAccountingData;
import edu.hm.model.bookings.IEntry;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 03.06.13
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class PaloMock implements IPaloControl {

    @Override
    public Object compute(IAccountingData data) {
        float actifity = 0.0f;
        float faktActivity = 0.0f;
        float efficiency = 0.0f;
        float totalQuantity = 0.0f;
        float costs = 0.0f;
        float benefit = 0.0f;
        float illnessRate = 0.0f;

        for(IEntry entry : data.getBookings()) {
            if(!entry.getAccount().equals("KRANK")
                    && !entry.getAccount().equals("URLAUB"))
                actifity += entry.getHours();
            if(entry.isFakt())
                faktActivity += entry.getHours();

            if(entry.isFakt())
                totalQuantity += entry.getCostRate() * entry.getHours();

            costs += entry.getCostLimit() * entry.getHours();
            if(entry.getAccount().equals("KRANK"))
                illnessRate += entry.getHours();
        }

        efficiency = faktActivity / actifity;
        benefit = totalQuantity - costs;
        illnessRate = illnessRate / actifity;

        return new Data(actifity, faktActivity, efficiency,
                totalQuantity, costs, benefit, illnessRate);
    }
}
