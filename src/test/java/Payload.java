import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Payload {

    public Map<String, Object> buildPayload(String str, Map<String, String> variables) throws IOException {
        Map<String, Object> payload = new HashMap<>();

        payload.put("query", extractQuery(str));
        payload.put("variables", variables);

        return payload;
    }

    public String extractQuery(String str) throws IOException {
        String content = Files.readString(Path.of("src/main/java/queries/queries.graphql"));

        String[] blocks = content.split("#name:");


        for(String block : blocks){
            block = block.trim();
            if(block.trim().startsWith(str)){
                String[] lines = block.split("\n", 3);
                return lines[2].trim();
            }
        }

        throw new RuntimeException("Query Not Found");
    }

    public String extractVariables(String str) throws IOException {
        String content = Files.readString(Path.of("src/main/java/queries/queries.graphql"));

        String[] blocks = content.split("#name:");

        for(String block : blocks){
            block = block.trim();
            if(block.trim().startsWith(str)){
                String[] lines = block.split("\n", 3);
                return lines[1].trim().split(":")[1];
            }
        }

        throw new RuntimeException("Query Not Found");
    }
}
