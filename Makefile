JAR_FILE = millennium-falcon-computer/target/millennium-falcon-computer-*.jar
OUTPUT_DIR = r2d2

all: $(JAR_FILE)
	cd ..
	mkdir -p $(OUTPUT_DIR)
	mv $(JAR_FILE) $(OUTPUT_DIR)

$(JAR_FILE):
	cd millennium-falcon-computer; mvn clean package

# Clean up the output folder and generated files.
clean:
	rm -rf $(OUTPUT_DIR)/millennium-falcon-computer-*.jar
	cd millennium-falcon-computer; mvn clean

run-backend:
	cd millennium-falcon-computer; ./mvnw spring-boot:run

run-frontend:
	cd c3po; npm run serve

release: clean all

.PHONY: all clean release