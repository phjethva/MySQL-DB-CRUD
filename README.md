# MySQL-DB-CRUD
It is an example for Android to store date in MySQL Database with basic CRUD operation.

![imge](https://github.com/phjethva/MySQL-DB-CRUD/blob/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png)

# Example:
Here in this tutorial we try to do an example of To Do list. You can find the structure of MySQL Database in below table.

| Id (AI) | TaskName |  TaskDateTime  |
|:-:|:-:|:-:|
|    1    |  Task 1  | dd/MM/yyyy kk:mm:ss |
|    2    |  Task 2  | dd/MM/yyyy kk:mm:ss |
|    3    |  Task 3  | dd/MM/yyyy kk:mm:ss |
|    4    |  Task 4  | dd/MM/yyyy kk:mm:ss |
|    5    |  Task 5  | dd/MM/yyyy kk:mm:ss |

We will follow these below steps to understand CRUD operations of MySQL Database.

### Step 01:
- Base class for MySQL database in Android is "SQLiteOpenHelper". We will extends two method form this class:
  1) onCreate()
      - This method will create database if not exist.
  2) onUpgrade()
      - This method will upgrade database as necessary.
- First we will create database named **"my_task_db"**.
- Create a table under this database named **"my_task_table"**.
- We will manage three values in database:
  1) **Id (Auto Increment)**  : It is an unique id of Task created.
  2) **TaskName**             : It is a name of Task.
  3) **TaskDateTime**         : It is a date & time of Task created.
- This are two methods below, whis will handle operation:
  1) **getReadableDatabase()**
  2) **getWritableDatabase()**

Now We will manage datbase query for different CRUD operation for this created table.

### Step 02 : CREATE
- Create new task.
- We will create a method named **"createTask()"**.
- Use "ContentValues" class to insert multiple entries in database.
- method **"db.insert(...)"** will insert entry in to created database.

### Step 03 : READ
- Read all task.
- We will create three methods for different read related operation.
  1) **"getTotalTaskCount()"** : It will give count of total entries available in database.
  2) **"readAllTask()"**       : It will read all entries available by values in database.
  3) **"readTaskByID()"**      : It will read entry by given id if available in database.

### Step 04 : UPDATE
- Update task.
- We will create a method named **"updateTask()"**.
- method **"db.update(...)"** will updte enter of given id by necessary values in to database.

### Step 05 : DELETE
- Delete task.
- We will create a method named **"deleteTask()"**.
- method **"db.delete(...)"** will delete entry of given id in to database.
- If you want to clear all values available in table than use
      **"db.delete(TABLE_NAME, null, null)"**
  It will delete all entries available in that table in to database.

Read more about MySQL database operations in Android here: [SQLiteOpenHelper](https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper)

# Thanks for you Time & Consideration.
If you feel that this tutorial really helps you than give me a like by clicking above 🟊 STAR button.

You can buy me a coffee for my work by clicking this link: [Buy Me a Coffee on PayPal](https://www.paypal.me/phjethva)
