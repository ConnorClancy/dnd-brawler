# D&D Brawler Engine
Automatic combat runner for ttrpg D&D. Project takes in JSON sourced combatants, converts them into functioning objects and pits them against each other using D&D mechanics. 

## How To Use
The project can currently be run in an IDE, the desired matchup is fed in as command line arguments. The results and the round-by-round combat will be outputted in the command line.

The structure of the arguments is as follows:
> \<monster name> <team [red/blue]> \<number of monsters>

And example input might look like the following:
> direwolf red 3 blackbear red 5 goblin blue 10 crocadile blue 2 skeleton blue 6 

![image](https://user-images.githubusercontent.com/22297137/205460833-2d3b19e4-d05c-4040-a2c7-ea24fe037020.png)

**Currently implemented monsters:**
* Ankheg
* Balor
* Behir
* Black Bear
* Brown Bear
* Crocodile
* Direwolf
* Flying Sword
* Giant Eagle
* Goblin
* Green Dragon
* Skeleton 
* Troll

## Output
The results can be returned to either the console or to a JSON file. By default, it will be returned to the console. To toggle this, change line 33 of *GameRunner.java* from *getBrawlOutputter()* to *getBrawlOutput()* (the details of these methods can be found in *BrawlOutputter.java* class).

![image](https://user-images.githubusercontent.com/22297137/205492821-5e32239a-c3e5-4af6-a680-f6a35abc9be9.png)

The format of the output is as follows: debugging information will be displayed in red text, the winner of the brawl and how many of the winning team will be shown, then the turn by turn battle information.

![image](https://user-images.githubusercontent.com/22297137/205461322-944ae3e8-4ac0-4e26-a0b7-21f80910dab2.png)

## Dependencies
The org.json.jar file is required for the code to run. Download the latest version for [here](https://jar-download.com/artifacts/org.json). To configure the path in Eclipse, right click on the project and select Build Path > Configure Build Path. Navigate to the Libraries Tab and edit the path for the json-20220302.jar to the where the downloaded jar is stored on your machine. Click Apply and Close.

![image](https://user-images.githubusercontent.com/22297137/205461020-6005a2c9-dfad-4ab0-a2e3-1462bef1ec15.png)
