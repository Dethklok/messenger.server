# Running

- Run ./docker/docker-compose.yml
- Create database for keycloak: CREATE DATABASE keycloak
- Restart keycloak docker container

# Setup keycloak

- http://localhost:8081/auth/
- Move to administration console (user: admin, password: admin)
- Create new realm: "pegasus"
- Create new client: "pegasus-messenger-server"
