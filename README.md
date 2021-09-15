# Automatic Robot Maintenance
## Semester assignment 1

### Scenario
You are hired as a consultent at Automatic Robot Maintenance (ARM). They have many years of experience  in robot technology and cybernetics. Recently ARM has landed a new contract for maintaining a large field of solar panels. Close to the solar panels there is a garbage disposal plant and some trash has a tendency to cover and dirty the solar panels, so that they do not produce any electricity. The company who owns the solar panels has constructed a system to detect if solar panels are recieving less light so that they can check for trash covering them. However, they have become tired of manually cleaning the solar panels and has therefore hired ARM to automate this task. The robots have been programmed to move on the solar panel field and to clean the solar panels, but currently it is taking too long to move the robots to where they need to be. 

ARM is in need of an algorithm to choose the correct robots to clean which solar panels and to position the robots so that they can execute a cleaning job as soon as the system detects one. ARM gets payed extra for each solar panel they clean, but lose money for each minute a solar panel is not operational due to being dirty.

### Online algorithm
This is what we call an online algorithm. You have to make choices based on the information you have in the moment and when future tasks arise you soon discover that your original choices where not optimal.

### Code 
You are given a framework which includes:
 * Location.java - describes a location using double values ``x`` and ``y``
 * Job.java - describes a job generated by the alert system which monitors if the solar panels are generating electricity. A job object includes:
    * ``Location location`` - describes where the solar panel is located
    * ``double time`` - denotes the time when it was discovered that the solar panel stopped producing electricity
    * ``int robotsNeeded`` - denotes the number of robots required for cleaning the solar panel
 * Robot.java - represents a robot for cleaning. Set of methods controlling the robot:
    * ``getLocation()`` - get last known ``Location`` of the robot
    * ``getJob`` - get the current ``Job`` assigned to the robot
    * ``move(Job job)`` - assigns the ``Job`` to the robot. Robot moves towards the location of the job. Aborts any previous commands
    * ``move(Location location)`` - robots moves towards the given ``Location`` and awaits further orders. Aborts any previous commands

The package ``inf102.h21.system`` contains code for simulating the environment of robots and solar panels. **You do not have to look at this code**.
The package ``inf102.h21.generators`` is used to generate input for the system. Each generator constructs input of robots and solar panels placed around the field. To visualize the input run ``Visualizer.java``. 

## Task
Your job as consultant on the project is to devise a strategy for manuevering the robots around the field to allow the solar panels to generate the most amount of electricity over time. For this you must implement the interface ``IStrategy``:
```java
public class MyStrategy implements IStrategy {
    ...
}
```
This interface contains three methods which the system calls when a new cleaning job is detected. In addition to implementing these three methods it will be neccessary to create a set of variables and helper methods to effectivly manage the robots.

Currently there is an abstract class ``AbstractStrategy``:
```java
public abstract class AbstractStrategy implements IStrategy {
    ...
}
```
which contains a set of field variables and helper methods which may be helpful to complete the task. But there is no requirement to make use of this class. You may directly implement ``IStrategy`` if you want to.

### Task 1
**Implement RandomStrategy**
In the first task you are to implement a random strategy which selects a random robot out of the available robots to execute the incomming cleaning jobs. The robot you choose must not be busy with another job. 

