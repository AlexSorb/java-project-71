build:
	./gradlew build

install:
	./gradlew installDist

run-dist: build install
	./build/install/app/bin/app

run-help: build install
	./build/install/app/bin/app -h

run-all-argunm: build install
	./build/install/app/bin/app -f=stylish src/test/java/resources/File1.json src/test/java/resources/File2.json

test:
	./gradlew test

clean:
	./gradlew clean

report:
	./gradlew jacocoTestReport

.PHONY: build
