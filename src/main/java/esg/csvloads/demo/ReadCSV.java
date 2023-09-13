package esg.csvloads.demo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ReadCSV{

    public List<JSONObject> getJSONFromCSV(String fileName) {

        Resource resource = new FileSystemResource(fileName);
        List<JSONObject> result = null;
        if (resource.exists()) {
            try (CSVReader reader = new CSVReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

                List<String[]> r = reader.readAll();
                String[] headers = r.get(0);
                r.remove(0);
                String[] validation = {"customerRef",
                        "customerName",
                        "address1",
                        "address2",
                        "town",
                        "county",
                        "country",
                        "postcode"};


                assert Arrays.equals(headers, validation) : "Column headers do not match the required format";

                result = new ArrayList<>(r.size());

                for (String[] row : r) {
                    JSONObject tempObj = new JSONObject();
                    for (int j = 0; j < headers.length; j++) {
                        tempObj.put(headers[j], row[j]);

                    }

                    result.add(tempObj);
                }

            } catch (IOException | CsvException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            return result;
        } else {
            System.err.println("The specified CSV file does not exist: " + fileName);
            return result;
            }
        }

    }

