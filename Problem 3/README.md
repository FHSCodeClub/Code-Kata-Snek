#Maze Solver
##General Description
Given a list of coordinates that can be walked on in a rectangular maze with a given start and end point, return a list of moves (up, down, left, right) that takes you to the end point.
##Input Description
A text file in the following format:

`[tile][tile] ... [tile]`\
`[tile][tile] ... [tile]`\
`.                  .`\
`.                  .`\
`.                  .`\
`[tile][tile] ... [tile]`

where each `[tile]` is either a 0 or a 1, representing whether or not the tile is traversable. A 1 can be replaced by either an `s` or an `f`.

For instance:
`f1100000`\
`00100001`\
`00100011`\
`0s111110`
##Output Description
A sequence of lefts and rights that represent the steps required to go from `s` to `f`.

For instance, for the above, the output should be any variant of
`right`\
`up`\
`up`\
`up`\
`left`\
`left`

Any working solution to a maze will be accepted.
