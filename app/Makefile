build:
	./gradlew build

install:
	./gradlew installDist

test:
	./gradlew test

clean:
	./gradlew clean

report:
	./gradlew jacocoTestReport

run-dist: build install
	./build/install/app/bin/app

run-help: build install
	./build/install/app/bin/app -h

run-json: build install
	./build/install/app/bin/app -f=stylish ./src/main/java/resources/json/FirstJsonFile.json ./src/main/java/resources/json/SecondJsonFile.json

.PHONY: build
