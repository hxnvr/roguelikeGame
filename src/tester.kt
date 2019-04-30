

fun main(args: Array<String>){
    val field = GameField(1.0)
    val enemy = createEnemy(1.0)
    var check = false
    while (!check)
    check = field.character.interact(enemy)
}









fun main2(args: Array<String>){
    var message = ""
    for(i in 0 until 10) {
        val test1 = createEvent(1.0)
        when(test1.lootType){
            1 -> message = "Здоровье"
            2 -> message = "Оружие"
            3 -> message = "Броня"
            4 -> message = "Зелье"
            5 -> message = "Ничего"
        }
        println(test1.name)
        println(message + " " + test1.lootValue)
        println("-------------------------------")
    }
    for(i in 0 until 10) {
        val test2 = createEnemy(1.0)
        when(test2.lootType){
            1 -> message = "Здоровье"
            2 -> message = "Оружие"
            3 -> message = "Броня"
            4 -> message = "Зелье"
            5 -> message = "Ничего"
        }
        println(test2.name + " " + "HP: " + test2.health + " " + "DMG: " + test2.damage)
        println(message + " " + test2.lootValue)
        println("-------------------------------")
    }
    for(i in 0 until 10) {
        val test3 = createBox(1.0)
        when(test3.lootType){
            1 -> message = "Здоровье"
            2 -> message = "Оружие"
            3 -> message = "Броня"
            4 -> message = "Зелье"
            5 -> message = "Ничего"
        }
        println(test3.name)
        println(message + " " + test3.lootValue)
        println("-------------------------------")
    }
}