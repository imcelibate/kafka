package producer;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class AvroProducer {
	private static String TOPIC = "AMMA";
    private final static String BOOTSTRAP_SERVERS =
            "localhost:9090,localhost:9091,localhost:9092";
    private static Scanner in = new Scanner(System.in);
    
    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                                            BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                                        IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        		KafkaAvroSerializer.class.getName());       
        return new KafkaProducer(props);
	}
	
	

}
