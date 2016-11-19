package pl.edu.pja.s11531.mas.mp1

def ammoShip = new CargoShip("Ammo ship", 10000.0, 100)

// atrybut powtarzalny / atrybut złożony
ammoShip.cargo << new Cargo("Guns", 1000.0)
ammoShip.cargo << new Cargo("Ammo", 500.0)
ammoShip.cargo << new Cargo("Space rum", 1500.0)
assert ammoShip.cargo.size() == 3

// metoda / przesłonięcie
println ammoShip.toString()

def flagShip = new SpaceShip("Flag ship", 5000.0, 50, "flag")

def fighter = new LightFighter("Fighter", 100)

// atrybut klasowy
assert fighter.crewCount == LightFighter.CREW_COUNT

// atrybut pochodny
assert ammoShip.mass == 13010.0 // przeciążenie
assert flagShip.mass == 5005.0
assert fighter.mass == 100.2

// atrybut klasowy / pochodny / metoda klasowa
assert SpaceShip.totalFleetCrew == 152

BaseObject.getExtent(SpaceShip.class).each {
    print "Space ship '$it.name' of type ${it.class.simpleName}, " +
            "mass = $it.mass t, " +
            "crew = $it.crewCount people"
    // atrybut opcjonalny
    if ( it.function ) print ", function = $it.function"
    print "\n"
}

// ekstensja
assert BaseObject.getExtent(SpaceShip.class).size() == 3
assert BaseObject.getExtent(CargoShip.class).size() == 1
assert BaseObject.getExtent(LightFighter.class).size() == 1

// trwałość ekstensji
def store = new File('spaceships.bin')
BaseObject.saveAll store

// metoda klasowa
BaseObject.clearExtent()

assert BaseObject.getExtent(SpaceShip.class).size() == 0
assert BaseObject.getExtent(CargoShip.class).size() == 0
assert BaseObject.getExtent(LightFighter.class).size() == 0

// trwałość ekstensji
BaseObject.loadAll store

assert BaseObject.getExtent(SpaceShip.class).size() == 3
assert BaseObject.getExtent(CargoShip.class).size() == 1
assert BaseObject.getExtent(LightFighter.class).size() == 1

// metoda klasowa / przeciążenie
BaseObject.clearExtent LightFighter.class

assert BaseObject.getExtent(SpaceShip.class).size() == 2
assert BaseObject.getExtent(CargoShip.class).size() == 1
assert BaseObject.getExtent(LightFighter.class).size() == 0