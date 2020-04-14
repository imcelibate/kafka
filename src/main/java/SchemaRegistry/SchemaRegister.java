package SchemaRegistry;




import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.avro.Schema;
import org.apache.commons.compress.utils.IOUtils;
import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SchemaRegister {

	// schema registry url
	private static String url = "http://localhost:9090";

	// associated topic name.
	private static String topic = "AMMA";

	// associated topic name.
	private static String schemaPath = "";
	
    private final static MediaType SCHEMA_CONTENT =
            MediaType.parse("application/vnd.schemaregistry.v1+json");
    
    private final static String ASHRAM_SCHEMA_v1 = "{\n" +
            "  \"schema\": \"" +
            "  {" +
            "    \\\"namespace\\\": \\\"com.ammaguru.ashram\\\"," +
            "    \\\"type\\\": \\\"record\\\"," +
            "    \\\"name\\\": \\\"AMMA_ASHRAM\\\"," +
            "    \\\"fields\\\": [" +
            "        {\\\"name\\\": \\\"AshramName\\\", \\\"type\\\": \\\"string\\\"}," +
            "        {\\\"name\\\": \\\"location\\\", \\\"type\\\": \\\"string\\\"}," +
            "        {\\\"name\\\": \\\"AshramID\\\",  \\\"type\\\": \\\"int\\\"}," +
            "        {\\\"name\\\": \\\"phoneNumber\\\",  \\\"type\\\": \\\"string\\\"}" +
            "    ]" +
            "  }\"" +
            "}";
    
    
    private final static String ASHRAM_SCHEMA_v2 = "{\n" +
            "  \"schema\": \"" +
            "  {" +
            "    \\\"namespace\\\": \\\"com.ammaguru.ashram\\\"," +
            "    \\\"type\\\": \\\"record\\\"," +
            "    \\\"name\\\": \\\"AMMA_ASHRAM\\\"," +
            "    \\\"fields\\\": [" +
            "        {\\\"name\\\": \\\"AshramName\\\", \\\"type\\\": \\\"string\\\"}," +
            "        {\\\"name\\\": \\\"Country\\\", \\\"type\\\": \\\"string\\\"}," +
            "        {\\\"name\\\": \\\"AshramID\\\",  \\\"type\\\": \\\"int\\\"}," +
            "        {\\\"name\\\": \\\"phoneNumber\\\",  \\\"type\\\": \\\"string\\\"}" +
            "    ]" +
            "  }\"" +
            "}";

	public static void main(String arg[]) {

		if (arg.length != 2) {
			System.out.println("As TOPIC and Schema path is not specified, going with Defaults");
			System.out.println("To register Schema for a particular TOPIC and Schema metion it as a Program Argument");
		} else {
			topic = arg[0];
			schemaPath = arg[1];
		}

		SchemaRegister ss = new SchemaRegister();
		ss.registerSchema();

	}

	private void registerSchema() {
		try {
			if (schemaPath.equals(""))
				schemaPath = getClass().getClassLoader().getResource("source.avsc").getPath();
			  
			// subject convention is "<topic-name>-value" 
			String subject = topic + "-value"; 
			
			// avsc json string.
			String schema = null;

			schema = new String ( Files.readAllBytes(Paths.get(schemaPath) ) );
			
			final OkHttpClient client = new OkHttpClient();
			
	        //POST A NEW SCHEMA
	        Request request = new Request.Builder()
	                .post(RequestBody.create(SCHEMA_CONTENT, ASHRAM_SCHEMA_v1))
	                .url("http://localhost:8081/subjects/ashram1/versions")
	                .build();

	        String output = client.newCall(request).execute().body().string();
	        System.out.println(output);

	        
//	        //POST A NEW Version of Schema
//	         request = new Request.Builder()
//	                .post(RequestBody.create(SCHEMA_CONTENT, ASHRAM_SCHEMA_v2))
//	                .url("http://localhost:8081/subjects/ashram1/versions/3")
//	                .build();
//
//	       output = client.newCall(request).execute().body().string();
//	        System.out.println(output);
	        
	        //LIST ALL SCHEMAS
	        request = new Request.Builder()
	                .url("http://localhost:8081/subjects")
	                .build();

	        output = client.newCall(request).execute().body().string();
	        System.out.println(output);
	        
	        //SHOW VERSION 1 OF Ashram
	        request = new Request.Builder()
	                .url("http://localhost:8081/subjects/ashram1/versions/1")
	                .build();

	        output = client.newCall(request).execute().body().string();
	        System.out.println(output);
			
			

//			Schema avroSchema = new Schema.Parser().parse(schema);
//
//			CachedSchemaRegistryClient client = new CachedSchemaRegistryClient(url, 20);
//
//			client.register(subject, avroSchema);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

	}

}
