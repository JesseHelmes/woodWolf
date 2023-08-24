-------------------------------------------
Source installation information for modders
-------------------------------------------
This code follows the Minecraft Forge installation methodology. It will apply
some small patches to the vanilla MCP source code, giving you and it access 
to some of the data and functions you need to build a successful mod.

Note also that the patches are built against "unrenamed" MCP source code (aka
srgnames) - this means that you will not be able to read them directly against
normal code.

Source pack installation information:

Standalone source installation
==============================

See the Forge Documentation online for more detailed instructions:
http://mcforge.readthedocs.io/en/latest/gettingstarted/

Step 1: Open your command-line and browse to the folder where you extracted the zip file.

Step 2: You're left with a choice.
If you prefer to use Eclipse:
1. Run the following command: "gradlew genEclipseRuns" (./gradlew genEclipseRuns if you are on Mac/Linux)
2. Open Eclipse, Import > Existing Gradle Project > Select Folder 
   or run "gradlew eclipse" to generate the project.
(Current Issue)
4. Open Project > Run/Debug Settings > Edit runClient and runServer > Environment
5. Edit MOD_CLASSES to show [modid]%%[Path]; 2 times rather then the generated 4.

If you prefer to use IntelliJ:
1. Open IDEA, and import project.
2. Select your build.gradle file and have it import.
3. Run the following command: "gradlew genIntellijRuns" (./gradlew genIntellijRuns if you are on Mac/Linux)
4. Refresh the Gradle Project in IDEA if required.

If at any point you are missing libraries in your IDE, or you've run into problems you can run "gradlew --refresh-dependencies" to refresh the local cache. "gradlew clean" to reset everything {this does not affect your code} and then start the processs again.

Should it still not work, 
Refer to #ForgeGradle on EsperNet for more information about the gradle environment.
or the Forge Project Discord discord.gg/UvedJ9m

Forge source installation
=========================
MinecraftForge ships with this code and installs it as part of the forge
installation process, no further action is required on your part.

LexManos' Install Video
=======================
https://www.youtube.com/watch?v=8VEdtQLuLO0&feature=youtu.be

For more details update more often refer to the Forge Forums:
http://www.minecraftforge.net/forum/index.php/topic,14048.0.html


This mod is basicly 2 mod ideas in 1
the orignal is Woodwold with is the timberwolfs from My Little Pony
and the changeling dark stone also from My Little Pony, this one came later

Features
- Items
	- wood wolf sticks
		- burn time 1600
	- Changeling anti magic sword
		- does not take damage
		- clear active potions from all players in a range of 10 blocks when in inventory of a player
	- Dark stone Block item
		- clear active potions from all players in a range of 10 blocks when in inventory of a player
- Blocks
	- Dark stone
		- can be found in Badlands, Default (don't know what this is)
	- Dark stone stairs
	- dark stone fence
	- dark stone fence gate
	- dark stone wall
	- dark stone slab
	- dark stone button
	- dark stone pressure plate
- Entities
	- Wood wolf
		- spawn egg
		- spawn in biomes: Dark Forest, dark forest hills
		- scares away creepers

Planned features
- when updating to new version remove MobSpawnEggItem as Forge has this
- Woodwolf spawn from merged Woodwolf sticks
- make a hive structure from the hive blocks
- lose all effects when near a hive block
- rename items and blocks
- split mod into 2
- rename example_item to woofwolf sticks in code
- rename classes in code
- remove unused code

Known bugs
