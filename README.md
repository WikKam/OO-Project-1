# OOP-Project-1
My project on Object Oriented Programming. It is a simple animal evolution simulator. 

##Mechanics
*Map consists of two kinds of lands: Jungle (int the middle) and Savannah(outside)
*Whenever age passes, animal makes a random move to neighbour position (8 posibilities, based on animal's genome)
*Moving costs energy. The brighter animal is, the lower its energy.
*After each age one field of grass appears in the jungle and savannah
*Animals eat grass when they step on a field with grass. There can be multiple animals on one field, so the strongest(highest energy) eats grass.
*Animals can breed, but only when their energy is high enough. There are no genders, so two strongest animals on one field breed, and baby appears on the same field with a friction of energy from its parents.
*Baby inherits genes from both of its parents
*When energy of animal reaches 0, it dies
##What you can do with this app
*gui displays current age, avarage amount of children, avarage amount of energy, avarage age of dead animals, amount of grasses and dominating gene
* when simulation is paused you can click on an animal, and see its genes, amount of children and amount of ancestors
* after restarting, you can track its movement
* when simulation is paused you can highlight all animals with dominating gene and save statistics to txt file
## Some screens
*Menu
![image of a menu](https://ibb.co/gPTqJwB)
*Running simulation
![image of a simulation](https://ibb.co/nBt536r)
*Starting parameters of this application
![image of parameters](https://ibb.co/LPd596d)



