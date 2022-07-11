package edu.dronicbest.helloworld

import java.lang.IllegalArgumentException

class Triangle(private val sideA: Int, private val sideB: Int, private val sideC: Int) {

    init {
        if ((sideA <= 0 ) || (sideB <= 0 ) || (sideC <= 0 )) {
            throw IllegalArgumentException("Triangle sides cannot be non-positive")
        }
        if ((sideA + sideB <= sideC) || (sideB + sideC <= sideA ) || (sideC + sideA <= sideB )) {
            throw IllegalArgumentException("Triangle sides cannot be non-positive")
        }
    }

    fun isRightTriangle(): Boolean {
        return sideA.square() + sideB.square() == sideC.square() ||
                sideB.square() + sideC.square() == sideA.square() ||
                sideC.square() + sideA.square() == sideB.square()
    }
}

private fun Int.square(): Int {
    return this * this
}