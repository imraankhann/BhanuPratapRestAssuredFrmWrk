package testScripts;

import PojoObjects.Data;
import PojoObjects.GetUsersObj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;
import commonMethods.WebserviceCommonMethods;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.EndPointUrl;
import utils.ParseJson;
import utils.Url;


public class TC_001 {
    Response response;

    @BeforeClass
    public void setUp(){

    }

    @Test
    public void verifyGetAllUsers(){
        Gson gson=new GsonBuilder().create();
        Data data;
        GetUsersObj getUsersObj;
        String url= Url.fixReqResUrl+ EndPointUrl.ALL_USERS.getResourcePath();
        response= WebserviceCommonMethods.get(url);
        if(response.getStatusCode()==200){
            data=gson.fromJson(response.getBody().asString(),Data.class);
            System.out.println("Printing avatar: "+data.getAvatar());
            System.out.println("Printing firstName : "+data.getFirstName());
            System.out.println("Printing lastName : "+data.getLastName());
            getUsersObj=gson.fromJson(response.getBody().asString(),GetUsersObj.class);
            System.out.println("========================================");

        }
        String getAllUsersResp=response.getBody().asString();
        System.out.println("getCountiresApiResponse : ---------->> "+getAllUsersResp);
        JSONObject getALlUsersJsonResp=new JSONObject(getAllUsersResp);
        String totalRecords= ParseJson.getValueByJPath(getALlUsersJsonResp,"/total");
        System.out.println("Printing total records in the req resp  response : "+totalRecords);
        String totalPages=ParseJson.getValueByJPath(getALlUsersJsonResp,"/total_pages");
        System.out.println("Printing total pages in req resp : "+totalPages);
        String dataofZero=ParseJson.getValueByJPath(getALlUsersJsonResp,"/data[0]/id");
        System.out.println("Printing data of zero id : "+dataofZero);
        String dataOfZerofirstName=ParseJson.getValueByJPath(getALlUsersJsonResp,"/data[0]/first_name");
        System.out.println("Printing data of zero first name : "+dataOfZerofirstName);


    }
}
