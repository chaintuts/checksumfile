## General
____________

### Author
* Josh McIntyre

### Website
* jmcintyre.net

### Overview
* CheckSumFile is a simple file hash-based malware scanner

## Development
________________

### Git Workflow
* development for bugfixes and new features

### Building
* make build
Build the application
* make tests
Build the unit tests
* make clean
Clean the build directory

### Features
* Calculate the SHA-256 hash of a chosen file
* Search a threat database for matching file hashes and report a match
* Includes a short sample threat database

### Requirements
* Requires Java Runtime Environment

### Platforms
* Linux
* Windows
* Mac OSX

## Usage
____________

### GUI Usage
* Choose a file using the file chooser dialog
* Click `Scan` to hash the file and search the database for any matches
* GUI will report the file hash and if it matches any signature

### Threat Database Usage
* A small sample database is included
* Update with your own desired entries in the format `<hash>,<name>: <description>`

### Unit Tests
* Run `java -jar tests/junit.jar --class-path obj/ --scan-class-path`
* junit jar is not included with repo; add your own junit 5 download to `tests/junit.jar`
