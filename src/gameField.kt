@file:Suppress("UNUSED_PARAMETER", "unused")




class GameField(k: Double) {

    private val listOfLootTypes = mapOf(1 to "Здоровье", 2 to "Оружие", 3 to "Броня", 4 to "Зелье", 5 to "Ничего")
    private val listOfPotionTypes = mapOf(1 to "Лечения")
    private val listOfCells = createGamingKit(k)
    var gameField = createMatrix(6, 6, Any())
    var character = createCharacter("", "")


    /**
     * Создание игрового поля из элементов, созданных функцией createGamingKit()
     */
    init {
        println("Enter character's name: ")
        val name = readLine().toString()
        character.name = name
        var count = 0
        for (i in 0..5)
            for (n in 0..5) {
                gameField[i, n] = listOfCells[count]
                count++
            }
        gameField[5, 5] = character
    }


    fun interact(character: Character,enemy: Enemy): Boolean {
        val check = character.attack(enemy)
        if (check) {
            character.upgrade(enemy.lootType, enemy.lootValue)
            enemy.toEmptyCell()
        }
        return check
    }

    fun interact(character: Character, box: Box): String {
        val check = character.upgrade(box.lootType, box.lootValue)
        var message = ""
        message = if (check) {
            if (box.lootType != 4)
                "Вы нашли в ${box.name} ${listOfLootTypes[box.lootType]} - ${box.lootValue}"
            else
                "Вы нашли в ${box.name} ${listOfLootTypes[box.lootType]} - ${listOfPotionTypes[box.lootValue]}"
        } else
            "Вы не нашли ничего интересного"
        box.toEmptyCell()
        return message
    }

    fun interact(character: Character,event: Event): String {
        val check = character.upgrade(event.lootType, event.lootValue)
        var message: String
        message = if (check) {
            if (event.lootType != 4)
                "${event.name} и вы нашли ${listOfLootTypes[event.lootType]} ${event.lootValue}"
            else
                "${event.name} и вы получили ${listOfLootTypes[event.lootType]} ${listOfPotionTypes[event.lootValue]}"
        } else
            "${event.name}, да и все собственно"
        event.toEmptyCell()
        return message
    }
}