package common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by aditya on 06/09/14.
 */
public class Resource {
	@SuppressWarnings("unchecked")
	public static List<String> toLines(String resource_name) throws IOException {
        InputStream stream;
        stream = Resource.class.getResourceAsStream(resource_name);
        return IOUtils.readLines(stream, "UTF-8");
    }

    public static HashMap<String, String> toHashMap(List<String> lines) {
        HashMap<String, String> elem_map = new HashMap<String, String>();
        for(String line : lines) {
            String[] parts = line.split(":");
            elem_map.put(parts[0], parts[1]);
        }
        return elem_map;
    }

	public static HashMap<String, String> propToHashMap(String resource_name)
			throws IOException {
		return toHashMap(toLines(resource_name));
	}

    public static List<HashMap<String, String>> csvToHashMaps(String resource_name) throws IOException {
    	Logger.getGlobal().info("Resource name: " + resource_name);
    	InputStream stream = Resource.class.getResourceAsStream(resource_name);
    	InputStreamReader s_reader = new InputStreamReader(stream);
        CSVReader reader = new CSVReader(s_reader, '|');
        List<String[]> myEntries = (List<String[]>) reader.readAll();
        reader.close();
        String[] keys = myEntries.remove(0);

        return myEntries.stream().map((String[] tokens) ->  {
            HashMap<String, String> record = new HashMap<String, String>();
            for (int i = 0; i < tokens.length; i++) {
                record.put(keys[i], tokens[i]);
            }
            return record;
        }).collect(Collectors.toList());
    }
}
