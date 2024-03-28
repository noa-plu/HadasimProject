HMO
Partial server-side HMO management system and DB (java, H2) This system provides a partial solution for the management of the health fund - retrieving and inserting records into the fund's database. For the purpose of presenting and checking the correctness of the functions, I used Postman - a software that allows API tests.

Server-side
Server-side code is written in the java language using java-spring Boot, an open source tool that facilitates a framework for creating microservices and web applications.

DB
We created the DB and connected through the server in the application.properties file named hadasim2 H2 (which converts to SQL):

Client-side
The help in postman for client side image.

Actions
Add a new customer to the health insurance services.
![‏‏צילום מסך (172)](https://github.com/noa-plu/HadasimProject/assets/148070382/ac27fc41-048e-406c-8b55-26ed3a0af9d5)
![‏‏צילום מסך (173)](https://github.com/noa-plu/HadasimProject/assets/148070382/bf60208f-65e3-4308-a26a-5da1abd34e25)

Accept an existing customer according to conditions (for example: date of birth, ID card, etc...)
![‏‏צילום מסך (175)](https://github.com/noa-plu/HadasimProject/assets/148070382/aeebd6a9-77c5-4955-a211-bac89cb0d51f)

Obtain all existing customers
![‏‏צילום מסך (174)](https://github.com/noa-plu/HadasimProject/assets/148070382/3d70b055-dc76-48e6-ae20-348452c5c403)

Add corona details to the customer
![‏‏צילום מסך (176)](https://github.com/noa-plu/HadasimProject/assets/148070382/cab65372-c49b-48ef-8ee0-a1a172e2440d)

Add a vaccine to the client
![‏‏צילום מסך (177)](https://github.com/noa-plu/HadasimProject/assets/148070382/da219006-07a2-42ef-97e9-c949cf43e9ee)


Technologies & dependencies:
Maven

jdk20

SpringBootApplication
