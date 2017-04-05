/**
 * Created by vbaibus on 4/4/17.
 */

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.InvalidJsonException;
import org.junit.Assert;
import org.junit.Test;
import com.jayway.jsonpath.JsonPath;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class TempSpreadTest {

    @Test
    public void testMaxTempSpread() {
        Object document = new Object();
        try {
            document = Configuration.defaultConfiguration().jsonProvider().parse(inputJson);
        } catch (InvalidJsonException e) {
            Assert.fail("Json parsing error: " + e);
        }

        List<Integer> days = JsonPath.read(document, "$.days[*].day" );
        List<Number> high = JsonPath.read(document, "$.days[*].high" );
        List<Number> low = JsonPath.read(document, "$.days[*].low" );

        HashMap<Integer, Double> spreadByDay = new HashMap<Integer, Double>();
        int iterator = 0;
        Double maxSpread = 0.0;
        DecimalFormat df = new DecimalFormat("#.##");

        for (Integer day: days) {

            //Calculating temperature difference for each day
            Double spread = 0.0;
            try {
               spread  = high.get(iterator).doubleValue() - low.get(iterator).doubleValue();
            } catch (ArithmeticException e) {
                Assert.fail("Exception: " + e);
            }

            //Rounding double to 2 decimal places
            spread = Double.valueOf(df.format(spread));

            if (iterator == 0) {
                maxSpread = spread;
                iterator++;
                continue; //Setting initial value of maxSpread and moving to second iteration
            }

            int result = Double.compare(spread, maxSpread); //Doubles can't be compared using <>==

            if (result > 0) { //spread is greater then maxSpread
                maxSpread = spread;
                spreadByDay.clear();

                spreadByDay.put(day, spread);
                //This is needed as there might be more then one day with maxSpread in the same month
            } else if (result == 0) {  //both are equal
                spreadByDay.put(day, spread);
            }

            iterator++;
        }
        Assert.assertTrue("Maximum Temp Spread collection is empty.", spreadByDay.size() > 0);
        System.out.println("Maximum temperature difference is being observed on the following days:" + spreadByDay);
    }


    protected String inputJson = "{ \"month\": \"September\",\n" +
        "  \"year\": 2016,\n" +
        "  \"days\": [\n" +
        "    {\n" +
        "      \"day\": 13,\n" +
        "      \"high\": 90.7,\n" +
        "      \"ave\": 81.9,\n" +
        "      \"low\": 73.2\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 14,\n" +
        "      \"high\": 89.6,\n" +
        "      \"ave\": 82.2,\n" +
        "      \"low\": 74.8\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 15,\n" +
        "      \"high\": 92.1,\n" +
        "      \"ave\": 82.6,\n" +
        "      \"low\": 73\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 16,\n" +
        "      \"high\": 93,\n" +
        "      \"ave\": 83.8,\n" +
        "      \"low\": 74.5\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 17,\n" +
        "      \"high\": 92.5,\n" +
        "      \"ave\": 83.6,\n" +
        "      \"low\": 74.7\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 18,\n" +
        "      \"high\": 94.5,\n" +
        "      \"ave\": 85.3,\n" +
        "      \"low\": 76.1\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 19,\n" +
        "      \"high\": 95.2,\n" +
        "      \"ave\": 85.2,\n" +
        "      \"low\": 75.2\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 20,\n" +
        "      \"high\": 93.6,\n" +
        "      \"ave\": 84.2,\n" +
        "      \"low\": 74.8\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 21,\n" +
        "      \"high\": 90.7,\n" +
        "      \"ave\": 82.8,\n" +
        "      \"low\": 75\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 22,\n" +
        "      \"high\": 90.9,\n" +
        "      \"ave\": 80.9,\n" +
        "      \"low\": 70.9\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 23,\n" +
        "      \"high\": 89.4,\n" +
        "      \"ave\": 80.5,\n" +
        "      \"low\": 75\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 24,\n" +
        "      \"high\": 90.5,\n" +
        "      \"ave\": 80.8,\n" +
        "      \"low\": 76.6\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 25,\n" +
        "      \"high\": 84.4,\n" +
        "      \"ave\": 76.1,\n" +
        "      \"low\": 71.1\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 26,\n" +
        "      \"high\": 72.7,\n" +
        "      \"ave\": 69.9,\n" +
        "      \"low\": 66.2\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 27,\n" +
        "      \"high\": 76.5,\n" +
        "      \"ave\": 70.1,\n" +
        "      \"low\": 66.2\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 28,\n" +
        "      \"high\": 85.1,\n" +
        "      \"ave\": 73.7,\n" +
        "      \"low\": 64\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 29,\n" +
        "      \"high\": 81.7,\n" +
        "      \"ave\": 71.6,\n" +
        "      \"low\": 63.5\n" +
        "    },\n" +
        "    {\n" +
        "      \"day\": 30,\n" +
        "      \"high\": 77.7,\n" +
        "      \"ave\": 68.9,\n" +
        "      \"low\": 59.2\n" +
        "    }\n" +
            "    {\n" +
            "      \"day\": 31,\n" +
            "      \"high\": 85.1,\n" +
            "      \"ave\": 73.7,\n" +
            "      \"low\": 64\n" +
            "    },\n" +
        "  ]\n" +
        "}";

}