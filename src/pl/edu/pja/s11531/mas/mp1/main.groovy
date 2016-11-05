import pl.edu.pja.s11531.mas.mp1.BaseObject
import pl.edu.pja.s11531.mas.mp1.Cargo
import pl.edu.pja.s11531.mas.mp1.CargoShip
import pl.edu.pja.s11531.mas.mp1.LightFighter
import pl.edu.pja.s11531.mas.mp1.SpaceShip

def ammoShip = new CargoShip("Ammo ship", 10000.0, 100)
ammoShip.cargo << new Cargo("Guns", 1000.0)
ammoShip.cargo << new Cargo("Ammo", 500.0)
ammoShip.cargo << new Cargo("Space rum", 1500.0)

def flagShip = new SpaceShip("Flag ship", 5000.0, 50)

def fighter = new LightFighter("Fighter", 100)

assert ammoShip.mass == 13010.0
assert flagShip.mass == 5005.0
assert fighter.mass == 100.2
assert SpaceShip.maximumFleetCrew == 152

def store = new File('spaceships.bin')

assert BaseObject.getExtent(SpaceShip.class).size() == 3
assert BaseObject.getExtent(CargoShip.class).size() == 1
assert BaseObject.getExtent(LightFighter.class).size() == 1

BaseObject.saveAll store
BaseObject.clearExtent()

assert BaseObject.getExtent(SpaceShip.class).size() == 0
assert BaseObject.getExtent(CargoShip.class).size() == 0
assert BaseObject.getExtent(LightFighter.class).size() == 0

BaseObject.loadAll store

assert BaseObject.getExtent(SpaceShip.class).size() == 3
assert BaseObject.getExtent(CargoShip.class).size() == 1
assert BaseObject.getExtent(LightFighter.class).size() == 1

BaseObject.clearExtent LightFighter.class

assert BaseObject.getExtent(SpaceShip.class).size() == 2
assert BaseObject.getExtent(CargoShip.class).size() == 1
assert BaseObject.getExtent(LightFighter.class).size() == 0