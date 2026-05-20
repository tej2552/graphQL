import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Payload {

    Utils utils = new Utils();

    public Map<String, Object> buildPayload(String str) throws IOException {
        Map<String, Object> payload = new HashMap<>();

        payload.put("query", extractQuery(str));
        payload.put("variables", constructVariablePayload(str));

        return payload;
    }

    public String extractQuery(String str) throws IOException {
        String content = Files.readString(Path.of("src/main/java/queries/queries.graphql"));

        String[] blocks = content.split("#name:");


        for(String block : blocks){
            block = block.trim();
            if(block.trim().startsWith(str)){
                String[] lines = block.split("\n", 2);
                return lines[1].trim();
            }
        }

        throw new RuntimeException("Query Not Found");
    }

    public Map<String, Object> getVariableCandidatesMap(String query) throws IOException {
        String q = extractQuery(query);
        String queryLine = q.split("\\{", 2)[0];
        String queryPortion = queryLine.split("\\(")[1].split("\\)")[0];

        if(queryPortion.trim().isEmpty()){
            return null;
        }

        Map<String, Object> variables = new HashMap<>();

        String[] variableCandidates = queryPortion.split("[\n,]");;

        for(String variableCandidate : variableCandidates){
            String value = variableCandidate.split("\\$")[1];
            variables.put(value.split(":")[0], value.split(":")[1]);
        }

        return variables;
    }

    public ArrayList<String> getVariables(String query) throws IOException {
        return new ArrayList<>(getVariableCandidatesMap(query).keySet());
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> constructVariablePayload(String query) throws IOException {
        JsonNode node = utils.readJson("src/main/java/variables/variables.json", "");

        return (Map<String, Object>) new ObjectMapper().convertValue(node.path(query), Map.class);
    }
}
