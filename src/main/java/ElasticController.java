import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * Created by Александр on 08.12.2016.
 */
public class ElasticController {
    private Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
    private TransportClient transportClient = new TransportClient(settings);
    private Client client = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost", 9200));

    public void pushInfo(){

    }

}
