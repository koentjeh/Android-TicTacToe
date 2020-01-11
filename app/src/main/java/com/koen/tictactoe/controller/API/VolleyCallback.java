package com.koen.tictactoe.controller.API;

public interface VolleyCallback<T> {
    void onSuccess(T response);
    void onError(T error);
}
