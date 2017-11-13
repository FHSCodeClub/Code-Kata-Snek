#Greedy Cowboys
##General Description
Given many greedy cowboys with randomly sized cattle, find out which one lasts the longest. Cowboys kill each others' cattle to try to outcompete each other.
At the start of each iteration, each cowboy earns money equal to the total size of their cattle. Then, each cowboy kills the largest cattle of the richest cowboy. Cowboys do not kill their own cattle. A If the richest cowboy runs out of cattle, then they shoot at the next richest cowboy.
##Input Description
A text file in the following format:

`[Cowboy]:[Cow], [Cow], [Cow], ...`\
`[Cowboy]:[Cow], [Cow], [Cow], ...`\
`etc.`

Where `[Cowboy]` is the cowboy's id, and each `[Cow]` is the cow's size.
##Output Description
A text file in the following format:

`[Cowboy] [Cash]`
`[Cowboy] [Cash]`
`etc.`

Where `[Cowboy]` is the cowboy's id, and `[Cash]` is the amount of cash the cowboy had at the end. The output should be ordered by the wealthiest cowboys at the top and the poorest at the bottom.
