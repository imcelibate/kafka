package consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class StringConsumer {
	
	private static String TOPIC = "AMMA";
    private final static String BOOTSTRAP_SERVERS =
            "localhost:19090,localhost:19091,localhost:19092";
    
    private static Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                                    BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                                    "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                IntegerDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());

        // Create the consumer using props.
        final Consumer<Long, String> consumer =
                                    new KafkaConsumer(props);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }
    
    static void startConsumer() throws InterruptedException {
        final Consumer<Long, String> consumer = createConsumer();
        boolean exit = false;

        while (!exit) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(100);
/*
            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }
*/
            
            for(ConsumerRecord<Long, String>record : consumerRecords) {
            	if(record.value().equalsIgnoreCase("Exit Poll1")) {
            		exit = true;
            	}
            	 System.out.printf("Record Key : %d, Record value : %s, Record partition : %d, Record Offset : %d)\n",
                         record.key(), record.value(),
                         record.partition(), record.offset());
            	
            }
            consumer.commitAsync();
        }
       // consumer.close();
        System.out.println("DONE");
    }
    
    public static void main(String args[]) {
    	try {
    		if (args.length != 1) {
    			 System.out.println("As TOPIC is not specified , fetching message from default TOPIC \"AMMA\"");
    			 System.out.println("To fetch from a particular TOPIC metion it as a Program Argument");		 		 
    			 }else {    				 
    				 TOPIC = args[0];
    				 System.out.println("Fetching message from :"+TOPIC);		 		 
        			 
    			 }
    		
			startConsumer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
