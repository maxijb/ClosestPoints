# ClosestPoints



Finding the top K similar points on a 2D space, for a a large set of items, is a common problem in computer science.

The naive solution would implement a O(N^2) process, comparing each item with all of the others. A sample of this solution can be found on this blog post, written by someone at my company.

However, as we’re developers who’re always asking themselves how to do better, we can arrive to a more optimal solution. The algorithm I’m proposing here can find the 10 closest points for each, into an array of 500.000 items, in less than 3 minutes in a regular laptop with a SATA disk.

And it does so, using some common sense. The final algorithm can be found at the source panel, if you want to skip the whole explanation. The implementation is donde in Java, since it’s a language I feel comfortable with, but can be replicated with any other language.

Otherwise, here we go with it…

##The data

First we need to understand that each item is composed by, basically 2 coordinates and an ID. As I was working at Booking.com at the time I wrote the algorithm, let’s call this class Hotel…

The class holding the data from where we’ll pick our closest items, must implement a few methods. I created an interface with these methods. They’re:

**compareTo(Hotel other);**// extended from comparable, because we’re gonna need to sort the data

**getEuclideanDistance(Hotel other);**// real distance to other point

**getXDistance(Hotel other);** // get distance in one dimension from the other point.
 
The algorithm
With the backing data already in place, we can now start the algorithm.
The first step is sorting the array of points, by the most significative dimension (the one with biggest variance). This way, we’ll get an array looking something like this (assume that first coordinate is X and second Y):

*[ {0, 3}, {1, 5}, {2, 5}, {4, 40}, {5, 2} ] //points are sorted by the first dimension X*

It may seem a little heavy to sort every point of the array, but hopefully we’ll see that this might even be the bottleneck in the whole process.

Once the data’s been sorted, we start iterating over the elements of the array.

So we pick the first one located at {0, 3}. As this the first node and we know that the points are sorted by the dimension, we certainly know that the item located at {1, 5} is closest one if we only care about the X dimension.

So, at this time we assume that this is one of the closest K points (let’s say K = 2 for this example). We’ll use a max_heap to keep track of the closest already found points for this item.

The keys of the heap will be the ecuclidean distance (the real distance) between the two points, calculated as: **sqrt( |X1 – X2|2 + |Y1 – Y2|2 )**

Which eavluates to: 
sqrt( |0 – 1|^2 + |3 – 5|^2) –> sqrt(1 + 4) –> sqrt(5) —> **2,23… this will be our key into the heap**

As the heap is not complete (we’re looking for the 2 closest hotels) we follow adding the next item in the array to the heap. For this element the key in the heap is going to be 2,82.

Then, as we get to our third iteration, we have a heap composed by:
- key 2,82 -> item {2, 5}
- key 2,23 -> item {1,5}

At this point we know that it the next point is going to be one of the K (2) closest hotels in the array, the difference between the X dimension of the current point X0 and the same dimension of the next point to be evaluate X3 must be smaller than the peek of the heap.

So, since the current item for which we’re searching closest points is {0, 3}, we positively know that the next item to be considered must in the range of -2,82 and 2,82 in the X dimension. Since our next point is {4, 3} we know that this is not going one of the closest points.

So, by now we’ve already found the best 2 candidates for the most similar items, and we can conclude with this item. We just need to move the items in the heap to where we’ll keep our results.
Wrapping up

All we have to do from now on, is just repeat the process for every item, taking care always that the next candidate ot be one of the closest is the one with the lesser distance int he X dimension. So for every node i, the closest one can be (i + 1) or (i-1).

The running time of this algorithm is a bit tricky to calculate… we know that the initial sorting can be done in O(N log N). Then we iterate linearly over item O(N), adding items to the heap which is O(log K).

The tricky part is to calculate how many items we’ll have to compare with the current evaluated node, until we find the top K similarities. And many variables can influence this number. Of course, a bigger K will produce a rise in the number of comparisons. Also the variance of the Y dimension will increase the required processing. Let’s understand that the maximum distance in the heap, creates an “allowed band” within the X dimension, where the best matches can live within. The rest of the array is ignored.

So, my best intuition tells me that this algorithm to O(N log N), but if think you can get a more accurate bound, please reach me and let me know.

Hope you’ve enjoyed the article…
Cheers!!!
