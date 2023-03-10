package create;

import com.google.gson.Gson;
import create.pojo.CreateRequest;
import create.pojo.CreateResponse;
import io.cucumber.java.af.En;
import io.cucumber.java.en.And;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class CreateStepDefinitions {

    private CreateRequest createRequest;
    private CreateResponse createResponse;

    private Response restResponse;

    @Dado("que eu quero criar um novo usuario")
    public void instanciaUsuario(){
        this.createRequest = new CreateRequest();
    }

    @E("insiro o nome {string}")
    public void insereNome(String name){
        this.createRequest.setName(name);
    }

    @E("insiro o job {string}")
    public void insereJob(String job){
        this.createRequest.setJob(job);
    }

    @Quando("eu envio minhas infomacoes")
    public void chamaApi(){

        Gson gson =  new Gson();

        String url = "https://reqres.in/api/users";

        this.restResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(gson.toJson(createRequest))
                .when()
                .post(url)
                .then()
                .assertThat().statusCode(201)
                .extract()
                .response();


        this.createResponse = gson.fromJson(this.restResponse.getBody().asString(), CreateResponse.class);

    }

    @Entao("eu recebo uma resposta")
    public void validaResponse(){
        Assertions.assertNotNull(this.createResponse, "O objeto de resposta nao pode ser nulo");

    }
    @E("valido o status code {string}")
    public void validaStatusCode(String statusCode){
        Assertions.assertEquals(Integer.valueOf(statusCode), this.restResponse.getStatusCode(),
                "O status code retornado esta diferente do esperado");
    }

    @E("recebo o nome {string}")
    public void validaNome(String name){
        Assertions.assertNotNull(this.createResponse.getName(), "O nome nao deveria está nulo");
        Assertions.assertFalse(this.createResponse.getName().isBlank(), "O nome nao deveria está em branco");
        Assertions.assertEquals(name, this.createResponse.getName(), "O nome retornado esta diferente do esperado");
    }

    @E("recebo o job {string}")
    public void validaJob(String job){
        Assertions.assertNotNull(this.createResponse.getJob(), "O job nao deveria está nulo");
        Assertions.assertFalse(this.createResponse.getJob().isBlank(), "O job nao deveria está em branco");
        Assertions.assertEquals(job, this.createResponse.getJob(), "O job retornado esta diferente do esperado");
    }

    @E("recebo um id")
    public void validId(){
        Assertions.assertNotNull(this.createResponse.getId(), "O id nao deveria está nulo");
        Assertions.assertFalse(this.createResponse.getId().isBlank(), "O id nao deveria está em branco");
    }

    @E("recebo uma data de criacao")
    public void validCreateDate(){
        Assertions.assertNotNull(this.createResponse.getCreatedAt(), "A data de criaçao nao deveria está nulo");
        Assertions.assertFalse(this.createResponse.getCreatedAt().isBlank(), "A data de criaçao nao deveria está em branco");
    }

}
