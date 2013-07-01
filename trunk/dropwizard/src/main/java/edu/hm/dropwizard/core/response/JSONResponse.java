package edu.hm.dropwizard.core.response;

public class JSONResponse {
    /**
     * Summe aller gebuchten Stunden ohne KRANK und URLAUB.
     * Leistung
     */
    private float actifity;
    /**
     * Summe der STunden, die fakturierbar sind.
     * fakturisierbare Leistung.
     */
    private float faktActivity;
    /**
     * fakLeistung / Leistung = auslastung
     */
    private float efficiency;
    /**
     * Die summe des produkts aus verrechnungssatz und stunden
     * uber alle fakt. Buchungen
     */
    private float totalQuantity;
    /**
     * Die summe des Produkts aus Grenzkosten und Stunden ueber
     * alle Buchungen
     */
    private float costs;
    /**
     * Leistungsumsatz - kosten
     */
    private float benefit;
    /**
     * Summe der Stunden ueber alle Buchungen, die auf ein KRANK
     * Konto gebucht wurden/L
     */
    private float illnessRate;
	
	public JSONResponse() {

	}

    public JSONResponse(float actifity, float faktActivity, float efficiency, float totalQuantity, float costs, float benefit, float illnessRate) {
        this.actifity = actifity;
        this.faktActivity = faktActivity;
        this.efficiency = efficiency;
        this.totalQuantity = totalQuantity;
        this.costs = costs;
        this.benefit = benefit;
        this.illnessRate = illnessRate;
    }

    public float getActivity() {
        return actifity;
    }

    public float getFaktActivity() {
        return faktActivity;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public float getTotalQuantity() {
        return totalQuantity;
    }

    public float getCosts() {
        return costs;
    }

    public float getBenefit() {
        return benefit;
    }

    public float getIllnessRate() {
        return illnessRate;
    }
}
