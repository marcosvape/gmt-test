This is an API to import video metadata. It only supports youtube for now. The database is being mocked into memory.

It uses:
- spring boot
- spring security
- feign client
- lombok

It was built under the MVC architecture.

It has a GHA pipeline that will build the project ( jar file ) and then build a docker image with the jar file.

in order to run the application locally, you need to have:

- java 21
- docker
- maven

you can simply run:

- mvn clean install
- docker build -t video-metadata-importer .
- run the docker image with:
  - docker run -p 8080:8080 video-metadata-importer

The users are mocked on the file users.json under resources. You can change the users there, but the default users are:
admin@email.com/admin123 - ADMIN role
user@email.com/user123 - USER role

This API has a documentation under swagger, which you can access after running the application at:
http://localhost:8080/swagger-ui/index.html

the default port configured is 8080, you can change it in the application.properties file and Dockerfile.

This is using the real Youtube API. You can replace you own API key at application.properties

There is a postman collection on the file postman_collection.json you can simply import it to your postman and run the tests.

It also has basic unit tests implemented.

By lack of time i couldn't implement async import, other video providers, the caching layer and paging and sorting, but for sake of acknowledgement, i would implement it with:

- for caching i would use caffeine
- for async import i would use spring async annotations
- for paging and sorting i would use spring data jpa when using real DB
- for other video providers i would use feign client to call their APIs and implement the logic to parse the metadata under the contract service ExternalVideoMetadataService.

Any questions please reach out to me at:
marcosaguiar@live.com