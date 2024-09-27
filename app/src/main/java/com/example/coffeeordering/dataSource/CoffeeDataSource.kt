package com.example.coffeeordering.dataSource

class CoffeeDataSource {

    //define the coffee description map
    private val coffeeDescriptions: Map<String,String> = mapOf(
        "Caffe Mocha" to "A delicious combination of espresso, steamed milk, and chocolate.",
        "Flat White" to "Smooth microfoam milk poured over a shot of espresso.",
        "Americano Misto" to "A mix of hot water and steamed milk added to espresso.",
        "Cappuccino" to "Equal parts espresso, steamed milk, and frothed milk.",
        "Macchiato" to "An espresso with a small amount of foamed milk on top."

    )

    // Method to get the description for a coffee type
    fun getCoffeeDescription(coffeeType: String): String? {
        return coffeeDescriptions[coffeeType]
    }
}