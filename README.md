# Automatic Robot Maintenance
## Semester assignment 1

### Scenario
You are hired as a consultent at Automatic Robot Maintenance (ARM). They have many years of experience  in robot technology and cybernetics. Recently ARM landed a new contract for maintaining a large field of solar panels. Close to the solar panels there is a garbage disposal plant and some trash has a tendency to cover and dirty the solar panels, such that they do not produce any electricity. The company who owns the solar panels has constructed a system to detect if solar panels are recieving less light so that they can check for trash covering them. However, they have become tired of manually cleaning the solar panels and has therefore hired ARM to automate this task. The robots have been programmed to move on the solar panel field and to clean the solar panels, but currently it is taking too long to move the robots to where they need to be. 

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

The package ``inf102.h21.generators`` is used to generate input for the system. Each generator constructs input of robots and solar panels placed around the field. To visualize the input run ``Visualizer.java``. You will see a screen of white and red dots. White dots are robots and red dots are jobs that need to be executed (solar panels that need cleaning). Visualizing the input might help you devise your own strategy in task 3.

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

Note that jobs may often require more than one robot to be able to execute.

### General Task
For each of the following tasks you are to implement classes with a set of their own methods. All methods you utilize to manouver the robots, i.e. the methods in ``IStrategy``, ``AbstractStrategy`` (if you use it) and all classes implemented in task 1, 2 and 3, you must give a runtime analysis (using *Big-O*). Fill out svar.md with what runtime each method has for the different strategies and why they have that runtime.
In particular the runtime of selectRobot() method in task 1 and 2 will be important.

To see how efficient the different strategies are run ``TestClient.java``. Add the strategy you want to test in ``TestClient::getStrategy``.

### Task 1 - RandomStrategy
Implement ``RandomStrategy``, a strategy which selects ``k`` **random** robots out of the available robots to execute the incomming cleaning jobs. The robot you choose must not be busy with another job. 

### Task 2 - ClosestStrategy
Implement ``ClosestStrategy``, a strategy which always chooses the ``k`` **closest** robots for the incoming cleaning jobs.The robot you choose must not be busy with another job.

### Task 3 - BetterStrategy
Implement your own strategy: ``BetterStrategy``. Apply all your algorithmic knowledge to best manouver the robots.

This task does not require you to develop an optimal algorithm for manouvering the robots. As long as all jobs are executed then we will approve the solution. A normal algorithm has correct and incorrect answers, but in this task you are to develop a heuristic. A heuristic has many correct answers, some more correct than others. 

In addition to implementing ``BetterStrategy``, changes in the methods in AbstractStrategy can give you a better performance.

## Grading
This mandatory assignment will count 15 % towards your final grade. You will recieve a score between 0 and 15.
The following rubric will be used to assess you assignemnt:

### Code Quality
Code quality gives 0-3 points.
 * The code must be clear and readable
 * Avoid repetition of code
 * Utilize concepts from INF101 to write maintainable and modular code

### Runtime Analysis (svar.md)
Runtime analysis gives 0-3 points.
 * Every method you use must have a runtime analysis using Big-O notation. You get points if the runtime is correct, but a reduction in score if it is incorrect. 
 * The runtime analysis must be written in svar.md. In addition to Big-O notation you must add a description of why the method has this runtime.
 * The runtime should be expressed using three parameters
    * ``m`` - number of jobs in the simulation (input up to 1 000 000 can be expected)
    * ``n`` - number of robots in the simulation (input up to 100 can be expected)
    * ``k`` - number of robots required for a job (input up to 10 can be expected)

Note that not all of these parameters will be relevant to all methods. Some methods might just be dependent on one or two of the parameters.

### Functional and Efficient Algorithms and Datastructures
Correct and appropriate use of algorithms and datastructures gives 0-9 points.
The goal of this task is to write algorithms that both solve the problem yielding the best result possible, i.e. the solar panels produce as much electricity as possible, and the code runs as efficiently as possible. In this task you will need to use algorithms and datastructures you have learned in this course, for instance: ``LinkedList``, ``ArrayList``, ``HashMap``, ``PriorityQueue``, etc. We will look at every method you have implemented and assess if you have done this as efficiently as possible.
 * **Task 1** gives 0-3 points.
    * We will assess if your implementation is functionally correct. You can check this by running ``RandomTest.java``
    * We will assess whether the methods and classes implemented to complete the task are written as efficiently as possible
 * **Task 2** gives 0-3 points.
    * We will assess if your implementation is functionally correct. You can check this by running ``ClosestTest.java``
    * We will assess whether the methods and classes implemented to complete the task are written as efficiently as possible
 * **Task 3** gives 0-3 points.
    * 1 point is given by outperforming ``RandomStrategy`` and ``ClosestStrategy`` on some inputs
    * 2 point is given much better than ``RandomStrategy`` and ``ClosestStrategy`` on most inputs
    * 3 points are given to the most efficient solutions
