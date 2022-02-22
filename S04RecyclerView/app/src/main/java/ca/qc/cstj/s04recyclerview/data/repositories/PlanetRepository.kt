package ca.qc.cstj.s04recyclerview.data.repositories

import ca.qc.cstj.s04recyclerview.domain.models.Planet
import kotlin.random.Random

class PlanetRepository {

    fun retrievePlanets(): List<Planet> {

        val numberToGenerate = Random.nextInt(10,31)
        val planets = mutableListOf<Planet>()

        for(i in 0..numberToGenerate) {
            planets.add(newPlanet())
        }

        return planets

    }

    private fun newPlanet() : Planet {
        val planetNumber =  Random.nextInt(1, 50)
        val planetImage = Random.nextInt(1, 7)
        val temperature = Random.nextDouble(0.0,60.0)
        return Planet("planet$planetNumber", planetImage.toString(), temperature)
    }

}