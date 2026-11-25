# Cloud deployment

The application listens on `PORT` (default `8080`) and uses `/journal` as its servlet context path. Configure these environment variables in the hosting provider:

| Variable | Required | Purpose |
| --- | --- | --- |
| `MONGODB_URI` | Yes | MongoDB connection URI, including credentials and database access |
| `JWT_SECRET` | Yes | Random secret with at least 32 UTF-8 bytes |
| `REDIS_URL` | No | Redis URL; defaults to `redis://localhost:6379` |
| `WEATHER_API_KEY` | No | Enables the weather portion of `GET /journal/user?city=...` |
| `MAIL_USERNAME`, `MAIL_PASSWORD` | No | SMTP credentials for scheduled email |
| `MAIL_HOST`, `MAIL_PORT` | No | SMTP server settings; defaults to Gmail on port 587 |
| `MONGODB_DATABASE` | No | Database name; defaults to `journaldb` |
| `JWT_EXPIRATION_MS` | No | Token lifetime; defaults to `3600000` |

Build with `mvn package` and start the generated jar with `java -jar target/journalApp-0.0.1-SNAPSHOT.jar`. Public health checks use `GET /journal/public/health-check`. Authenticated routes require `Authorization: Bearer <token>`; obtain a token from `POST /journal/public/login`.

MongoDB must be reachable from the host, and its network access list must allow the host's outbound address. The application creates the unique username index at startup, so the MongoDB credentials need permission to create indexes. MongoDB transactions used for journal writes require a replica set deployment (MongoDB Atlas provides one).
