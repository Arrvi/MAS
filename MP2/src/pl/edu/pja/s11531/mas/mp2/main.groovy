package pl.edu.pja.s11531.mas.mp2

import pl.edu.pja.s11531.mas.mp2.stms.*

import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.temporal.TemporalAmount

import static pl.edu.pja.s11531.util.AssertionUtil.*

Company cargoCompany = new Company('Cargo company')
Company genericCompany = new Company('Xinh Zheng Premium Ships')

SpaceShip smallShip = new SpaceShip(genericCompany, 'Small ship', 1000)
SpaceShip mediumShip = new SpaceShip(genericCompany, 'Medium ship', 5000)
SpaceShip largeShip = new SpaceShip(cargoCompany, 'Large ship', 25000)
CargoShip massiveShip = new CargoShip(cargoCompany, 'Massive cargo ship', 100000)
CargoShip cargoShip = new CargoShip(cargoCompany, 'Smaller, but significant cargo ship', 30000)

// kompozycja
assert cargoCompany.spaceShips.size() == 3
assert genericCompany.spaceShips.size() == 2
assertException AssociationViolationException.class, {genericCompany.addSpaceShip(massiveShip)}

cargoShip.addCargo 'ammo', new Cargo('Ammo', 10)
cargoShip.addCargo 'fridge1', new Cargo('Food', 5)
cargoShip.addCargo 'fridge2', new Cargo('Water', 100)
cargoShip.addCargo 'cargo1', new Cargo('Art', 1000)

massiveShip.addCargo 'vault1', new Cargo('Gold', 10000)
massiveShip.addCargo 'vault2', new Cargo('Silver', 5000)
massiveShip.addCargo 'vault3', new Cargo('Gold', 5000)
massiveShip.addCargo 'vault4', new Cargo('Shiny thing', 1)
def containers = new Cargo('Containers', 10000)
massiveShip.addCargo 'bay1', containers
massiveShip.addCargo 'bay2', new Cargo('More containers', 20000)

// kwalifikowana
assert cargoShip.storage.size() == 4
assert massiveShip.storage.size() == 6
assertException AssociationViolationException.class, {massiveShip.addCargo 'vault1', new Cargo('More gold', 1000)}
assert massiveShip.takeCargo('bay1') == containers
assert massiveShip.storage.size() == 5

Crew captain = new Crew('cpt. Davy Jones')
Crew firstNavigator = new Crew('Koleniko')
Crew secondNavigator = new Crew('Greenbeard')
Crew member1 = new Crew('Maccus')
Crew member2 = new Crew('Ratlin')
Crew member3 = new Crew('Penrod')
Crew member4 = new Crew('Angler')

LocalDate yearAgo = LocalDate.now() - Period.ofYears(1)
LocalDate monthAgo = LocalDate.now() - Period.ofMonths(1)
smallShip.addCrew secondNavigator, yearAgo
mediumShip.addCrew captain, yearAgo
largeShip.addCrew firstNavigator, yearAgo
largeShip.addCrew member1, monthAgo
massiveShip.addCrew member2, yearAgo
cargoShip.addCrew member3, monthAgo

// z atrybutem
assert smallShip.crew.size() == 1
assert mediumShip.crew.size() == 1
assert largeShip.crew.size() == 2
assert massiveShip.crew.size() == 1
assert cargoShip.crew.size() == 1

assert captain.spaceShip == mediumShip
assert firstNavigator.spaceShip == largeShip
assert secondNavigator.spaceShip == smallShip
assert member4.spaceShip == null

assert captain.currentService?.started == yearAgo
assert member1.currentService?.started == monthAgo


Fleet pirates = new Fleet("Pirates fleet")
Fleet chineseFleet = new Fleet("Xinh Zheng fleet")

pirates << smallShip << largeShip
chineseFleet << cargoShip

assert smallShip.fleet == pirates
assert mediumShip.fleet == null
assert largeShip.fleet == pirates
assert cargoShip.fleet == chineseFleet
assert pirates.spaceShips.size() == 2
assert chineseFleet.spaceShips.size() == 1

massiveShip << captain << firstNavigator << member4

assert smallShip.crew.size() == 1
assert mediumShip.crew.size() == 0
assert largeShip.crew.size() == 1
assert massiveShip.crew.size() == 4
assert cargoShip.crew.size() == 1

pirates << massiveShip << cargoShip

assert massiveShip.fleet == pirates
assert cargoShip.fleet == pirates
assert pirates.spaceShips.size() == 4
assert chineseFleet.spaceShips.size() == 0