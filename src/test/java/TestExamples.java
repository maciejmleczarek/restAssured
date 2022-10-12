import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class TestExamples {

    @Test
    public void test1() {
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);


    }

    @Test
    public void test2() {
        Response response = get("https://reqres.in/api/users/1");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void test3() {
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().
                statusCode(200).
                body("data[1].id", equalTo(8)).
                log().all();
    }

    @Test
    public void testGet() {
        baseURI = "https://reqres.in/api";
        given()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data[4].first_name", equalTo("George"));
        //         .body("data.first_name", hasItems("George", "Rachel"));
    }

    @Test
    public void testGet2() {
        baseURI = "https://reqres.in/api";
        given()
                .get("/users/642")
                .then()
                .statusCode(200)
               .body("data.first_name", equalTo("George"));
           //      .body("data.first_name", hasItems("George", "Rachel"));
    }

    @Test
    public void testPost() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Rughav");
//        map.put("job", "Teacher");
//        System.out.println(map);
        JSONObject request = new JSONObject();
        //    System.out.println(request);
        request.put("name", "Maciek");
        request.put("job", "Tester");
        System.out.println(request);
        baseURI = "https://reqres.in/api";
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void testPut() {
        JSONObject request = new JSONObject();
        request.put("name", "Maciek");
        request.put("job", "Tester");
        System.out.println(request);
        baseURI = "https://reqres.in/api";
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testPatch() {
        JSONObject request = new JSONObject();        request.put("name", "Maciej");
        request.put("job", "Tester");
        System.out.println(request);
        baseURI = "https://reqres.in/api";
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testDelete() {
        baseURI = "https://reqres.in";
        when()
                .delete("/api/users/2")
                .then()
                .statusCode(204)
                .log()
                .all();
    }
}
