package edu.hm.model.analyze.impl;

import edu.hm.model.analyze.IData;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 03.06.13
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public class Data implements IData {
    private float actifity;
    private float faktActivity;
    private float efficiency;
    private float totalQuantity;
    private float costs;
    private float benefit;
    private float illnessRate;

    public Data() {

    }

    public Data(float actifity, float faktActivity, float efficiency, float totalQuantity, float costs, float benefit, float illnessRate) {
        this.actifity = actifity;
        this.faktActivity = faktActivity;
        this.efficiency = efficiency;
        this.totalQuantity = totalQuantity;
        this.costs = costs;
        this.benefit = benefit;
        this.illnessRate = illnessRate;
    }

    @Override
    public float getActifity() {
        return actifity;
    }

    @Override
    public float getFaktActivity() {
        return faktActivity;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getTotalQuantity() {
        return totalQuantity;
    }

    @Override
    public float getCosts() {
        return costs;
    }

    @Override
    public float getBenefit() {
        return benefit;
    }

    @Override
    public float getIllnessRate() {
        return illnessRate;
    }
}
