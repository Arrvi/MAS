package pl.edu.pja.s11531.mas.mp4.stms

import pl.edu.pja.s11531.mas.mp4.ConstraintViolationException

import java.time.LocalDate

/**
 * Created by kris on 12/18/16.
 */
trait Serviceable {
    LocalDate lastServiceDate

    void setLastServiceDate(LocalDate date) {
        if ( lastServiceDate?.isAfter(date) ) {
            throw new ConstraintViolationException("Cannot set older service date")
        }
        if ( date.isAfter(LocalDate.now())) {
            throw new ConstraintViolationException("Cannot set future date")
        }
        lastServiceDate = date
    }
}