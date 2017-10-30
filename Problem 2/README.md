#Bacteria!
##General Description
Given a surface area, a starting population, a food supply, food supply growth, and rate of lifespan growth per turn, calculate how many bacteria will exist on the 100th turn. Each bacterium takes up 1 space. If a bacterium doesn\'t have food or space or it has lived its lifespan, it dies. Otherwise, it creates a new offspring with the new lifespan. The lifespan of any new bacterium changes by the given rate of lifespan growth every turn. The food supply grows by the given rate of food supply growth every turn.
##Input Description
A text file in the following format:

`[SurfaceArea] [StartingPopulation] [AmountOfFood] [RateOfGrowthForFood] [RateOfGrowthForPopulation]`\
`[SurfaceArea] [StartingPopulation] [AmountOfFood] [RateOfGrowthForFood] [RateOfGrowthForPopulation]`\
`etc.`

##Output Description
A text file in the following format:

`[EndingPopulation]`\
`[EndingPopulation]`\
`etc.`