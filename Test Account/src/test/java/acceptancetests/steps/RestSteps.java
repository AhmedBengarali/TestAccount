package acceptancetests.steps;

import acceptancetests.base.DriverUtil;
import acceptancetests.base.EncryptionHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestSteps implements En {

    private static final Logger LOG = LoggerFactory.getLogger(RestSteps.class);

    final ValidatableResponse[] response = new ValidatableResponse[1];
    String user = new String();
    String password = new String();
    String name = new String();
    String scope = new String();
    String product = new String();
    String evaluatioversion_id = new String();
    String encryptionPassword = "PGAEncryptionPwd";

    public RestSteps() {

    }

    @Given("^I use the REST API$")
    public void iUseTheRestApi() {
        RestAssured.baseURI = DriverUtil.getTargetRestUrl();
        //record the amount of total entries before the start of test execution

    }

    @Then("^the entry disappears from the list of entries$")
    public void theEntryDissapearsFromList() {
        LOG.info("It disappears!!!");
    }

    @Then("^I get the returncode (\\d+)$")
    public void iGetTheReturnCode(Integer returnCode) {
        LOG.info("Returncode " + returnCode);
        response[0].statusCode(returnCode);
    }

    @Then("^the json data matches to schema \\\"([^\\\"]*)\\\"$")
    public void theSchemaMatches(String filename) {
        LOG.debug("The json data matches to schema '" + filename + "'");
        response[0].assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/" + filename));
    }

    public int getTotalEntriesREST() {
        RestAssured.baseURI = DriverUtil.getTargetRestUrl();
        return given().relaxedHTTPSValidation("TLS").contentType("application/json").when().get("/entry/amount").getBody().as(Integer.class);
    }

    @Given("^I use user \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iUseUserAndPassword(String user, String password) throws Exception {
        this.user = user;
        this.password = password;
        LOG.info(EncryptionHelper.encrypt(encryptionPassword, password));
    }

    @Given("^I create a new PGA$")
    public void iCreateNewPGA() {

        String urlSuffix = "/evaluations";

        // build payload
        String jsonData = this.name + this.product + this.scope;

        response[0] = given().contentType(ContentType.JSON).auth().preemptive().basic(
                user, password).when().body(jsonData).
                post(urlSuffix).then(); //.log().all();

    }

    @Given("^I select PGA-name \"([^\"]*)\"$")
    public void addPgaName(String name) {
        this.name = "{\"name\":\"" + name + "\",";
    }

    @Given("^I select PGA-scope \"([^\"]*)\"$")
    public void addPgaScope(String scope) {
        this.scope = ",\"scope\":\"" + scope + "\"}";
    }

    @Given("^I select product ID \"([^\"]*)\" and product name \"([^\"]*)\"$")
    public void addPgaProduct(String id, String name) {
        this.product = "\"product\":{\"productId\":\"" + id + "\",\"name\":\"" + name + "\"}";
    }

    @Then("^PGA-object is created$")
    public void createPGA() {

        response[0].statusCode(200);
        JsonPath jsonPath = new JsonPath(response[0].contentType(
                ContentType.JSON).extract().response().getBody().asString());
        Map<Object, Object> jsonPatheval = jsonPath.getMap("evaluation");

        Object namePga = jsonPatheval.get("id");
        this.evaluatioversion_id = namePga.toString();
        LOG.info("create new pga with evaluation id = " + this.evaluatioversion_id);

    }

    @When("^I delete a PGA with evaluation id \"([^\"]*)\"$")
    public void iDeletePGAWithEvaluationId(String evaluationId) {
        LOG.debug("Deleting PGA with evaluation id: " + evaluationId);

        response[0] = given().auth().preemptive().basic(user, password).
                delete("/evaluations/" + evaluationId).
                then();//.log().all();
    }

    @When("^I delete  my PGA with PGA Name \\\"([^\\\"]*)\\\"$")
    public void iDeletePGAWithPGAName(String Name) {
        response[0] = given().auth().preemptive().basic(user, password).
                get("/evaluations/my-evaluations?evaluationStatus=IN_PROGRESS").
                then();//.log().all();
        JsonPath jsonPath = new JsonPath(response[0].contentType(
                ContentType.JSON).extract().response().getBody().asString());

        List<Map<String, Object>> evaluationMapList = jsonPath.param("name", Name).get("evaluation");

        for (int i = 0; i < evaluationMapList.size(); i++) {
            if (evaluationMapList.get(i).get("name").equals(Name)) {
                String id = evaluationMapList.get(i).get("id").toString();
                LOG.debug("Found evaluation id to delete: " + id);
                iDeletePGAWithEvaluationId(id);
                break;
            }
        }
    }

    @Then("^PGA-object is deleted$")
    public void pgaDeleted() {

        response[0].statusCode(200);

        LOG.info("create new pga with evaluation id = " + this.evaluatioversion_id);

    }
}
