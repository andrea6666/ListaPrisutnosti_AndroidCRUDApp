## ListaPrisutnosti

### This Android Application is created with the aim of having meeting and lecture participants directly input their information into a Google Sheet, where records are kept of details such as name, surname, city, job position, phone number, and email. The application utilizes Google Sheets as its database.


#### All required configuration is in [buld.gradle.kts](ListaPrisutnosti/app/build.gradle.kts).
#### All required code for Google Sheets is in [Extensions Apps Script](https://github.com/andrea6666/ListaPrisutnosti_AndroidCRUDApp/tree/main/Google%20Sheet%20Apps%20Script) folder, it is a Javascript code.
#### Also You will need to create Firebase and to connect [Firebase](https://firebase.google.com) to android application. Users that are using login will have to be added manually into Firebase. 

#### In order to connect applicaton with your own Google Sheet You need to create the sheet and to change sheet url in the Google Apps Script and in [MainActivity2](https://github.com/andrea6666/ListaPrisutnosti_AndroidCRUDApp/blob/main/ListaPrisutnosti/app/src/main/java/com/example/listaprisutnosti/MainActivity2.java). In the Google sheet You need to create project in Extesions in Apps Script and to add [code](https://github.com/andrea6666/ListaPrisutnosti_AndroidCRUDApp/tree/main/Google%20Sheet%20Apps%20Script) that you can find in this project. You also need to deploy script in Google Sheet and to use deployed version in application.
## 
### Adding the user
#### This shows how to add user and how it is added automatically to the Google Sheet. Added records are name, surname, city, job position, phone number, and email. Several validations are performed before a user is added to the sheet: all fields must be filled, the phone number must be at least 9 digits long, and the email must be valid. Additionally, all letters are converted to Cyrillic, and the initial letters of the first and last names are capitalized. If the user wishes to modify their input, there is a button that leads to the initial page with their saved input in the layout. This way, the user doesn't have to input all the data from scratch; they can simply edit the required information.

https://github.com/andrea6666/ListaPrisutnosti_AndroidCRUDApp/assets/45822712/fd993173-22cc-4d36-849e-3dd2fdf3541d

## 
### Login and change password
#### This section illustrates how to log in and change passwords. A hidden button - logo image, located in the top right corner of the main layout, serves as an access point for administrators to log into a list containing all the data from the Google Sheet. This list is searchable and displays comprehensive information for an individual from the Google Sheet records.
#### An administrator is a user manually added to Firebase by the owner. Once an admin receives an email, they can change their password, which is then encrypted for security. Email and password hints are removed for enhanced privacy.

https://github.com/andrea6666/ListaPrisutnosti_AndroidCRUDApp/assets/45822712/2b517f57-f6c0-4042-b070-a157e73ec782

