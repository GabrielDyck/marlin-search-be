cd marlin-be
cp pet-planet-domain/src/main/resources/com.pet.planet.domain/prod.properties pet-planet-domain/src/main/resources/com.pet.planet.domain/config.properties
mvn clean package
nohup java -jar -Duser.timezone=America/Argentina/Buenos_Aires -Dtty.host=10.142.0.2 pet-planet-controllers/target/pet-planet.controllers-1.0-SNAPSHOT.jar &