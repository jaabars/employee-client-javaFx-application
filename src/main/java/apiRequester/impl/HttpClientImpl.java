package apiRequester.impl;

import apiRequester.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Employee;
import models.Position;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;

public class HttpClientImpl implements HttpClient {
    private ObjectMapper objectMapper = new ObjectMapper();
    private OkHttpClient client = new OkHttpClient();
    private String baseCategoryUrl = "http://localhost:8080/api/v1/category/";
    @Override
    public List<Position> getAllPositions() {
   /*     Request request = new Request.Builder()
                .url(baseCategoryUrl+"all")
                .build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();

            List<Category> categoryList = objectMapper.readValue(response.body().string(), new TypeReference<List<Category>>(){});

            return categoryList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
    return null;
    }
}
