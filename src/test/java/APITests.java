import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITests {

    SpecBuilder specBuilder = new SpecBuilder();

    @Test
    public void shouldFetchData(){
        RestAssured.given()
                .spec(specBuilder.buildRequestSpec());
    }
}
