Core-Java-Reapply
=================

A word/line/character count utility written in Java for HackBulgaria Android course.

This is an Eclipse project.  

It uses the org.json library to format the result when not specified what to count (lines, words or characters).

In order to run, simply navigate to `/jar/wc.jar` from the terminal and then run
`$ java -jar wc.jar [path-to-txt-file] [-c/-w/-l]`

Where:
* `path-to-txt-file` is the path to the text file to be scanned
* `-c` is for counting characters
* `-w` for counting words
* `-l` for counting lines, 
* or omit the last argument to count everything
