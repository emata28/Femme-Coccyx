package image;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

public class ImageAnalyzer {

    private static final String subscriptionKey = "f9a284b3c9ca47b89b634b5e378a3f8a";
    private static final String uriBase = "https://eastus.api.cognitive.microsoft.com/vision/v2.0/analyze";
    private ArrayList<Tag> Tags = new ArrayList<>();

    private static ImageAnalyzer ourInstance = new ImageAnalyzer();
    public static ImageAnalyzer getInstance() {
        return ourInstance;
    }

    private ImageAnalyzer() {
    }

    public void AnalyzeImage(String imageToAnalyze){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("visualFeatures", "Tags");
            builder.setParameter("language", "en");

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            StringEntity requestEntity =
                    new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject  json= new JSONObject(jsonString);
                getTagsFromJson((JSONArray) json.get("tags"));
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
    }

    private void getTagsFromJson(JSONArray arr){
        int i = 0;
        while (i < arr.length()) {
            JSONObject jobj = arr.getJSONObject(i);
            Tag tag = new Tag((Double) jobj.get("confidence"), jobj.get("name").toString());
            Tags.add(tag);
            i++;
        }
    }

    public ArrayList<Tag> getTags() {
        return Tags;
    }
}
