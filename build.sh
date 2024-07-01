./mvnw clean
./mvnw package -DskipTests
mkdir target/extracted
java -Djarmode=layertools -jar target/PracticeModsenJava-0.0.1-SNAPSHOT.jar extract --destination target/extracted

sudo docker build -t practice_modsen:0.0.1 .
sudo docker-compose up