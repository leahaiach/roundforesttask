Roundforest task - Leah Aiach

limitation: It took me 5 hours to implement this task. I didn't have time to do the google translation part.


Question

-->How do you make sure that there are no duplicates in the file?
The user id of a user is what defines him uniquely. A user can change his profileName but not his id. This means that a user can have
different profileNames. 
In my code , I have used a class User with two fields :id and profileName. I have overriden the method equals and hashcode 
so that when checking if a user already exist we check it by its id.
For the most used word I have deceided to make the word count case-sensitive (ex: is and IS are two different words),
 I could make it case-insensitive it is considerate as duplicate.

-->We are interested in using full multi core CPU power.
I could implement it using java 8 streams in order to distibute the line reading and processing

-->We will be running this on machine with 500MB of RAM. How do you make sure that we are not using more than that? 
-->How are you going to monitor the memory usage of your program?
I don't store all the file in the memory at all. I store the lines one by one and I put all the information needed in hashmaps.

-->Our goal is to support the files with up to 100M reviews on multiple machines with 500MB of RAM and 4 core CPUs. 
-->How are you going to make it happen?
We distribute 500k lines to each computer. They create the hashmaps as in the solution and then we merge all the hashmaps. 
According to the size of the hashmaps, we partition them and distribute them to some computers so that they get the
1000 most frequent products/users/words with their count and then we merge the 1000 element PriorityQueues 
to get the 1000 most frequent element we need.

