package Isolado;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.http.ContentType;
import org.junit.Test;
import Pojo.Usuario;


public class DesafioTest {

    @Test
    public void Testcadastro() {
        //configuraçoes de parametros restassured
        Usuario name = new Usuario();
        name.setname("jeremias");
        name.setJob("analista de qa");

        given().log().all()
                .contentType(ContentType.JSON)

                .body(name)

                .when().post("https://reqres.in/api/users")
                .then().
                assertThat().statusCode(201)
                .body("name", equalTo("jeremias"))
                .body("job", equalTo("analista de qa"));
    }


    @Test
    public void TestcadastroValidacaocontrato() {
        //configuraçoes de parametros restassured
        Usuario name = new Usuario();
        name.setname("jeremias");
        name.setJob("analista de qa");

        given().log().all()
                .contentType(ContentType.JSON)

                .body(name)

                .when().post("https://reqres.in/api/users")
                .then().assertThat().statusCode(201).

                body(matchesJsonSchemaInClasspath("Scheema/PostvalidacaodeContrato.json"));
    }
}
