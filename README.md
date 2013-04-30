Go3moku
=======

This is a 3D tic-tac-toe game. It offers human to human, as well human to computer or computer to computer gameplay. It has not many features, but works.

![A finished gameplay (computer to computer)](https://github.com/downloads/hroncok/Go3moku/screenshot.png)

Compile
-------
You can compile Go3moku either from Netbeans IDE, or using ant:

````
ant jar
````

Run
---
After successful compilation. Run Go3moku.jar from dist folder:

````
java -jar dist/Go3moku.jar
````

Add `--help` for list of available options.

````
java -jar dist/Go3moku.jar --help
````

Develop
-------
If you want, you can implement a better user interface by implementing an **UI** interface, or you can create smarter computer players by implementing **Player** interface.

License
-------
This is a free software, it is released under the terms of ISC license (more info in COPYING).
