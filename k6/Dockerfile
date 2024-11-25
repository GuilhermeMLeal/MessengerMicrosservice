FROM grafana/k6:latest

# Copy the test script into the container
COPY ./request_test_list_products.js /scripts/request_test_list_products.js

# Set the working directory
WORKDIR /scripts

# Default command to run the test
CMD ["k6", "run", "/scripts/request_test_list_products.js"]
