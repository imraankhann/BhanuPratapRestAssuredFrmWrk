package utils;

public enum EndPointUrl {


    /*
     * @Author = Imraan Khan
     *This end point url will append the end point url to fix url
     */


     // country end point url

    ADD_COUNTRY("/countries/details"),
    UPDATE_COUNTRY("/countries/update/details"),
    GET_COUNTRY_ALL("/get/all"),
    DELETE_COUNTRY("/country/"),
    GET_COUNTRIES("/countries"),

    //Request response url sample : https://reqres.in/api/users
    ALL_USERS("/api/users"),
    SINGLE_USER("/api/users/2"),
    LIST_RESOURCE("/api/unknown"),
    SINGLE_RESOURCE("/api/unknown/2"),
    CREATE_USER("/api/users"),
    UPDATE_USER("/api/users/2");


    String resourcePath;


    EndPointUrl(String resourcePath){
        this.resourcePath=resourcePath;
    }

    public String getResourcePath(){
        return this.resourcePath;
    }

    public String getResourcePath(String data){
        return this.resourcePath+data;
    }





}
