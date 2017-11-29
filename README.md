### LAMk App
#### The aim of this app is adding serach feature for LAMK app. Student by selecting the campus, date and time, can search and reserve the available classes. Also he/she can cancel the reservation. The app uses Postgres database hosted on Heroku which the codes are available on LamkApp_back repository.
##### First, I tried to make the app's design as similiar as to the original app.
![alt text](https://user-images.githubusercontent.com/25009890/33402815-7a6bb580-d566-11e7-91d2-1ad348bdd958.jpg)
##### In the Search Fragment, I put three elements, one Spinner and two EditText for searching, including campus, date and time.
##### By clicking on the Spinner, the student can select campus.
##### By clicking on DatePicker, student can select the date.
##### By clicking on TimePicker, student can select the time. (The time is allowed only between 8am to 6pm)
##### By clicking on SearchButton, student can check the available classes information including campus name, name, floor and capacity.
##### By clicking on the item, student can reserve the room.
##### A message is shown and asks the user the confirm the reservation. After confirming, the reservation information is shown on the screen and the class removed the database.
