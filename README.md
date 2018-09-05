# springboot-messenger
A messaging app built on SpringBoot

## Current Status
The application implements all REST operations requested in the challenge: https://asappinc.github.io/challenge-backend/


## TODO:
* Implement code quality best practices such as unit tests, integration tests (with mocking) and code coverage
* Refactor application to include logging frameworks and monitoring tools
* Re-architect for scale by using caching and a standalone DBMS


## Usage
### Run the application: 
`java -jar ./target/challenge-0.1.0.jar`

### Sample calls:
Create a user: `curl -s -XPOST http://localhost:8080/users -H "Content-Type: application/json" -d '{"username":"username1","password":"password1"}'`

Login: `curl -s -XPOST http://localhost:8080/login -H "Content-Type: application/json" -d '{"username":"username7","password":"password"}'`

Send Message: `curl -s -XPOST http://localhost:8080/messages -H "Content-Type: application/json" -H "Authorization: MTtjMmY5NDg0MS1kNmIxLTQxMjgtODYyMi1hNzg3YzczNDA1MmU=" -d '{"sender": 1, "recipient": 1, "content": {"type": "text", "text": "text of message"}}'`

Get Messages: `curl -s -XGET 'http://localhost:8080/messages?recipient=1&start=3' -H "Content-Type: application/json" -H "Authorization: MTtjMmY5NDg0MS1kNmIxLTQxMjgtODYyMi1hNzg3YzczNDA1MmU="`

