# AllocineIndexer

The project goal is to retrieve many movie details quickly.
From a list of hundreds of movies, you can get the following information:

  - The movie title
  - The public grade (from the allocine website)
  - The presse grade (also from allocine)
  - The movie type (horror, comedy...)

# How to use it

## 1/ Get the runnable jar
### The easiest way
 
 Get the latest compiled JAR [here](https://github.com/ymougenel/allocineIndexer/raw/release/allocineExtracter.jar)

### The other way
 
 Download the project from [github](https://github.com/ymougenel/allocineIndexer/archive/master.zip)

 Compile the source code
```sh
$ mvn clean compile package 
```

## 2/ Run the jar

Copy the sourcefile source.txt next to the jar location.

Run the jar
```sh
$ java -jar allocineallocineExtracter.jar
```

# Author

I'm currently working on the project on my own ([@ymougenel](https://github.com/ymougenel)), any contribution/suggestion is more than welcome.

# Contribute

## Issue/feature

While using the project, if you encounter any bug or frustration, feel free [to open an issue](https://github.com/ymougenel/allocineIndexer/issues). It also applies to all the suggestion you have regarding some future enhancements.

## Merge request

If you want to contribute, please respect the following guideline:
- Describe your MR, including the purpose and changes
- Ensure your code is tested, in order to maintain a stability

# License

The project is open-source, it therefore complies to all the terms related to [the open-source philosophy](https://en.wikipedia.org/wiki/The_Open_Source_Definition).
