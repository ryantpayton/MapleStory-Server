> All credits go to Ronan C. P. Lana, Nexon, Wizet, and the contributors of HeavenMS and subsequent parties.
> I do not take credit for any files located herein unless specifically specified otherwise.
> Forked from [HeavenMS](https://github.com/ronancpl/HeavenMS) in order to have a history of changes made herein.

### Required Files
 * Use Git or checkout with SVN using [this](https://github.com/ryantpayton/MapleStory.git) web URL
   * I will refer to this location, for the source, later as **{source}**
 * Download and install the following from [here](https://drive.google.com/drive/folders/0BzDsHSr-0V4MYVJ0TWIxd05hYUk):
   * current_wz
   * jdk-7u79-windows-x64.exe
   * ManagerMsv83.exe
     * I will refer to this location, for the client, later as **{client}**
   * mysql-query-browser-1.1.20-win.msi
   * netbeans-8.0.2-javase-windows.exe
   * HaSuite-211.7z
 * Download the latest [localhost](https://hostr.co/tsYsQzzV6xT0)
 * Download and install [mariadb-10.0.19-win32.msi](https://downloads.mariadb.org/mariadb/10.0.19/)
 * Download [STREDIT](http://www.craftnet.nl/Downloads/)

### Creating a New Database
 1. Open **HeidiSQL**
 2. Connect to your MySQL server with the parameters used while setting up MariaDB
 3. Right-click your open connection in the top-left panel
 4. Expand **Create new**
 5. Click **Database**
 6. Type **maplestory** in the **name** field
 7. Click **OK**
 8. Close **HeidiSQL**

### Preparing the Database
 1. Open **MySQL Query Browser**
 2. Connect to your MySQL server with the parameters used while setting up MariaDB
 3. Click **File**
 4. Click **Open Script ...**
 5. Navigate to **{source}\sql**
 6. Open and execute **db_database.sql**
 7. Repeat steps 3 - 6 for **db_drops.sql**
 8. Close **MySQL Query Browser**

### Creating an Account
 1. Open **HeidiSQL**
 2. Connect to your MySQL server with the parameters used while setting up MariaDB
 3. Expand the **maplestory** database
 4. Click on the **accounts** table
 5. Click on the **Data** tab
 6. Press the **Insert** key
 7. Type a username into the **name** column
 8. Type a password into the **password** column
 9. Close **HeidiSQL**

### Configure Your Server
 1. Navigate into the **{source}** folder
 2. Open **configuration.ini**
 3. Change **HOST** to the IP Address you want your server to listen on
 4. Change **URL**, **DB_USER**, **DB_PASS** to the parameters used while setting up MariaDB

### Build the Server
 1. Open **NetBeans IDE 8.0**
 2. Click **File**
 3. Click **Open Project...**
 4. Navigate to **{source}**
 5. Click on the **{source}** folder
 5. Click **Open Project**
```TODO: Need to get UI text from here```
 6. Click **Manage Platforms...**
 7. Click **Add platform...**
 8. Click **Next >**
 8. Navigate to **C:\Program Files\Java**
 9. Click on **jdk1.7.0_60**
 10. Click **Next >**
 11. Click **Finish**
 12. Right-click on the newly opened project
 13. Click **Clean and Build**
 14. Close **NetBeans IDE 8.0**

### Preparing the Client
 1. Navigate into the **{client}** folder
 2. Delete the **HShield** folder
 3. Delete **ASPLnchr.exe**, **MapleStory.exe**, and **Patcher.exe**
 4. Copy all files from **current_wz** into **{client}**
```TODO: Need to get UI text from here```
 5. Click **Yes** when prompted to replace files
 6. Copy **HeavenMS-localhost-WINDOW.exe** into the **{client}** folder
 7. Rename **HeavenMS-localhost-WINDOW.exe** to **MapleStory.exe**
 7. Open **STREDIT.exe**
 8. Click **File**
 9. Expand **Open..**
 10. Click **Open new file...**
 11. Navigate into the **{client}** folder
 12. Click **MapleStory.exe**
 13. Click **Open**
 14. Click **No**
 15. Click **No** again
 16. Change all three IPs listed in the **Configured IPs** section to match **HOST** in the **configuration.ini** file
 17. Click **File**
 18. Click **Save**

### Testing the Client
 1. Navigate into the **{source}** folder
 2. Run **launch.bat**
 3. Navigate into the **{client}** folder
 4. Run **MapleStory.exe**

### Changing a character to a GM character
 1. Open **HeidiSQL**
 2. Connect to your MySQL server with the parameters used while setting up MariaDB
 3. Expand the **maplestory** database
 4. Click on the **characters** table
 5. Click on the **Data** tab
 6. Find the row with the character who will be a GM
 7. Change the **gm** column to one of the following values:
    * 0: Common
    * 1: Donator
    * 2: JrGM
    * 3: GM
    * 4: SuperGM
    * 5: Developer
    * 6: Admin
 8. Close **HeidiSQL**

### Port-forwarding the server
 1. Port-forward the following ports:
    * MapleStory LoginServer 8484
    * MapleStory Scania Ch 1 - 3 7575 - 7577

### WZ Editing
I am going to go over how to WZ Edit with using Heena as an example. I want to change the dialogue when you talk to her. The dialogue is either in the scripts as a .js file or in the .wz files. In this example it is the .wz file. Procedure may differ based on different scenarios but hopefully this will get you an idea on how navigate the .wz file and the basics of editing.
 1. Extract **HaSuite-211.7z**
 2. Navigate into the extracted folder
 3. Open **HaRepacker.exe**
 4. Change **BMS\GMS\MSEA** to **GMS (old)** in the drop-down menu
 5. Click **File**
 6. Click **Open...**
 7. Navigate into the **{client}** folder
 8. Click **String.wz**
 9. Click **Open**
 10. Expand **String.wz**
 11. Double-click on **Npc.img**
 12. Find **2101**
 13. Expand **2101**
 14. Click **d0**
 15. Change **traveller** to **traveler**
 16. Click **Apply Changed Value**
 * ```Make sure maplestory is closed before proceeding, otherwise you will receive an unhandled excepetion error and you will have to redo your changes```
 17. Click **File**
 18. Click **Save...**
 19. Click **Save** again
 20. Click **String.wz**
 21. Click **Save** for the last time
 22. Click **Yes**
 23. Close **HaRepacker.exe**

### MobBookUpdate
```TODO: Coming soon```