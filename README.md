QME
===

This is the GitHub repository for our game. Here are some descriptions of the various folders:<br/>
**res**: the folder for audio clips, sound files, etc.<br/>
**data**: world saves, preference settings, etc. This folder will be specific to each user.<br/>
**src**: source code for the game.

If you need to add something to this page but aren't sure how, please contact a programmer.

The code can only be built with Eclipse for now. If you need a new release, contact a programmer
to build it, or check the "Releases" tab.

The Javadoc folder has been removed, because every tiny change in the code requires a massive amount
of new git adds. To generate it, go into Eclipse, select Project > Generate Javadoc, and select all
folders in `src` with a check mark. Or, ask a programmer.

Changelog for pre0:
  - Made the game
  - Added scrolling
  - Added buttons
  
Changelog for pre1:
  - Fixed mouse clicks
  - Added game selection menu and next player button
  - Added a game state system and improved world gen
  - Added tile tooltips
  - Added button and tile highlights upon hovering
  
Notes for pre1:
  - 2072 lines of code! Yay!
  - There is currently no way to see the list of players or the turn count. Coming soon ...

Changelog for pre2:
  - Added random AI names
  - Added rendering layers
  - Added some unit backend for later
  - Changed rendering from "flat" to 3/4 perspective
  - Added tile coordinates to tooltip display
  - Added escape menu (press the escape key to access)
  - Added UI for creating players (human / AI, names)
  - Added tech backend for later
  - Added debug screen (press q to toggle in main game)
  - Added game info display (for player name / turn, currently untogglable)
  
Notes for pre2:
  - 2683 lines of code (wow)
  - You need a file for error logging (if you don't have one, you will be prompted to fix it)

Changelog for pre3:
  - Added tooltip preferences
  - Added a tech tree UI
  - Finished up the tech tree code
  - Added units and all of their diverse types
  - Created a framework for the AI
  - Created framework for player statistics
  - Added more name gens (unused as of now)
  
Notes for pre3:
  - 4257 lines of code (that's a lot)
  - Enter the tech tree view with the t key, exit with either the back button or `esc`
  - Techs are blue if you have them, green if you can buy them, and black otherwise (currently no way to buy techs)
  - Your preferences will be stored in a file called `qdata/preferences.txt`. Don't delete this file.
  - In future releases, game builds will be with a ZIP file, because resources!
