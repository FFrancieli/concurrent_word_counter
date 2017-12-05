# concurrent_word_counter

The program runs through files defined on Main class and counts the word frequency in both files. At the end the following 
input will be printed on the console for each word found:
``<word>	<total	occurrences>	=	<occurrences	in	file1>	+ <occurrences	in	file2>``

The following items are removed from the file content, since they are not considered words:
* empty spaces
* especial caracters (Eg.: *@#$%^~+)
* punctuation (!,.?:;)

Note that the apostophe is accepted as part of a word.

#### Dependencies
* Java 8
* [Gradle](https://gradle.org/)

#### Running tests

`gradle test` or
`./gradlew test` (if you don't have gradle installed you may run gradlew script instead. It may take some time when running for the first time.)

