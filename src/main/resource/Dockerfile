#cd /home/iasoa_dell/AMMA/tutorial/kafka/target

##--Without buildtime argument
#docker build -t testjava:latest -f /home/iasoa_dell/AMMA/tutorial/kafka/src/main/resource/Dockerfile .
##	--running without runtime arg
#	docker run testjava
##	--running with runtime arg
#	docker run --rm -e fullClassName=producer.StringProducer testjava
#
##--With buildtime argument
#docker build -t testjava:latest --build-arg fullClassName=producer.StringProducer -f /home/iasoa_dell/AMMA/tutorial/kafka/src/main/resource/Dockerfile .
##	--running without runtime arg
#	docker run testjava
##	--running with runtime arg
#	docker run --rm -e fullClassName=producer.testDocker testjava
FROM openjdk:8
COPY kafka-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
#ENTRYPOINT ["java","producer.testDocker"]
#CMD java -cp kafka-0.0.1-SNAPSHOT.jar producer.testDocker
#ENV fullClassName=producer.testDocker
ARG fullClassName=producer.testDocker
ENV fullClassName=$fullClassName
ENTRYPOINT java -cp kafka-0.0.1-SNAPSHOT.jar $fullClassName



#
#