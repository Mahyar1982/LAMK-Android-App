### LAMk App
#### The aim of this app is adding serach feature for LAMK app. Student by selecting the campus, date and time, can search and reserve the available classes. Also he/she can cancel the reservation. The app uses Postgres database hosted on Heroku which the codes are available on LamkApp_back repository.
##### First, I tried to make the app's design as similiar as to the original app.
![alt text](https://user-images.githubusercontent.com/25009890/33402815-7a6bb580-d566-11e7-91d2-1ad348bdd958.jpg)
##### In the Search Fragment, I put three elements, one Spinner and two EditText for searching, including campus, date and time.
![alt text](https://user-images.githubusercontent.com/25009890/33402816-7a890176-d566-11e7-9c52-6cd4478f466d.jpg)
##### By clicking on the Spinner, the student can select campus.
![alt text](https://user-images.githubusercontent.com/25009890/33402817-7aa33c8a-d566-11e7-8d72-5f1aa9ad6e14.jpg)
##### By clicking on DatePicker, student can select the date.
![alt text](https://user-images.githubusercontent.com/25009890/33402818-7abe1302-d566-11e7-80e1-05eb373050d9.jpg)
##### By clicking on TimePicker, student can select the time. (The time is allowed only between 8am to 6pm)
![alt text](https://user-images.githubusercontent.com/25009890/33403973-1cdda0b8-d56b-11e7-804e-1065d46e773d.jpg)
##### By clicking on SearchButton, student can check the available classes information including campus name, name, floor and capacity.
![alt text](https://user-images.githubusercontent.com/25009890/33402820-7b00d854-d566-11e7-96f4-f414c61cf918.jpg)
##### By clicking on the item, student can reserve the room. A message is shown and asks the user the confirm the reservation.
![alt text](https://user-images.githubusercontent.com/25009890/33402821-7b1dc3a6-d566-11e7-8ace-510b948c596a.jpg)
##### After confirming, the reservation information is shown on the screen and the class removed from the database.
![alt text](https://user-images.githubusercontent.com/25009890/33402819-7ae40a62-d566-11e7-8943-bef4a43b72f0.jpg)
##### The student can cancel the reservation.
