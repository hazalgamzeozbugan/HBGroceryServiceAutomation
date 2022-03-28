import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class RestAssuredTest {


    @Test
    public void getListStock(){
        when()
                .get("https://62409cb119f60987923e509d.mockapi.io/ss/3")
                .then()
                .statusCode(200)
                .body("stock", equalTo(60))
                .time(lessThan(3000L));
    }
    @Test
    public void getListBad() {
        given()
                .when()
                .get("https://62409cb119f60987923e509d.mockapi.io/ss/23")
                .then()
                .statusCode(400);
    }

    @Test
    public void postProduct(){
        HashMap data =new HashMap();
        data.put("id", 14);
        data.put("name", "Fındık");
        data.put("price", 5);
        data.put("stock", 6);
        Response res=
        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://62409cb119f60987923e509d.mockapi.io/ss")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
        String jsonString=res.asString();
        Assert.assertEquals(jsonString.contains("Kayıt başarılı"), true);



    }
}
