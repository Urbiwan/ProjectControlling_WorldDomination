package edu.hm.dropwizard;

import static org.junit.Assert.*;

import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;
import edu.hm.dropwizard.excel.ExcelParser;
import junit.framework.Test;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: WEBER
 * Date: 24.06.13
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class TestExcelParser {

    @org.junit.Test
    public void testExcelParser() throws IOException, InvalidFormatException {
        InputStream stream = getClass().getResourceAsStream("/test.xlsx");

        JSONRequest request = ExcelParser.getData(stream);
        JSONRequest archetype = getDummyRequest();

        assertEquals("Count does not fit", request.getEntries().length, 4);

        for(int i = 0; i < request.getEntries().length; i++) {
            JSONChild child = request.getEntries()[i];
            JSONChild ideal = archetype.getEntries()[i];

            assertChild(child, ideal);
        }
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

    private void assertChild(JSONChild child, JSONChild ideal) {
        assertEquals("Ma-Id doesn't match.", child.getEmployeeID(), ideal.getEmployeeID());
        assertEquals("Ma-Id doesn't match.", child.getEmployee(), ideal.getEmployee());
        assertEquals("Ma-Id doesn't match.", child.getEmployeeTier(), ideal.getEmployeeTier());
        assertEquals("Ma-Id doesn't match.", child.getHours(), ideal.getHours(), 0);
        assertEquals("Ma-Id doesn't match.", child.getMonth(), ideal.getMonth());
        assertEquals("Ma-Id doesn't match.", child.getYear(), ideal.getYear());
        assertEquals("Ma-Id doesn't match.", child.getProject(), ideal.getProject());
        assertEquals("Ma-Id doesn't match.", child.getAccount(), ideal.getAccount());
        assertEquals("Ma-Id doesn't match.", child.getDepartment(), ideal.getDepartment());
        assertEquals("Ma-Id doesn't match.", child.isFakt(), ideal.isFakt());
        assertEquals("Ma-Id doesn't match.", child.getCostLimit(), ideal.getCostLimit(), 0);
        assertEquals("Ma-Id doesn't match.", child.getCostRate(), ideal.getCostRate(), 0);
    }
}
