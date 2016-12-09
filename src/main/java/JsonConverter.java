import com.google.gson.Gson;

/**
 * Created by Александр on 08.12.2016.
 */
public class JsonConverter {
    private Gson GSON = new Gson();

    public String toJson(People people){
        return GSON.toJson(people,People.class);
    }
}
