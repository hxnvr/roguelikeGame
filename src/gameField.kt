@file:Suppress("UNUSED_PARAMETER", "unused")

import javafx.scene.image.Image


class GameField(k: Double) {

    private val listOfLootTypes = mapOf(1 to "Здоровье", 2 to "Оружие", 3 to "Броня", 4 to "Зелье", 5 to "Ничего")
    private val listOfPotionTypes = mapOf(1 to "Лечения")
    private val listOfCells = createGamingKit(k)
    var gameField = createMatrix(6, 6, Any())
    var character = createCharacter("")


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


    fun Enemy.getClass(): Enemy {
        return this
    }



    fun interact(character: Character,enemy: Enemy): Boolean {
        val check = character.attack(enemy)
        if (check) {
            character.upgrade(enemy.lootType, enemy.lootValue)
        }
        return check
    }

    fun interact(character: Character, box: Box): String {
        val check = character.upgrade(box.lootType, box.lootValue)
        val message: String
        message = if (check) {
            if (box.lootType != 4)
                "Вы нашли в ${box.name} ${listOfLootTypes[box.lootType]}  ${box.lootValue}"
            else
                "Вы нашли в ${box.name} ${listOfLootTypes[box.lootType]}  ${listOfPotionTypes[box.lootValue]}"
        } else
            "Вы не нашли ничего интересного"
        return message
    }

    fun interact(character: Character,event: Event): String {
        val check = character.upgrade(event.lootType, event.lootValue)
        val message: String
        message = if (check) {
            if (event.lootType != 4)
                "${event.name}, и вы получили ${listOfLootTypes[event.lootType]} ${event.lootValue}"
            else
                "${event.name}, и вы нашли ${listOfLootTypes[event.lootType]} ${listOfPotionTypes[event.lootValue]}"
        } else
            "${event.name}, да и все собственно"
        return message
    }
}