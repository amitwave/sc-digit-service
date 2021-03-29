This project reads the contents of the file and parses them into digits.

Each digit is composed of underscore and/or piper _ and/or |

Using file stream, the contents are read into a collection and then a 3X3 matrix is created.

ach vertices are numbered with a weight. the sequence of the _ and | constructs a unique key and 
based on that a value is fetched from the map.

The DigitService in sht main interface. and Digit class is the self sufficient class to tell its value based on the 
input and arrangement of the _ and |.

All the test files are in the test resources.

This can be further enhanced and for that I would need more time.

