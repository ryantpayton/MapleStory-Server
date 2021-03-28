> All credits go to Ronan C. P. Lana, Nexon, Wizet, and the contributors of HeavenMS and subsequent parties.
> I do not take credit for any files located herein unless specifically specified otherwise.
> Forked from [HeavenMS](https://github.com/ronancpl/HeavenMS) in order to have a history of changes made herein.

### Required Files
 * Use Git or checkout with SVN using [this](https://github.com/ryantpayton/MapleStory.git) web URL
   * I will refer to this location, for the source, later as **{source}**
 * Download and install the following from [here](https://drive.google.com/drive/folders/0BzDsHSr-0V4MYVJ0TWIxd05hYUk):
   * current_wz
   * HaSuite-211.7z
   * jdk-7u79-windows-x64.exe
   * ManagerMsv83.exe
     * I will refer to this location, for the client, later as **{client}**
   * netbeans-8.0.2-javase-windows.exe
 * Download the latest [localhost](https://hostr.co/tsYsQzzV6xT0)
 * Download the latest [mariadb-*-win32.msi](https://downloads.mariadb.org/mariadb/)
 * Download [STREDIT](http://www.craftnet.nl/Downloads/)
   * I will refer to this location, for the extracted STREDIT, later as **{edit}**

### Creating a New Database
 1. Open **HeidiSQL**
 2. Click **New** in the bottom-left panel
 3. Type a name for the new session
 4. Fill in the **Hostname / IP**, **User** and **Password** with the parameters used while setting up MariaDB
 5. Click **Open**
 6. Right-click your open connection in the top-left panel
 7. Expand **Create new**
 8. Click **Database**
 9. Type **maplestory** in the **Name** field
 10. Click **OK**

### Preparing the Database
 11. Click **maplestory** in the top-left panel
 12. Click **File**
 13. Click **Run SQL file...**
 14. Navigate to **{source}\sql**
 15. Open **db_database.sql**
 16. Repeat steps 12 - 14 for **db_drops.sql**

### Creating an Account
 19. Right-click the **maplestory** database and click **Refresh**
 20. Expand the **maplestory** database
 21. Click on the **accounts** table
 22. Click on the **Data** tab
 23. Press the **Insert** key
 24. Type a username into the **name** column
 25. Type a password into the **password** column
 26. Close **HeidiSQL**

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
 6. Click **Open Project**
 7. If prompted to resolve **Project Problems** continue with **Resolve Project Problems** otherwise continue with **No Project Problems**

##### Resolve Project Problems
 1. Click **Resolve Problems...**
 2. Click **Resolve...**
 3. Complete steps 1 - 7 from **Combined Procedure**
 4. Click **Close** again
 5. Continue with **Continued Steps**

##### No Project Problems
 1. Right-click on the newly opened project
 2. Click **Properties**
 3. Click **Libraries**
 4. Click **Manage Platforms...**
 5. Click **Add Platform...**
 6. Complete steps 1 - 7 from **Combined Procedure**
 7. Click **OK**
 8. Continue with **Continued Steps**

##### Combined Procedure
 1. Check **Java Standard Edition**, if not already selected
 2. Click **Next >**
 3. Navigate to **C:\Program Files\Java**
 4. Click on **jdk1.7.0_XX**
 5. Click **Next >**
 6. Click **Finish**
 7. Click **Close**

##### Continued Steps
 1. Right-click on the newly opened project
 2. Click **Clean and Build**
 3. Close **NetBeans IDE 8.0**

### Preparing the Client
 1. Navigate into the **{client}** folder
 2. Delete the **HShield** folder
 3. Delete **ASPLnchr.exe**, **MapleStory.exe**, and **Patcher.exe**
 4. Copy all files from **current_wz** into **{client}**
 5. Click **Replace the files in the destionation**
 6. Copy **HeavenMS-localhost-WINDOW.exe** into the **{client}** folder
 7. Rename **HeavenMS-localhost-WINDOW.exe** to **MapleStory.exe**
 8. Navigate into the **{edit}** folder
 8. Open **STREDIT.exe**
 9. Click **File**
 10. Expand **Open..**
 11. Click **Open new file...**
 12. Navigate into the **{client}** folder
 13. Click **MapleStory.exe**
 14. Click **Open**
 15. Click **No**
 16. Click **No** again
 17. Change all three IPs listed in the **Configured IPs** section to match **HOST** in the **configuration.ini** file
 18. Click **File**
 19. Click **Save**

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