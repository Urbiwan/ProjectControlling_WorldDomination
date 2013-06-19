package edu.hm.palo;

import edu.hm.dropwizard.core.response.JSONResponse;
import edu.hm.model.bookings.IAccountingData;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 03.06.13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public interface IPaloControl {

    /**
     * Computes data for accounting data.
     * @param data Accounting Data.
     * @return Result.
     */
    public Object compute(IAccountingData data);

    /**
     * Computes data for token. Token must be stored.
     * @param token Token, identifier for stored accouting data.
     * @return Result.
     */
    public Object compute(int token);

    /**
     * Stores accounting data, and return token.
     * @param data Accounting Data.
     * @return Result.
     */
    int upload(IAccountingData data);

    /**
     * Returns stored accounting data.
     * @param token Token, indentifier for stored accounting data.
     * @return Data.
     */
    IAccountingData getData(int token);
}
