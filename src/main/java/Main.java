import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String absolutPath = "C:/Users/weyma/IdeaProjects/My project (http, task2)/src/";
    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=0KVw26xh5bjtLth0Hz0rG5PQ2aRd6J2pSMGLJASE";


    public static void fileWriter(String fileName, byte[] bytes) {
        File file = new File(fileName);
        if (!file.exists()) {
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(bytes, 0, bytes.length);
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        } else {
            System.out.println("Файл уже существует. Удалите его перед созданием нового");
        }
    }

    public static void main(String[] args) throws IOException {


        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        HttpGet request = new HttpGet(URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = httpClient.execute(request);

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonRequest result = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
        String pictureURL = result.getHdurl();
        HttpGet requestPicture = new HttpGet(pictureURL);
        CloseableHttpResponse response1 = httpClient.execute(requestPicture);
        byte[] pictureBody = response1.getEntity().getContent().readAllBytes();

        String[] fileName = pictureURL.split("/");

        fileWriter(absolutPath + fileName[fileName.length - 1], pictureBody);

        response.close();
        httpClient.close();
    }
}
