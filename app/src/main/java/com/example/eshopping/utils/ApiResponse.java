package com.example.eshopping.utils;

public class ApiResponse<T>{

    //here is enum method
    public enum Status{
        ONLOADING,
        ONSUCCESS,
        ONERROR,
    };

    private Status status;
    private T data;
    private String error;

    //constructor
    private ApiResponse(Status status, T data , String error){
        this.status = status;
        this.data = data;
        this.error = error;
    }

    //here is the success method
    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(Status.ONSUCCESS, data, null);
    }

    // here is the error method
    public static <T> ApiResponse<T> error(String error){
        return new ApiResponse<>(Status.ONERROR, null, error);
    }

    public static <T> ApiResponse<T> loading(){
        return new ApiResponse<>(Status.ONLOADING,null, null);
    }

    public Status getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getError(){
        return error;
    }
}
