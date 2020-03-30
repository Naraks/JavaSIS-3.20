package com.github.naraks.weather;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class CodeErrorsResponseHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode() != HttpStatus.OK;
    }

    @Override
    public void handleError(ClientHttpResponse response) {
        try {
            switch (response.getStatusCode()) {
                case NOT_FOUND:
                    throw new RuntimeException("Incorrect date format. Use 'yyyy-MM-dd'");
                default:
                    throw new RuntimeException(response.getStatusText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
