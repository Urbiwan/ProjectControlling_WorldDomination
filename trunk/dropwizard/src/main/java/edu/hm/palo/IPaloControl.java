package edu.hm.palo;

import edu.hm.model.bookings.IAccountingData;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 03.06.13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public interface IPaloControl {

    public Object compute(IAccountingData data);
}
