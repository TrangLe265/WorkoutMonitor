# Workout Monitor 💪🏻 💫
## ✨ Overview
Workout Monitor is a web application designed to track personal fitness activities . Users can upload workout data (e.g., cycling, running) from CSV files, manage their activity logs. The inspiration for this app came from Strava's CSV Exporting feature, detailed in their guide:[Exporting Your Data and Bulk Export - Strava Support](https://support.strava.com/hc/en-us/articles/216918437-Exporting-your-Data-and-Bulk-Export#:~:text=Hover%20over%20your%20name%20in,archive%E2%80%9D%20on%20the%20next%20page.). To keep the project within the scope of a coursework assignment, I focused solely on processing the activities.csv file from the exported data. As a result, the entities in the application were designed to align with the structure of the original `activities.csv` file. 
## ✨ Features
* Spring security login
* Activity Upload: Upload workout data from CSV files.
* Personalized Activity Logs: Each user can view, delete and add their own activities.
* Basic RESTful API: Supports creating and managing workout data via APIs.
* Basic responsive frontend: Built with Thymeleaf and Bootstrap
* The app does allow multiple users, each will have their own frontend view
<img width="300" alt="Screenshot 2024-11-21 at 16 42 07" src="https://github.com/user-attachments/assets/b2fb26a7-e459-4bd8-9e6e-da8279cbf0c9">
<img width="300" alt="Screenshot 2024-11-21 at 16 42 28" src="https://github.com/user-attachments/assets/8325b049-5f76-424b-be06-4555d63a4901">

<img width="605" alt="Screenshot 2024-11-21 at 16 33 00" src="https://github.com/user-attachments/assets/6238a264-9fd7-48d6-bffc-ce6aa6655239">

## ✨ Tech stack: 
* Backend: Spring Boot
* Frontend: Thymeleaf, Bootstrap
* Database: MySQL
* File Processing: Spring Boot MultipartFile, OpenCSV
* Authentication: Spring Security
* Build Tool: Maven
* Version Control: Git and GitHub
  
## ✨ Prerequisites: 
* Java 17 or higher
* Springboot and maven
* MySQL/ MySQL Workbench
* Some instructions to set up database is in the application.properties file for you to be able to run the program locally
* The `activities.csv` file requires preprocessing, including cleaning and removing unused columns, as it can be too large to handle efficiently in its original form.
* You can create your own account using CommandlineRunner

## ✨ Possible further developments: 
* The API formatting requires further refinement to ensure consistency, clarity, and ease of use.
* API documentation like Swagger can be applied
* More authentication and signup options
* Frontend views for activitities of different types can be made

Please note that this application was developed within a classroom context, where security was not the primary focus. As such, the security protocols implemented are open for further development and enhancement as part of future improvements.
