#cd /home/iasoa_dell/AMMA/tutorial/kafka/target

##--Without buildtime argument
#docker build -t kafka-pub-sub:latest -f /home/iasoa_dell/AMMA/tutorial/kafka/src/main/resource/Dockerfile .
##	--running without runtime arg
#	docker run kafka-pub-sub
##	--running with runtime arg
#	docker run --rm -e fullClassName=producer.StringProducer kafka-pub-sub
#
##--With buildtime argument
#docker build -t kafka-pub-sub:latest --build-arg fullClassName=producer.StringProducer -f /home/iasoa_dell/AMMA/tutorial/kafka/src/main/resource/Dockerfile .
##	--running without runtime arg
#	docker run kafka-pub-sub
##	--running with runtime arg
#	docker run --rm -e fullClassName=producer.testDocker kafka-pub-sub
FROM openjdk:8
COPY kafka-pub-sub-jar-with-dependencies.jar /tmp
WORKDIR /tmp
#ENTRYPOINT ["java","producer.testDocker"]
#CMD java -cp kafka-0.0.1-SNAPSHOT.jar producer.testDocker
#ENV fullClassName=producer.testDocker
ARG fullClassName=producer.testDocker
ENV fullClassName=$fullClassName
ENTRYPOINT java -cp kafka-pub-sub-jar-with-dependencies.jar $fullClassName



#
#