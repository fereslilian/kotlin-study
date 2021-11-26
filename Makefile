build-image:
	docker build -t lilianferesl/ktor-example:latest .

push-image:
	docker push lilianferesl/ktor-example:latest

build-and-test:
	./gradlew build

print-random:
	java -jar producer.jar -m=console

infinite-random:
	java -jar producer.jar -m=http -p=8080

test:
	java -jar producer.jar -m=test -p=8080
