build:
	./gradlew build

install:
	./gradlew installDist

run-dist: build install
	./build/install/app/bin/app

run-help: build install
	./build/install/app/bin/app -h

run-all-argunm: build install
	./build/install/app/bin/app -f=stylish ./app/File1.json /home/alex/java-project-71/app/File2.json

test:
	./gradlew test

clean:
	./gradlew clean


.PHONY: build
