import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.node.Node;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


public class Application {
    public static Map<String, Object> putJsonDocument(String name, String city, Integer age){
        Map<String, Object> jsonDocument = new HashMap<String, Object>();

        jsonDocument.put("name", name);
        jsonDocument.put("city", city);
        jsonDocument.put("age", age);

        return jsonDocument;
    }

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();
        TransportClient transportClient = new PreBuiltTransportClient(settings);

        Client client = transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));


        People people = new People("Oleg", "Saratov", 19);

        IndexResponse response = client.prepareIndex("humanity", "people", "1").setSource(jsonBuilder().startObject()
                .field("name", people.getName())
                .field("city", people.getCity())
                .field("age", people.getAge())
                .endObject())
                .get();


        GetResponse getResponse = client.prepareGet("humanity", "people", "1").setOperationThreaded(false).get();

        System.out.println(getResponse.getId());
        System.out.println(getResponse.getIndex());

        String str = getResponse.getSourceAsString();
        System.out.println(str);

        client.close();

    }
}
