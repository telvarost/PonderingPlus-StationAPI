# PonderingPlus StationAPI Edition for Minecraft Beta 1.7.3

A b1.7.3 mod using StationAPI and babric (beta fabric) that was inspired by a desire to sit and read books and a love for libraries.
* Mod works on Multiplayer with [GlassConfigAPI](https://modrinth.com/mod/glass-config-api) version 3.0+ used to sync configs!

## List of changes

* Added the ability to sit by right-clicking any normal block with a book
  * Optionally you can disable sitting on the book using the config provided by [GlassConfigAPI](https://modrinth.com/mod/glass-config-api)
  * Use the sneak key to stand back up
  * If you log out and log back in while sitting you will fall into the block you sat on
    * To fix this, install either [AnnoyanceFix-StationAPI](https://modrinth.com/mod/annoyancefix-stationapi-edition) or [UniTweaksTelsAddons](https://modrinth.com/mod/unitweakstelsaddons-stationapi) and enable vehicle logout/login fixes
* Added the ability to write a message into a bookshelf (this will convert it into a modded block type)
  * Right-click a bookshelf with a feather in hand and black dye (ink sacs) in your inventory
    * One black dye (ink sac) will be consumed and the block will convert into an editable bookshelf
  * Editable bookshelves do not catch on fire, take slightly longer to break, and show a message when right-clicked
    * Editable bookshelves drop regular bookshelves when broken and the written data is lost
    * Their texture is also reversed to distinguish them from normal bookshelves (they will still use the bookshelf texture from terrain.png)
    * The particles are also ever so slightly different as they pull from the side texture rather than top texture
  * Once a bookshelf has become editable, you can edit it any number of times without consuming further items
    * Right-click the bookshelf (item in hand does not matter) to read/edit the bookshelf message

## Installation using Prism Launcher

1. Download an instance of Babric for Prism Launcher: https://github.com/Glass-Series/babric-prism-instance
2. Install Java 17 and set the instance to use it: https://adoptium.net/temurin/releases/
3. Add GlassConfigAPI 3.0.2+ to the mod folder for the instance: https://modrinth.com/mod/glass-config-api
4. Add Glass Networking to the mod folder for the instance: https://modrinth.com/mod/glass-networking
5. Add StationAPI to the mod folder for the instance: https://modrinth.com/mod/stationapi
6. (Optional) Add Mod Menu to the mod folder for the instance: https://modrinth.com/mod/modmenu-beta
7. Add this mod to the mod folder for the instance: https://github.com/telvarost/BetaTweaks-StationAPI/releases
8. Run and enjoy! 👍

## Feedback

Got any suggestions on what should be added next? Feel free to share it by [creating an issue](https://github.com/telvarost/PonderingPlus-StationAPI/issues/new). Know how to code and want to do it yourself? Then look below on how to get started.

## Contributing

Thanks for considering contributing! To get started fork this repository, make your changes, and create a PR. 

If you are new to StationAPI consider watching the following videos on Babric/StationAPI Minecraft modding: https://www.youtube.com/watch?v=9-sVGjnGJ5s&list=PLa2JWzyvH63wGcj5-i0P12VkJG7PDyo9T
