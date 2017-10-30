#Class Maker
##General Description
Sort students into requested Core. There are 8 periods in a day, during which no more than one instance of each class may be in session. There are a maximum of 30 students in each class.
##Input Description
A text file in the following format:

`[ID]:[Class], [Class], [Class], ...`\
`[ID]:[Class], [Class], [Class], ...`\
`etc.`

Where `[ID]` is a 6-digit integer, and each `[Class]` is represented by a 3-digit integer. 
##Output Description
A text file in the following format:

`[Class], [Period]:[ID], [ID], [ID], ...`\
`[Class], [Period]:[ID], [ID], [ID], ...`\
`etc.`

Where `[Class]` is a 3-digit integer, `[Period]` is an integer between 0 and 7, inclusive, with 0 representing the first period of the day, and `[ID]` is a 6-digit integer.
Each line should have no more than 30 student IDs, each student ID should correspond to a student who requested that class in the input file, and each combination of `[Class]` and `[Period]` should appear no more than once.
