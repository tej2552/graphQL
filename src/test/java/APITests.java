import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class APITests {

    SpecBuilder specBuilder = new SpecBuilder();
    Payload payload = new Payload();

    @Test
    public void shouldFetchData() throws IOException {

        String queryName = "getGameById";

        RestAssured.given()
                .spec(specBuilder.buildRequestSpec())
                .body(payload.buildPayload(queryName)).when()
                .post()
                .then().statusCode(200)
                .log().all();
    }
}
