package com.mine.data.exception;

import com.mine.data.net.HttpResponse;

public class ResponseException extends Exception {
    public static final int ERROR_CODE_NEED_LOGIN = -1000;
    public static final int ERROR_CODE_NEED_PERFECT_PROFILE = -1010;
    public static final int ERROR_CODE_NEED_THIRD_PARTY_BIND = -1020;
    public static final int STATUS_CODE_SUCCESS = 0;

    public ResponseException(HttpResponse response) {
        super(response.getStatusMessage());
    }


}
