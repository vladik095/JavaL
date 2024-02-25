# Tutorial and QR Code Management

This project provides an API for managing tutorials and generating QR codes. It utilizes Spring Boot for backend functionality.

## Project Structure

- **com.vladislav.spring.jpa.postgresql.model**: Contains model classes used in the application.
  - `Log.java`: Data model for event logging.

- **com.vladislav.spring.jpa.postgresql.controller**: Contains REST API controllers for handling requests.
  - `UserController.java`: Controller for managing tutorials and QR codes.

- **com.vladislav.spring.jpa.postgresql.service**: Contains services responsible for business logic.
  - `LogService.java`: Service for working with event logs.

- **com.vladislav.spring.jpa.postgresql.repository**: Contains repository interfaces for interacting with the database.
  - `LogRepository.java`: Repository for working with event logs.

- **com.vladislav.spring.jpa.postgresql**: Contains the main application class.
  - `SpringJpaPostgresqlApplication.java`: Main class to run the Spring Boot application.

- **resources**: Contains application configuration files and other resources.

## Database Description

This application uses a PostgreSQL database. It contains a table named `logs` with fields:

- `id`: Unique event identifier (type `long`).
- `Author`: Event author (type `String`).
- `QRdescription`: Description of the event for generating a QR code (type `String`).
- `Link`: Flag indicating whether the event is a link (type `boolean`).

## API Description

### Tutorial Management

- `GET /api/tutorials`: Retrieve all tutorials. Can be filtered by title by passing the `title` parameter.
- `GET /api/tutorials/{id}`: Retrieve a tutorial by its identifier.
- `POST /api/tutorials`: Create a new tutorial. Data about the new tutorial should be sent in the request body.
- `PUT /api/tutorials/{id}`: Update an existing tutorial. Data about the updated tutorial should be sent in the request body.
- `DELETE /api/tutorials/{id}`: Delete a tutorial by its identifier.
- `DELETE /api/tutorials`: Delete all tutorials.
- `GET /api/tutorials/published`: Retrieve all published tutorials.
- `GET /api/tutorials/searchByTitle`: Search tutorials by title. Can pass the `title` parameter for searching.

### QR Code Management

- `GET /api/generateQRCode`: Generate a QR code based on the provided text. Accepts a `text` parameter containing the text for generating the QR code.
- `GET /api/qrCodeDescription/{id}`: Generate a QR code based on the description of a tutorial. Accepts an `id` parameter identifying the tutorial, and generates a QR code based on the description.

