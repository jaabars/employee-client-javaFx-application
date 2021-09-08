package apiRequester;

import apiRequester.impl.HttpClientImpl;
import models.Employee;
import models.Position;

import java.util.List;

public interface HttpClient {
    HttpClient INSTANCE = new HttpClientImpl();
    List<Position> getAllPositions();
}
