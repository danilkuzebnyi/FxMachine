package sk.fxmachine.rest;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sk.fxmachine.logging.BasicLogger;
import sk.fxmachine.utils.CallbackFuture;
import java.util.concurrent.ExecutionException;

public class RestApiAgentImpl implements RestApiAgent {
    private OkHttpClient client;

    @Override
    public String sendGetRequest(String url) {
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        return doRequest(request, "get");
    }

    @Override
    public String sendPostRequest(String url, String jsonData) {
        client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(jsonData, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        return doRequest(request, "post");
    }

    private String doRequest(Request request, String httpMethodName) {
        CallbackFuture future = new CallbackFuture();
        client.newCall(request).enqueue(future);
        Response response = null;
        try {
            response = future.get();
        } catch (InterruptedException | ExecutionException e) {
            BasicLogger.info("Error in execution " + httpMethodName + " request");
        }

        return response != null ? response.toString() : "";
    }
}
