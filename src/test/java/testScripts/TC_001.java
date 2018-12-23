package testScripts;

import java.util.ArrayList;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;

import PojoObjects.Data;
import PojoObjects.GetUsersObj;
import commonMethods.WebserviceCommonMethods;
import utils.EndPointUrl;
import utils.ParseJson;
import utils.Url;


public class TC_001 {
	Response response;

	ArrayList<Integer> userId;
	ArrayList<Object> firstName;
	ArrayList<Object> avatar;
	ArrayList<Object> lastName;

	@BeforeClass
	public void setUp() {
		userId = new ArrayList<Integer>();
		firstName = new ArrayList<Object>();
		avatar = new ArrayList<Object>();
		lastName = new ArrayList<Object>();


	}



	/*@DataProvider(name = "getUserById")
    public Object[][] getUserId() {
        Object[][] result = new Object[userId.size()][3];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = userId.get(i);
            result[i][1] = firstName.get(i);
            result[i][2] = avatar.get(i);
            result[i][3] = lastName.get(i);

        }
        return result;
    }*/
	//@Test (dataProvider = "getUserById")
	// @Test
	//public void verifyGetAllUsers(String userId,String firstName, String avatar, String lastName){
	public void verifyGetAllUsers(){
		final Gson gson=new GsonBuilder().create();
		Data data;
		final String url= Url.fixReqResUrl+ EndPointUrl.ALL_USERS.getResourcePath();
		response= WebserviceCommonMethods.get(url);
		if(response.getStatusCode()==200){
			data=gson.fromJson(response.getBody().asString(),Data.class);
			System.out.println("Printing avatar: "+data.getAvatar());
			System.out.println("Printing firstName : "+data.getFirstName());
			System.out.println("Printing lastName : "+data.getLastName());
			gson.fromJson(response.getBody().asString(),GetUsersObj.class);
			/*Assert.assertEquals(userId,data.getId());
            Assert.assertEquals(firstName,data.getFirstName());
            Assert.assertEquals(avatar,data.getAvatar());
            Assert.assertEquals(lastName,data.getLastName());*/
			System.out.println("========================================");

		}
		final String getAllUsersResp=response.getBody().asString();
		System.out.println("getCountiresApiResponse : ---------->> "+getAllUsersResp);
		final JSONObject getALlUsersJsonResp=new JSONObject(getAllUsersResp);
		final String totalRecords= ParseJson.getValueByJPath(getALlUsersJsonResp,"/total");
		System.out.println("Printing total records in the req resp  response : "+totalRecords);
		final String totalPages=ParseJson.getValueByJPath(getALlUsersJsonResp,"/total_pages");
		System.out.println("Printing total pages in req resp : "+totalPages);
		final String dataofZero=ParseJson.getValueByJPath(getALlUsersJsonResp,"/data[0]/id");
		System.out.println("Printing data of zero id : "+dataofZero);
		final String dataOfZerofirstName=ParseJson.getValueByJPath(getALlUsersJsonResp,"/data[0]/first_name");
		System.out.println("Printing data of zero first name : "+dataOfZerofirstName);


	}


	@DataProvider(name="addUser")
	public Object[][] adduser(){
		final Object[][] result=new Object[2][3];
		result[0][0]="{\n" +
				"    \"name\": \"imran\",\n" +
				"    \"job\": \"Indian resident\"\n" +
				"}";
		result[0][1]="imran";
		result[0][2]="Indian resident";

		result[1][0]="{\n" +
				"    \"name\": \"Dilip\",\n" +
				"    \"job\": \"Nepal resident\"\n" +
				"}";
		result[1][1]="Dilip";
		result[1][2]="Nepal resident";

		return result;

	}

	@Test(dataProvider = "addUser")
	public void verifyAddUser(final String json,final String userName, final String resident){
		//      public void verifyAddUser(){
		final String url=Url.fixReqResUrl+EndPointUrl.CREATE_USER.getResourcePath();
		response=WebserviceCommonMethods.post(url,json);
		if(response.getStatusCode()==201){
			System.out.println("Printing add user response : "+response);
		}

	}

}
