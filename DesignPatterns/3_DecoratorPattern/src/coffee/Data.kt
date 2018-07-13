package coffee

// immutable map with prices
// im again using the Strategy pattern = separating stuff that changes
val itemPrices = mapOf(
		Item("Espresso", BeverageSize.SMALL) to 0.50,
		Item("Espresso", BeverageSize.BIG) to 0.70,
		Item("Espresso", BeverageSize.GRAND) to 1.30,
		
		Item("HouseBlend", BeverageSize.SMALL) to 1.00,
		Item("HouseBlend", BeverageSize.BIG) to 1.30,
		Item("HouseBlend", BeverageSize.GRAND) to 2.00,
		
		Item("Mocha", BeverageSize.SMALL) to 0.20,
		Item("Mocha", BeverageSize.BIG) to 0.30,
		Item("Mocha", BeverageSize.GRAND) to 0.50,
		
		Item("Whip", BeverageSize.SMALL) to 0.10,
		Item("Whip", BeverageSize.BIG) to 0.20,
		Item("Whip", BeverageSize.GRAND) to 0.30
)