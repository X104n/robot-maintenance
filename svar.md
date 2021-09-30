# Answer File - Mandatory 1
# Description of each Implementation
Briefly describe your implementation in the different strategies. What was your plan and how did you execute it? If there were any problems and/or issues failed implementations please add a description.

## Task 1 - RandomStrategy
*For this task I went with the strategy to just shuffle the available list to get the random numbers and then use a for loop to get the k first numbers of robots in the list to do the job. Notice I'm using linkedList as the return value, because if we were to add elements to an array list this would result in a runtime of O(n) in the worst case scenario.*

## Task 2 - ClosestStrategy
*For this task I use a PriorityQueue in a combination with a comparator to sort out the robots in an ordered fashion such as the nearest robot to the job comes first in the queue.*

## Task 3 - BetterStrategy
*For this final task I made some mistakes and here I want to discuss them. To begin with I didn't realize until very late in the exercise how exactly the different "solar panel fields" where set up. I thought every map was completely random robots over a vast field with some robots all over the place. So my approach was to use the already made closeStrategy as it was to select the robots then, implement a moveRobots() where I could spread them evenly over the field I was given. I think I completed this task fairly good, however it isn't as modular as I hoped it to be as I need to specify a lower and upper bound myself to make sure the algorithm covers the whole area. If I had some more time to do this task I would most likely end up creating some sort of list over the previous jobs and spread the surrounding robots around these jobs. And with even more time I could hopefully implement some form of heat map.*


# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation and a description of why it has this runtime.

*Ps: check the code to see the calculation of each of the functions*

**If you have implemented new methods not listed you must add these as well.**
## Task 1 - RandomStrategy
### IStrategy
* ``registerRobots(List<Robot> robots)``: O(n)
    * *Here the runtime of the function is O(n) + O(n) because we make two new arraylists. This equals O(2n) = O(n)*
* ``registerNewJob(Job job)``: O(mkn + m^2)
    * *Here we have an .add function, and then we have doJobs which have a much higher runtime, and we can disregard the .add O(1). And the runtime becomes O(mkn) or O(m^2). Ps:(See Task 1, AbstractStrategy, doJobs())*
* ``registerJobAsFulfilled(Job job)``: O(mkn + m^2)
    * *Here we have the same thing as in registerNewJob where the .addAll function becomes disregarded because doJobs have algorithmically a bigger run time than .addAll which is O(n).*

### AbstractStrategy (if you use it)
* ``doJobs()``: O(mkn + m^2) 
    * *On doJobs we have the runtime from selectRobots inside off the code. The worst case in this instance would be O(mkn) or O(m^2). This is depending on if we assume that all the variables can go to infinity, then we cannot decide weather O(mkn) or O(m^2) has the biggest runtime. Furthermore, we have the knowledge from README.md that m>n>k in most instances which would give O(m^2) the longest runtime. But ill take base on what martin said to me which was we cannot decide which of these are the largest*
* ``selectJob()``: O(1)
    * *On selectJob we only look at the first job in the queue, therefore the runtime is O(1)*
* ``removeJob(Job job)``: O(m)
    * *On remove Job the worst case is if we need to remove the last job in the queue which means the runtime will be O(m)*
* ``assignRobots(List<Robot> selected, Job job)``: O(kn)
    * *The worst case in this method is if canDo is true and the for loop under is used, which gives us the runtime of O(kn)*
* ``getAvailableRobots()``: O(n)
    * *Here we copy the available list, and the worst case in this scenario is if all the robot is available which gives us O(n)*

### RandomStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(k + n)
    * *Since we shuffle the available list we get O(n), further we loop through job.robotsNeeded therefore the runtime becomes O(n + k) and since we don't know which of the n and k have the biggest value we cannot rule out any of them*

## Task 2 - ClosestStrategy
### IStrategy
* ``registerRobots(List<Robot> robots)``: O(n)
    * *Nothing changes here*
* ``registerNewJob(Job job)``: O(mn log n + mkn + m^2)
    * *Here the .add function is only O(1) so here we get the same answer as in doJobs()*
* ``registerJobAsFulfilled(Job job)``: O(mn log n + mkn + m^2)
    * *Here as well we get the same answer as in doJObs as it makes .addAll O(n) obsolete*

### AbstractStrategy (if you use it)
* ``doJobs()``: O(mn log n + mkn + m^2)
    * *DoJobs uses the closestStrategy selectRobots, which was O(n log n). Therefore, we have O(m(n log n) + mkn + m^2) = O(mn log n + mkn + m^2). And here we stumble upon the same problem as in task 1, where we haven't gotten clear information whether to assume m>n>k or to assume that all of them goes to infinity. So im just going to write it as it is O(mn log n + mkn + m^2)*
* ``selectJob()``: O(1)
    * *Reads the first job in the backlog, which is O(1)*
* ``removeJob(Job job)``: O(m)
    * *Worst case is when we have to remove the last job from the list of all the jobs*
* ``assignRobots(List<Robot> selected, Job job)``: O(kn)
    * *Worst case scenario is the same as inn random, which is when it loops through all robots needed for the job and then removes it from the available list*
* ``getAvailableRobots()``: O(n)
    * *Makes a copy of the available list*

### ClosestStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(n log n)
    * *The biggest runtime in this function is the .addAll of the PriorityQueue which gives us the runtime of O(n log n)*

## Task 3 - BetterStrategy
### IStrategy
* ``registerRobots(List<Robot> robots)``: O(n)
    * *Nothing changes here between the different tasks*
* ``registerNewJob(Job job)``: O(mn log n + mkn + m^2)
    * *doJobs is still dominant in this function in betterStrategy*
* ``registerJobAsFulfilled(Job job)``: O(mn log n + mkn + m^2)
    * *doJobs is still dominant in this function in betterStrategy*

### AbstractStrategy (if you use it)
* ``doJobs()``: O(mn log n + mkn + m^2)
    * *Here we also use moveRobots, but this runtime is O(n log m) which is lower than the selectRobot section of this method and the runtime would therefore still be: O(mn log n + mkn + m^2)*
* ``selectJob()``: O(1)
    * *Looks at the information to the first object in the queue, and has the runtime of O(1)*
* ``removeJob(Job job)``: O(m)
    * *Runtime is the same as the other task, and is O(m)*
* ``assignRobots(List<Robot> selected, Job job)``: O(kn)
    * *Nothing changes here aswell, and the runtime is O(kn)*
* ``getAvailableRobots()``: O(n)
    * *The get available is the same as always and has the runtime of O(n)*

### BetterStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(n log n)
    * *The longest runtime here is the same as closestStrategy which gives us O(n log n)*
