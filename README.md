# Sports betting site
The final project of Java programming course

Docker images are available at :
https://hub.docker.com/u/jakbialy

Elements used in the project:
Spring  boot project with several extra dependencies : 
Hibernate,Lombook,Security, Thymeleaf, Jackson,  Javafaker, Tests, Swagger (temporarily disabled), with dependencies managment by Maven, Front-End is made on bootstrap  modified free template (SbAdmin), relation database is managed by MySQL.

Project rules:
Project  requires to start only  database named “sport_betting”
Matches and odds are generated every 5min  and take place for 2min (from  3rd to 5th minute  from generation)
Two  sports: Football and League of Legends
In the end of each event system checks results and bets
User can place bet,load account, see simple team statistics, add teams into favorites and see several user account information
User can interact with other users (make bets together, send messages, add them into friends)
Project generates own API for possible external clients secured by api key

Admin role is available in this project, Admin can remove users, look into history of their bets, wallet charges and can create new admins.

Feel free to download and try!

Now is in rebuilding - is going to be well tested by unit tests and re-written to use more abstraction, clean up code and put into Heroku server
What to do in rebuilding:
- Add unit tests, at least to all services
- Add integration tests, using for example liquid database
- Add extra abstraction layer to services
- Use dto objects for API instead of regular models
- Solve minor bugs
- Build extra branch which uses postgreSQL (for deploying to Heroku)

There's also a lot of minor issues, probably this project need re-writing to achieve good standards
