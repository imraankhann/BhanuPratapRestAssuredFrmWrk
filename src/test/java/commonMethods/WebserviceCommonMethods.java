package commonMethods;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import sun.misc.Request;

public class WebserviceCommonMethods {

    public static Response post(String uRI, String stringJSON){
        RequestSpecification requestSpecification=RestAssured.given().body(stringJSON);
        requestSpecification.contentType(ContentType.JSON);
        Response response=requestSpecification.post(uRI);
        return response;
    }

    public static Response get(String uRI){
        RequestSpecification requestSpecification=RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        Response response=requestSpecification.get(uRI);
        return response;
    }

    public static Response put(String uRI,String stringJSON){
        RequestSpecification requestSpecification=RestAssured.given().body(stringJSON);
        requestSpecification.contentType(ContentType.JSON);
        Response response=requestSpecification.put(uRI);
        return response;
    }
}
