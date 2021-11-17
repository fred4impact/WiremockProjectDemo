package WiremockSetup;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class setupWiremockServer {

    private RequestSpecification reqSpec;


    @Rule
    public WireMockRule wmRule = new WireMockRule(9876);


    @Before
    public void setUpSpecification(){

        reqSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9876)
                .build();

    }



    public void createSubForPostRequest(){

    /*************************************************
    Create sub that will respond to a post
    request /artist/01 with an Http status code 200
    stubFor(post(urlEqualTo("/artist/01")).willReturn(aResponse().withStatus(200)));
    ************************************* *************/

        stubFor(post(urlEqualTo("/artist/01"))
                .willReturn(aResponse()
                        .withStatus(200)
                ));

    }

    @Test
    public void testPostArtist_Scenario(){

          // Add the stub method here before writing the test
           createSubForPostRequest();

           given()
                .spec(reqSpec)
           .when()
                .post("/artist/01")
           .then()
                .assertThat()
                .statusCode(200)
                .log().all();

    }



}
