package kz.ksi.factor.test.soupui.test;

import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import kz.factor.resources.test.TestRequest;
import kz.factor.resources.test.TestResponse;
import kz.factor.resources.test.TextType;
import kz.ksi.factor.test.soupui.CountryRepository;
import kz.ksi.factor.test.soupui.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Erlan.Ibraev on 09.07.2016.
 */
@Endpoint
public class TestEndPoint {

    @Autowired
    private CountryRepository countryRepository;

    @PayloadRoot(namespace = TestConfig.NAMESPACE_URI, localPart = "TestRequest")
    @ResponsePayload
    public TestResponse test(@RequestPayload TestRequest request) {
        TestResponse result = new TestResponse();
        TextType text = new TextType();
        text.setValue("Привет !");
        text.setLang("ru");
        result.setTest(text);
        return result;
    }

    @PayloadRoot(namespace = TestConfig.NAMESPACE_URI_1, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = TestConfig.NAMESPACE_URI, localPart = "test")
    @ResponsePayload
    public TextType superTest(@RequestPayload TextType request) {
        return request;
    }


}
