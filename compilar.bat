git clone https://github.com/pjvega/Tarea01.git && 
cd ./Trabajo01_Distribuida && cd ./app-authors && gradlew quarkusBuild && 
docker build -t  jaimesalvador/app-authors:1.0.0 . && 
cd .. && 
cd ./app-books && gradlew installDist && 
gradlew copyLibs && 
docker build -t  jaimesalvador/app-books:1.0.0 . && 
cd .. && 
cd ./app-web && gradlew installDist && gradlew copyLibs && 
docker build -t  jaimesalvador/app-web:1.0.0 . && docker push  jaimesalvador/app-web:1.0.0 && 
docker push  jaimesalvador/app-books:1.0.0 && docker push  jaimesalvador/app-authors:1.0.0 && cd .. && docker-compose up
