# v1.0

- [x] add README file for this laboratory work
- [x] make colection package with all classes
- [x] make default sorting implementing Comparable interface, overriding compareTo method
- [x] Make comparator for City objects using Comparator interface
- [x] Think of logical project structure
- [x] Make sure that you separate `user` from `server` for next lab
  
#

- [x] create CSVManager
- [x] create CollectionManager
- [x] create Managers interface
- [x] create .csv file
- [x] make `serialization` from TreeSet collection to .csv (for save command)
  - [x] create write method in Managers interface
  - [x] create write method in CollectionManager
  - [x] create write method in CSVManager
- [x] make `deserialization` from .csv to TreeSet collection (for show and add commands)
  - [x] create read method in Managers interface
  - [x] create read method in CollectionManager
  - [x] create read method in CSVManager

#  
  
- [x] create userManager
  - [x] create working workflow
  - [x] complete it
  
#

- [x] make commands
  - [x] make help command
  - [x] make info command
  - [x] make show command
  - [x] make add element command
    - [x] make createNewCityByUser method in UserManager
    - [x] make requestEnum method in UserManager
  - [x] make update_id command
  - [x] make remove_by_id command
  - [x] make clear command
    - [x] make backup file for default data in .csv
  - [x] make save command
  - [x] make execute_script file_name command
    - [x] make scriptFile.txt
  - [x] make exit command
  - [x] make add_if_min command
  - [x] make remove_greater command
  - [x] make history command
  - [x] make sum_of_meters_above_sea_level command
  - [x] make print_descending command
  - [x] make print_field_descending_meters_above_sea_level command

#

- [x] Make id
  - [x] Make id generated automatically
  - [x] Make id unique

#

- [x] create checking for `^D` and other unexpected symbols in `std:in`
  - [x] `^D` symbol causes EOF, so what should I do?)
- [x] Make sure the `execute_script` in `execute_script` doesn't run indefinitely
  - [x] check recursion when file1 reference file2, which also references file1
- [x] make `UML` diagram using plantUML
- [x] generate `javadoc` for everything
- [x] make `report` in .docx and .pdf
  
#

- [x] generate lab5.jar file
- [x] upload it to `helios` via scp command
#

# v2.0

- [x] create validators for each City argument in collectionManagers.validators
  - [x] create Validator interface
  - [x] create NameValidator
  - [x] create CoordinateXValidator
  - [x] create CoordinateYValidator
  - [x] create AreaValidator
  - [x] create PopulationValidator
  - [x] create MetersAboveSeaLevelValidator
  - [x] create ClimateValidator
  - [x] create GovernmentValidator
  - [x] create StandardOfLivingValidator
  - [x] create HumanValidator
  - [x] create InputValidator
- [ ] use those validators in CSVManager

#

- [ ] create userMode and nonUserMode packages in colectionManagers.handlers
  - [x] create CityNonCLIManager in nonUserMode
  - [x] create CityCLIManager in userMode
    - [x] create CoordinatesCLIManager in userMode
    - [ ] create HumanCLIManager in userMode
    - [ ] create ClimateCLIManager in userMode
    - [ ] create GovernmentCLIManager
    - [ ] create StandardOfLivingCLIManager in userMode
  - [ ] write working code in both 
- [ ] create createCityObject() method in 
- [ ] create interfaces for handlers


- [ ] create 2 modes: user and nonuser
  - [ ] make CommandMode enum for creating this 2 modes
  - [ ] make ModuleHandler for creating objects based on given CommandMode mode
  - [ ] add it to CommandManager constructor
  - [ ] based on mode, give scanner to nonCLI and nothing to CLI handler
  - [ ] give every command, that could ask for nonuser mode just created handler object
  - [ ] store all commands in LinkedHashMap for normal storing order, not just HashMap

#
- [ ] IdManager class
  - [x] rename CityIdChecker to IdManager
  - [x] create generateId method
  - [ ] create checkCityById method

#
- [ ] Add iteration of enums validation to enumValidators instead of checking for null values

#

- [ ] rewrite new UserManager class
  - [ ] delete isWorking in Main and in UserManager.
  - [ ] It takes input stream and command mode (not hardcoded System.in, like before)
  - [ ] checks if next line is empty or not in while() loop
  - [ ] if not it calls executeCommand method in CommandManager
  
  
- [ ] make executeCommand in CommandManager, not in userManager
  - [ ] executeCommand check for every possible exception and than calls .execute method from Command interface
  - [ ] throw all those exceptions to later handle them in 

#
- [ ] Make readFromFile method in CSV Manager return not only City-like object, but csvParser object (say no to hardcode!)
- [ ] Then, make loadCityCollection method in CityManager iterate through fields like readFromFile method before
#

- [ ] create getDescription for each command
- [ ] make help command not just sout String, but iterate through getDescription method of each command in CommandManager and write it's description
- [ ] exit command now uses system.exit(0)
- [ ] add command uses CityCLI or nonCLI managers for createCityObject() method using CollectionHandler interface

# v2.1
- [x] delete enum validators, because there is nothing to check
- [x] create CLIManagerUtility for handy enum creation managing


# ?
1) system.exit(1)? (2)? other numbers?
