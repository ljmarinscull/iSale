package com.webproject.isale.utils;

public class Constants {
    private static final String URL_API_BASE = "/api";
    private static final String URL_API_VERSION = "/v1";
    private static final String URL_BASE = URL_API_BASE + URL_API_VERSION;

    //Auth
    public static final String URL_BASE_AUTH = URL_BASE + "/auth";
    public static final String URL_BASE_LOGIN = URL_BASE_AUTH +"/login";
    public static final String URL_BASE_SIGNUP = URL_BASE_AUTH +"/signup";
    public static final String URL_BASE_LOGOUT = URL_BASE_AUTH +"/logout";

    //Users
    public static final String URL_BASE_UPDATE_PROFILE = URL_BASE +"/user-profile";



    //Product
    public static final String URL_BASE_PRODUCTS = URL_BASE + "/products";
    public static final String URL_BASE_POPULARS_PRODUCTS = URL_BASE + "/populars-products";
    public static final String URL_BASE_USER_PRODUCTS = URL_BASE + "/user-products";
    public static final String URL_BASE_DELETE = URL_BASE_PRODUCTS +"/{id}";
    public static final String URL_BASE_PRODUCT = URL_BASE_PRODUCTS +"/{id}";
    public static final String URL_BASE_PUBLIC_PRODUCT = URL_BASE_PRODUCTS +"-public/{id}";
    public static final String URL_BASE_VIEW = URL_BASE_PRODUCTS +"/{id}/view";
    public static final String URL_BASE_PRODUCT_IMAGE = URL_BASE_PRODUCTS +"/image/{fileName}";


}
