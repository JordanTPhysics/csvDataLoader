package esg.csvloads.demo;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CsvReaderTest {

    @Autowired
    public ReadCSV csvReader;

    @Test
    public void CsvFormatTest() {
        List<JSONObject> jsonList = csvReader
                .getJSONFromCSV("C:\\Users\\jorda\\IdeaProjects\\demo\\src\\main\\resources\\static\\csvdata.csv");

        for (JSONObject line : jsonList) {
            String[] keys = JSONObject.getNames(line);
            String[] actuals = {"customerRef",
                    "customerName",
                    "address1",
                    "address2",
                    "town",
                    "county",
                    "country",
                    "postcode"};

            Arrays.sort(actuals);
            Arrays.sort(keys);


            Assert.assertArrayEquals(actuals, keys);

        }
    }
}