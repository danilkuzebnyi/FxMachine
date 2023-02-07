package sk.fxmachine.utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import sk.fxmachine.logging.BasicLogger;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class CallbackFuture extends CompletableFuture<Response> implements Callback {
    public void onResponse(@NotNull Call call, @NotNull Response response) {
        super.complete(response);
    }
    public void onFailure(@NotNull Call call, @NotNull IOException e){
        BasicLogger.error(new RuntimeException("Error while doing Get request"));
    }
}
