import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by Александр on 08.12.2016.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
        TransportClient transportClient = new TransportClient(settings);
        Client client = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost", 9200));

        People people = new People("Oleg", "Saratov", 19);
        IndexResponse response = client.prepareIndex("humanity", "people", "1").setSource(jsonBuilder().startObject().
                                                                                    field("name", people.getName()).
                                                                                    field("city", people.getCity()).
                                                                                    field("age", people.getAge()).endObject()).get();

        GetResponse getResponse = client.prepareGet("humanity", "people", "1").get();

        System.out.println(response.getId());
        System.out.println(response.getIndex());
        System.out.println(response.isCreated());

        String str = getResponse.getSourceAsString();
        System.out.println(str);

    }
}
