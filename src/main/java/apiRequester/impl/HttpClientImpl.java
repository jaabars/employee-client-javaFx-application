package apiRequester.impl;

import apiRequester.HttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Employee;
import models.Position;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class HttpClientImpl implements HttpClient {
    private ObjectMapper objectMapper = new ObjectMapper();
    private OkHttpClient client = new OkHttpClient();
    private String baseEmployeeServiceUrl = "http://localhost:8080/api/v1/";

    @Override
    public List<Position> getAllPositions() {
        Request request = new Request.Builder()
                .url(baseEmployeeServiceUrl + "position/getAll")
                .build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();

            List<Position> positionList = objectMapper.readValue(response.body().string(), new TypeReference<List<Position>>() {
            });

            return positionList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Position savePosition(Position position) {
        try {
            String json = objectMapper.writeValueAsString(position);
            RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                    .url(baseEmployeeServiceUrl + "position/save")
                    .post(requestBody)
                    .build();

            Call call = client.newCall(request);

            try {
                Response response = call.execute();

                position = objectMapper.readValue(response.body().string(), Position.class);

                return position;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        Request request = new Request.Builder()
                .url(baseEmployeeServiceUrl+"employee/get")
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            List<Employee> employeeList = objectMapper.readValue(response.body().string(), new TypeReference<List<Employee>>() {});
            return employeeList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        try {
            RequestBody requestBody = RequestBody.create(
                    objectMapper.writeValueAsString(employee),
                    MediaType.parse("application/json; charset=utf-8")
            );
            Request request = new Request.Builder()
                    .url(baseEmployeeServiceUrl+"employee/save")
                    .post(requestBody)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            employee = objectMapper.readValue(response.body().string(),Employee.class);
            return employee;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
