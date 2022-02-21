# Cars 'R' Us

Here is a short highlight of this week's commits.

### Important Changes
- Reworked the way Put requests works to be more intuitive
- A lot of empty tests have been added, because I didn't feel like actually writing them
- Added Swagger documentation when running in staging profile

### Other Changes
- Small changes to profiles and failing tests

### CI / CD
- Azure runs the stagin profile for now, so it shows the index page and swagger documentation


### Earlier Changes

<details>
  <summary>
    Week 2 [a9d07cd6]
    <a href="https://github.com/simongredal/cars-r-us/tree/a9d07cd6">(browse repository at this point in history)</a>
    or <a href="https://github.com/simongredal/cars-r-us/compare/a9d07cd6...main">(compare with current)</a>
  </summary>
  <article>

### Important Changes
- Car entity has received new attribute `bestDiscountPercentage`
- Created MVC layers for Car (API controller and service classes plus response and request DTOs)
- Created MVC layers for Member (API controller and service classes plus response and request DTOs)
- API controllers have been annotated with comments, indicating how access control is supposed to work later on
- Tests have been created for the Car service. One with Mockito mocking the repository layer and one with the repository
  layer using H2.
- Tests have been created for the Car controller, with H2 as the database for the repository layer.

### Other Changes
- Reformatted code and imports
- Replaced the generic `Client400xException` with more specific `CarNotFoundException` and `MemberNotFoundException`
- Changed the way `ValidationHandler` renders Exceptions
- Maven profiles have been created. They are set up to control the Spring Boot profile which makes
  switching between different `application.properties` files super easy

### Continuous Deployment
- Created a Dockerfile to build and run the application.
- Created a GitHub Action. This Action builds a Docker image from the Dockerfile, then the image is uploaded to my
  Docker Hub. My Azure App Service then downloads this image and deploys it. The reason for doing it this way instead
  of directly building a .jar and deploying it to an Azure App Service is because I'm stubborn and want to use Java 17,
  but this runtime is not yet supported directly by Azure App Services.

  </article>
</details>

<details>
  <summary>
    Week 1 [970a88]
    <a href="https://github.com/simongredal/cars-r-us/tree/970a88">(browse repository at this point in history)</a>
    or <a href="https://github.com/simongredal/cars-r-us/compare/970a88...main">(compare with current)</a>
  </summary>
  <article>

![image1.png](image1.png)

### Implemented Entity classes
with all attributes from domain model, and foreign keys
associations using @OneToMany and @ManyToOne

- [x] Member extends BaseUser
- [x] Car
- [x] Reservation
- [x] Rental

### Implemented Repository classes

most of these have only a few extra methods

- [x] MemberRepository
- [x] CarRepository
- [x] ReservationRepository
- [x] RentalRepository

### Implemented Repository Tests

below each class is listed the methods that have tests written

- [ ] MemberRepositoryTest
  - [x] count()
- [ ] CarRepositoryTest
  - [x] count()
- [ ] ReservationRepositoryTest
- [ ] RentalRepositoryTest

### CI / CD

Der er lavet en fil, .github/workflows/build-and-test.yaml, som aktiverer en github action Denne github action består
essentielt bare af at køre `./mvnw test`

  </article>
</details>

