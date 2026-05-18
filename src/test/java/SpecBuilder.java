import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    RequestSpecification requestSpecBuilder;

    public RequestSpecification buildRequestSpec(){
        requestSpecBuilder = RestAssured.given();
        return requestSpecBuilder
                .log()
                .all()
                .baseUri("http://localhost:4000/")
                .basePath("/graphql")
                .contentType(ContentType.JSON);

    }
}
