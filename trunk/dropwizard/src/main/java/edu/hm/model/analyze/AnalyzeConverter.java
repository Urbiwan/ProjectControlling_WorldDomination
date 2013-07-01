package edu.hm.model.analyze;

import edu.hm.dropwizard.core.response.JSONResponse;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 03.06.13
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class AnalyzeConverter {

    public static JSONResponse convert(Object object) {
        if(object instanceof IData)
        {
            IData data = (IData)object;
            return new JSONResponse(data.getActivity(), data.getFaktActivity(), data.getEfficiency(),
                    data.getTotalQuantity(), data.getCosts(), data.getBenefit(), data.getIllnessRate());
        }

        throw new IllegalArgumentException("Unkown Object.");
    }
}
