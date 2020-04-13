package producer;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class StringProducer {
	
	private final static String TOPIC = "my-example-topic";
    private final static String BOOTSTRAP_SERVERS =
            "localhost:9090,localhost:9091,localhost:9092";
    private static Scanner in = new Scanner(System.in);
	
	private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                                            BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                                        LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                                    StringSerializer.class.getName());
        return new KafkaProducer(props);
	}
	
	public static void sendMessage(String topic) {
		try {
			Producer<Long, String> producer = createProducer();
			String line = in.nextLine();		
			while(!line.equals("exit")) {
			long time = System.currentTimeMillis();
			ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(topic,line);
			
			RecordMetadata metadata = producer.send(record).get();

			long elapsedTime = System.currentTimeMillis() - time;
			line = in.nextLine();
				/*
				 * System.out.printf("sent record(key=%s value=%s) " +
				 * "meta(partition=%d, offset=%d) time=%d\n", record.key(), record.value(),
				 * metadata.partition(), metadata.offset(), elapsedTime);
				 */
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 public static void main(String[] args)throws Exception {
		 String topicName = "AMMA";
		 if (args.length != 1) {
		 System.err.println("As TOPIC is not specified , sending message to default TOPIC \"AMMA\"");
		 System.err.println("To send to a particular TOPIC metion it as a Program Argument");		 		 
		 }else {
			 topicName = args[0];
		 }
		 
		 System.out.println("Enter message(type exit to quit)");	
		 sendMessage(topicName);

  }
	 
	 
}