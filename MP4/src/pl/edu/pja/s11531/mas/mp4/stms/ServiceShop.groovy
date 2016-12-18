package pl.edu.pja.s11531.mas.mp4.stms

import pl.edu.pja.s11531.mas.mp4.LinkedObject

import java.time.LocalDate

/**
 * Created by kris on 12/18/16.
 */
class ServiceShop extends LinkedObject {
    final Queue<Serviceable> unitsToService = new LinkedList<>()

    void addUnit(Serviceable unit) {
        unitsToService.add unit
    }

    Serviceable serviceUnit() {
        if (unitsToService.empty) {
            return null
        }
        Serviceable unit = unitsToService.poll()
        unit.lastServiceDate = LocalDate.now()
        return unit
    }

    int getQueueSize() {
        return unitsToService.size()
    }
}
