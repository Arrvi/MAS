package pl.edu.pja.s11531.mas.mp2

def ammoShip = new pl.edu.pja.s11531.mas.mp1.CargoShip("Ammo ship", 10000.0, 100)

// atrybut powtarzalny / atrybut złożony
ammoShip.cargo << new pl.edu.pja.s11531.mas.mp1.Cargo("Guns", 1000.0)
ammoShip.cargo << new pl.edu.pja.s11531.mas.mp1.Cargo("Ammo", 500.0)
ammoShip.cargo << new pl.edu.pja.s11531.mas.mp1.Cargo("Space rum", 1500.0)
assert ammoShip.cargo.size() == 3

// metoda / przesłonięcie
println ammoShip.toString()

def flagShip = new pl.edu.pja.s11531.mas.mp1.SpaceShip("Flag ship", 5000.0, 50, "flag")

def fighter = new pl.edu.pja.s11531.mas.mp1.LightFighter("Fighter", 100)

// atrybut klasowy
assert fighter.crewCount == pl.edu.pja.s11531.mas.mp1.LightFighter.CREW_COUNT

// atrybut pochodny
assert ammoShip.mass == 13010.0 // przeciążenie
assert flagShip.mass == 5005.0
assert fighter.mass == 100.2

// atrybut klasowy / pochodny / metoda klasowa
assert pl.edu.pja.s11531.mas.mp1.SpaceShip.totalFleetCrew == 152

pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.SpaceShip.class).each {
    print "Space ship '$it.name' of type ${it.class.simpleName}, " +
            "mass = $it.mass t, " +
            "crew = $it.crewCount people"
    // atrybut opcjonalny
    if ( it.function ) print ", function = $it.function"
    print "\n"
}

// ekstensja
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.SpaceShip.class).size() == 3
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.CargoShip.class).size() == 1
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.LightFighter.class).size() == 1

// trwałość ekstensji
def store = new File('spaceships.bin')
pl.edu.pja.s11531.mas.mp1.BaseObject.saveAll store

// metoda klasowa
pl.edu.pja.s11531.mas.mp1.BaseObject.clearExtent()

assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.SpaceShip.class).size() == 0
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.CargoShip.class).size() == 0
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.LightFighter.class).size() == 0

// trwałość ekstensji
pl.edu.pja.s11531.mas.mp1.BaseObject.loadAll store

assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.SpaceShip.class).size() == 3
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.CargoShip.class).size() == 1
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.LightFighter.class).size() == 1

// metoda klasowa / przeciążenie
pl.edu.pja.s11531.mas.mp1.BaseObject.clearExtent pl.edu.pja.s11531.mas.mp1.LightFighter.class

assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.SpaceShip.class).size() == 2
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.CargoShip.class).size() == 1
assert pl.edu.pja.s11531.mas.mp1.BaseObject.getExtent(pl.edu.pja.s11531.mas.mp1.LightFighter.class).size() == 0