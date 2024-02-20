# ESE-Mailer

This is the service that will accept the trigger to send and email. It houses all the necessary services to provide end to end email scheduling and also hosts the SMTP server.

![ese-mailer overview](./images/overview.png)

---

## Components

The ESE-Mailer is composed of the following parts:

### RabbitMQ Queue

- For anyone who wishes to trigger an email, they would have to send a message to this queue.
- Stats about the queue available at `http://localhost:15672`

Message format:

```json
{
  "from": "saumi10600@gmail.com",
  "to": "saumya.bhatt106@gmail.com",
  "subject": "Suspicious user alert",
  "content": "User sending multiple stale data"
}
```

**Note:** The queue name on which the mailer will be subscribed to is `email_channel`.

![rabbitMQ Screen Shot](./images/rabbitmq_ss.png)

### Spring Boot Mailer

- This is the consumer of the messages from this queue.
- It will do the necessary parsing and trigger the SMTP server to send the email.

### MailHog SMTP Server

- This is a mock SMTP server to send and receive emails.
- The UI for the inbox is hosted at `http://localhost:8025`

![MailHog Screen Shot](./images/mailhog_ss.png)

---

## Local Set-up

- Before running the spring-boot mailer, ensure that the queue and the SMTP server are up and running.

```shell
# starting the Queue
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

# starting the SMTP server
docker run -d --name mailhog -p 1025:1025 -p 8025:8025 mailhog/mailhog

```

- Make any necessary changes required at `application.properties` file if needed.
- Build and run the `MailerApplication.java` file at `src/main/java/` to start the spring-boot-mailer.

### Using Docker Compose

- This will use the JAR that has currently been built.
- For this to work, ensure that the `queue.url` and `spring.mail.host` in _application.properties_ is set to either:
  - **host.docker.internal** for both
  - **rabbitmq** and **mailhog** respectively
- Run the compose file as `docker-compose up -d`
- If at first the `mailer-1` container fails, restart that container again.