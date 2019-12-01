# a8-zliyu
Game of Life (Assignment 8 - COMP 401, Fall 19)

An implementation of Conway's Game of Life. A description of the game can be found here: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

In the Wikipedia article, the game is described as being on an "infinite" grid. Your version should use a fixed sized field that the user can control the size of. You can assume that cells outside the boundary of the field are always dead. You are free to reuse the SpotBoard and related classes.

A basic version will have the following features:

-Ability to change the size of the field from 10x10 up to 500x500.
-Ability to manually set / clear any cell in order to set up patterns
-Ability to clear the entire field.
-Ability to fill the field randomly.
-Ability to advance the game by pressing a button.
-Written with a Model View Controller architecture

An intermediate version will also include:

-Ability to set the "survive" and "birth" thresholds to custom values. The default threshold values in the classic game brings a dead cell to life if the number of neighboring live cells is greater than or equal to 2 (low birth threshold) and less than or equal to 3 (high birth threshold) and otherwise stays dead. A living cell survives if the number of neighboring live cells is greater than or equal to 3 (low survive threshold) and less than or equal to 3 (high survive threshold) and otherwise will de. 
-Ability to toggle "torus" mode on or off. In torus mode, the field is treated as if it wraps around the edges back to the other edge.

An  advanced version will additionally have:

-A start/stop button that advances the game automatically using a separate thread with a delay between updates settable between 10 milliseconds and 1 second.
