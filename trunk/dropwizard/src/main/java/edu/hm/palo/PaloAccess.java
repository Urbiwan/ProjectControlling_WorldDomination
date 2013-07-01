package edu.hm.palo;

import edu.hm.model.analyze.impl.Data;
import edu.hm.model.bookings.IEntry;
import edu.hm.model.bookings.model.bookings.impl.*;
import org.palo.api.*;
import edu.hm.model.bookings.IAccountingData;

/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 25.06.13
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public class PaloAccess implements IPaloControl {

	private class TokenAndDB {
		protected Database db;
		protected int token;
	}


	private static final String SERVER = "localhost";
	private static final String PORT = "7921"; // std  = 7921; daniel = 7777;
	private static final String USER = "admin";
	private static final String PASS = "admin";

	private static final String DIM_EMPLOYEE_ID = "empl_id";
	private static final String DIM_EMPLOYEE_NAME = "empl_name";
	private static final String DIM_EMPLOYEE_DEV_TIER = "empl_dev_tier";
	private static final String DIM_MONTH = "month";
	private static final String DIM_YEAR = "year";
	private static final String DIM_PROJECT_NAME = "proj_name";
	private static final String DIM_DEPARTMENT_NAME = "dep_name";
	private static final String DIM_ACCOUNT_NAME = "account_name";
	private static final String DIM_FACT = "fact";
	private static final int AMOUNT_DIMS = 9;

	private static final String CUBE_HOURS = "hours";
	private static final String CUBE_COST_LIMIT = "cost_limit";
	private static final String CUBE_COST_RATE = "cost_rate";
	private static final int AMOUNT_CUBES = 3;

	private static final String TOKEN_DB_PREFIX = "tokendb";

	private static final String ACCOUNT_SICK = "KRANK";
	private static final String ACCOUNT_HOLIDAY = "URLAUB";

	private static final String IS_FACT = "" + true;

	private Connection connect;

	public PaloAccess() {
		this(SERVER, PORT, USER, PASS);
	}

	public PaloAccess(final String server, final String port, final String user, final String pass) {
		ConnectionConfiguration config = new ConnectionConfiguration(server, port);
		config.setUser(user);
		config.setPassword(pass);
		config.setTimeout(10000);
		config.setType(Connection.TYPE_HTTP);
		config.setLoadOnDemand(true);
		connect = ConnectionFactory.getInstance().newConnection(config);
	}

	private TokenAndDB createDB() {
		TokenAndDB ret = new TokenAndDB();
		ret.token = (int) (System.currentTimeMillis() / 1000);
		do {
			ret.token++;
			ret.db = this.openDB(ret.token);
		} while (ret.db != null);
		ret.db = connect.addDatabase(TOKEN_DB_PREFIX + ret.token);
		return ret;
	}

	private Database openDB(final int token) {
		return connect.getDatabaseByName(TOKEN_DB_PREFIX + token);
	}

	private void setupDB(Database db) {
		Dimension[] dimmensions = new Dimension[AMOUNT_DIMS];
		dimmensions[0] = db.addDimension(DIM_EMPLOYEE_ID);
		dimmensions[1] = db.addDimension(DIM_EMPLOYEE_NAME);
		dimmensions[2] = db.addDimension(DIM_EMPLOYEE_DEV_TIER);
		dimmensions[3] = db.addDimension(DIM_MONTH);
		dimmensions[4] = db.addDimension(DIM_YEAR);
		dimmensions[5] = db.addDimension(DIM_PROJECT_NAME);
		dimmensions[6] = db.addDimension(DIM_DEPARTMENT_NAME);
		dimmensions[7] = db.addDimension(DIM_ACCOUNT_NAME);
		dimmensions[8] = db.addDimension(DIM_FACT);
		Cube[] cubes = new Cube[AMOUNT_CUBES];
		cubes[0] = db.addCube(CUBE_HOURS, dimmensions);
		cubes[1] = db.addCube(CUBE_COST_LIMIT, dimmensions);
		cubes[2] = db.addCube(CUBE_COST_RATE, dimmensions);
	}

	private Element getOrMakeElement(final Dimension d, String elementName) {
		Element ret = d.getDefaultHierarchy().getElementByName(elementName);
		if (ret == null)
			ret = d.getDefaultHierarchy().addElement(elementName, 0);
		return ret;
	}

	private Element[] prepareElements(Database db, IEntry entry) {
		Element[] elements = new Element[AMOUNT_DIMS];
		elements[0] = this.getOrMakeElement(db.getDimensionByName(DIM_EMPLOYEE_ID),"" + entry.getEmployee().getId());
		elements[1] = this.getOrMakeElement(db.getDimensionByName(DIM_EMPLOYEE_NAME), entry.getEmployee().getName());
		elements[2] = this.getOrMakeElement(db.getDimensionByName(DIM_EMPLOYEE_DEV_TIER), "" + entry.getEmployee().getDevelopmentTier());
		elements[3] = this.getOrMakeElement(db.getDimensionByName(DIM_MONTH), "" + entry.getMonth());
		elements[4] = this.getOrMakeElement(db.getDimensionByName(DIM_YEAR), "" + entry.getYear());
		elements[5] = this.getOrMakeElement(db.getDimensionByName(DIM_PROJECT_NAME), entry.getProject().getName());
		elements[6] = this.getOrMakeElement(db.getDimensionByName(DIM_DEPARTMENT_NAME), entry.getProject().getDepartment());
		elements[7] = this.getOrMakeElement(db.getDimensionByName(DIM_ACCOUNT_NAME), entry.getAccount());
		elements[8] = this.getOrMakeElement(db.getDimensionByName(DIM_FACT), "" + entry.isFakt());
		return elements;
	}

	private void insertData(Database db, final IAccountingData data) {
		for (IEntry entry : data.getBookings()) {
			Element[] elements = this.prepareElements(db, entry);
			db.getCubeByName(CUBE_HOURS).setData(elements, entry.getHours());
			db.getCubeByName(CUBE_COST_LIMIT).setData(elements, entry.getCostLimit());
			db.getCubeByName(CUBE_COST_RATE).setData(elements, entry.getCostRate());
		}
	}

	private Element[] getDimensionElements(Dimension d) {
		return d.getDefaultHierarchy().getElements();
	}

	private Element[] getDimensionElements(Database db, String dName) {
		return this.getDimensionElements(db.getDimensionByName(dName));
	}

	private Element[][] getAllDimensionsElements(Database db) {
		Element[][] elementFilter = new Element[AMOUNT_DIMS][];
		elementFilter[0] = this.getDimensionElements(db, DIM_EMPLOYEE_ID);
		elementFilter[1] = this.getDimensionElements(db, DIM_EMPLOYEE_NAME);
		elementFilter[2] = this.getDimensionElements(db, DIM_EMPLOYEE_DEV_TIER);
		elementFilter[3] = this.getDimensionElements(db, DIM_MONTH);
		elementFilter[4] = this.getDimensionElements(db, DIM_YEAR);
		elementFilter[5] = this.getDimensionElements(db, DIM_PROJECT_NAME);
		elementFilter[6] = this.getDimensionElements(db, DIM_DEPARTMENT_NAME);
		elementFilter[7] = this.getDimensionElements(db, DIM_ACCOUNT_NAME);
		elementFilter[8] = this.getDimensionElements(db, DIM_FACT);
		return elementFilter;
	}

	private float calcActivity(Database db) {
		Element[][]  elementFilter = this.getAllDimensionsElements(db);
		Element[] accountFilter = new Element[elementFilter[7].length];
		int  j = 0;
		for (int i = 0; i < accountFilter.length; i++) {
			String elementName = elementFilter[7][i].getName();
			if (elementName.compareToIgnoreCase(ACCOUNT_SICK) != 0
				&& elementName.compareToIgnoreCase(ACCOUNT_HOLIDAY) != 0) {
				accountFilter[j++] = elementFilter[7][i];
			}
		}
		elementFilter[7] = new Element[j];
		for (int i = 0; i < j; i++) {
			elementFilter[7][i] = accountFilter[i];
		}

		float activity = 0.0f;

		Cube hours = db.getCubeByName(CUBE_HOURS);

		for (Object o : hours.getDataArray(elementFilter)) {
			if (o.toString().length() == 0) continue;
			activity += Float.parseFloat(o.toString());
		}

		return activity;
	}

	private float calcFactActivity(Database db) {
		Element[][]  elementFilter = this.getAllDimensionsElements(db);
		Element[] factFilter = new Element[] {
				db.getDimensionByName(DIM_FACT).getDefaultHierarchy().getElementByName(IS_FACT)};

		elementFilter[8] = factFilter;

		float activity = 0;

		Cube hours = db.getCubeByName(CUBE_HOURS);

		for (Object o : hours.getDataArray(elementFilter)) {
			if (o.toString().length() == 0) continue;
			activity += Float.parseFloat(o.toString());
		}

		return activity;
	}

	private float calcTotalQuantity(Database db) {
		Element[][]  elementFilter = this.getAllDimensionsElements(db);
		elementFilter[8] = new Element[] {
				db.getDimensionByName(DIM_FACT).getDefaultHierarchy().getElementByName(IS_FACT)};

		float activity = 0;

		Cube hours = db.getCubeByName(CUBE_HOURS);
		Cube costRate = db.getCubeByName(CUBE_COST_RATE);

		Object[] hourData = hours.getDataArray(elementFilter);
		Object[] costRateData = costRate.getDataArray(elementFilter);

		for (int i = 0; i < hourData.length; i++) {
			if (hourData[i].toString().length() > 0 && costRateData[i].toString().length() > 0) {
				float product = (float) (Float.parseFloat(hourData[i].toString()) * Float.parseFloat(costRateData[i].toString()));
				activity += product;
			}
		}

	return activity;
	}

	private float calcCosts(Database db) {
		Element[][]  elementFilter = this.getAllDimensionsElements(db);

		float activity = 0;

		Cube hours = db.getCubeByName(CUBE_HOURS);
		Cube costLimit = db.getCubeByName(CUBE_COST_LIMIT);

		Object[] hourData = hours.getDataArray(elementFilter);
		Object[] costLimitData = costLimit.getDataArray(elementFilter);

		for (int i = 0; i < hourData.length; i++) {
			if (hourData[i].toString().length() > 0 && costLimitData[i].toString().length() > 0) {
				float product = (float) (Float.parseFloat(hourData[i].toString()) * Float.parseFloat(costLimitData[i].toString()));
				activity += product;
			}
		}

		return activity;
	}

	private float calcIllnesHours(Database db) {
		Element[][]  elementFilter = this.getAllDimensionsElements(db);
		Element[] illnesFilter = new Element[] {
				db.getDimensionByName(DIM_ACCOUNT_NAME).getDefaultHierarchy().getElementByName(ACCOUNT_SICK)};

		elementFilter[7] = illnesFilter;

		float activity = 0;

		Cube hours = db.getCubeByName(CUBE_HOURS);

		for (Object o : hours.getDataArray(elementFilter)) {
			if (o.toString().length() <= 0) continue;
			System.out.println(o.toString());
			activity += Float.parseFloat(o.toString());
		}

		return activity;
	}

	private Data calculateData(Database db) {
		float activity = this.calcActivity(db);
		float faktActivity = this.calcFactActivity(db);
		float efficiency = faktActivity / activity;
		float totalQuantity = this.calcTotalQuantity(db);
		float costs = this.calcCosts(db);
		float benefit = totalQuantity - costs;
		float illnessRate = calcIllnesHours(db) / activity;

		return new Data(activity, faktActivity, efficiency, totalQuantity, costs, benefit, illnessRate);
	}

	@Override
	public Data compute(final IAccountingData data) {
		int token = this.upload(data);
		return this.compute(token);  //TODO testing
	}

	@Override
	public Data compute(final int token) {
		Database db = this.openDB(token);
		Data data = this.calculateData(db);
		return data;  //To change body of implemented methods use File | Settings | File Templates.
	}

    @Override
    public int upload(final IAccountingData data) {
		TokenAndDB tad = this.createDB();
		int token = tad.token;
		Database db = tad.db;
		this.setupDB(db);

		this.insertData(db, data);

		return token;  //TODO testing
	}

	@Override
	public IAccountingData getData(final int token) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean available() {
		return connect.isConnected();
	}
}
