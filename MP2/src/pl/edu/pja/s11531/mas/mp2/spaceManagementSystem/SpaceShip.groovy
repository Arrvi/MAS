package pl.edu.pja.s11531.mas.mp2.spaceManagementSystem

import pl.edu.pja.s11531.mas.mp2.associations.Association
import pl.edu.pja.s11531.mas.mp2.associations.Component

 class SpaceShip extends Component<Company> {
    public static final BigDecimal CREW_MASS = 0.1
    String name
    BigDecimal mass
    int crewCount
    String function

    SpaceShip(Company company, String name, BigDecimal mass, int crewCount, String function = null) {
        super(company)
        this.name = name
        this.mass = mass
        this.crewCount = crewCount
        this.function = function
    }

    public BigDecimal getMass() {
        return mass + crewCount * CREW_MASS
    }

    public static int getTotalFleetCrew() {
        return getExtent(SpaceShip.class).sum{it.crewCount}
    }

    @Override
    String toString() {
        return "SpaceShip name=$name, mass=$mass t, crew=$crewCount people" + (function ? ", function=$function" : "")
    }

    @Override
    def getMaxMultiplicity(Class<Fleet> cls) {
        return null
    }

     @Override
     def getMaxMultiplicity(Class cls) {
         return null
     }
 }
