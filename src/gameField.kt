@file:Suppress("UNUSED_PARAMETER", "unused")




class GameField(k: Double) {


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


    fun interact(character: Character,enemy: Enemy) {
        val check = character.attack(enemy)
        if (check) {
            character.upgrade(enemy.lootType, enemy.lootValue)
            enemy =
        }
    }
}