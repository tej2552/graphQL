package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Utils {
    public JsonNode readJson(String file, String keyPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode nodes = mapper.readTree(new File(file));

        if(keyPath != null && !keyPath.isBlank()){
            String[] keys = keyPath.split("\\.");

            for (String key : keys){
                nodes = nodes.path(key);
            }
        }

        return nodes;
    }

    public boolean scanSchema(String nodeName, String fieldName) throws IOException {
        JsonNode nodes = readJson("src/main/java/schema.json", "");
        boolean result = false;

        for(JsonNode field : nodes.path(nodeName)){
            if(field.asText().equals(fieldName)){
                result = true;
            }
        }

        return result;
    }
}
