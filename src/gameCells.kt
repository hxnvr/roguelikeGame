@file:Suppress("UNUSED_PARAMETER", "unused")

import javafx.scene.image.Image
import java.io.File




fun random(start: Int, finish: Int): Int {
    return (start..finish).toList().random()
}




    class Enemy(
        val image: Image,
        val name: String,
        var health: Int,
        val damage: Int,
        val lootType: Int,
        val lootValue: Int
    ) {
        fun toEmptyCell(): EmptyCell = EmptyCell()
    }

    fun createEnemy(k: Double): Enemy {
        val enemyName = File("input/enemyNames.txt").readLines().shuffled().first()
        val enemyImage = Image(File("images/enemy.png").toURI().toString())
        val enemyHealth = (random(20, 40) * k).toInt()
        val enemyDamage = (random(5, 10) * k).toInt()
        val enemyLootType = random(2, 5)
        val enemyLootValue = when (enemyLootType) {
            2 -> (random(5, 10) * k).toInt()
            3 -> (random(1, 5) * k).toInt()
            4 -> random(1, 5)
            else -> 0
        }
        return Enemy(enemyImage, enemyName, enemyHealth, enemyDamage, enemyLootType, enemyLootValue)
    }


    class Box(val image: Image,
              val name: String,
              val lootType: Int,
              val lootValue: Int) {
        fun toEmptyCell(): EmptyCell = EmptyCell()
    }

    fun createBox(k: Double): Box {
        val boxName = File("input/boxNames.txt").readLines().shuffled().first()
        val boxImage = Image(File("images/box.png").toURI().toString())
        val boxLootType = random(2, 4)
        val boxLootValue = when (boxLootType) {
            2 -> (random(5, 10) * k).toInt()
            3 -> (random(1, 5) * k).toInt()
            4 -> random(1, 5)
            else -> 0
        }
        return Box(boxImage, boxName, boxLootType, boxLootValue)
    }


    class Event(val image: Image,
                val name: String,
                val lootType: Int,
                val lootValue: Int) {
        fun toEmptyCell(): EmptyCell = EmptyCell()
    }

    fun createEvent(k: Double): Event {
        val eventInfo = File("input/eventNames.txt").readLines().shuffled().first().split("-----")
        val eventName = eventInfo[0]
        val eventImage = Image(File("images/event.png").toURI().toString())
        val eventLootType = eventInfo[1].toInt()
        val eventLootValue = when (eventLootType) {
            1 -> (random(-20, 20) * k).toInt()
            2 -> (random(5, 10) * k).toInt()
            3 -> (random(1, 5) * k).toInt()
            4 -> random(1, 5)
            else -> 0
        }
        return Event(eventImage, eventName, eventLootType, eventLootValue)
    }


    class Potion(var name: String,
                 var type: Int,
                 var value: Int)

    class Inventory {
        var potions = listOf(
            Potion("Healing potion", 1, 0), Potion("TODO()", 2, 0),
            Potion("TODO()", 3, 0), Potion("TODO()", 4, 0), Potion("TODO()", 5, 0)
        )
    }


    class Character(var image: Image,
                    var name: String,
                    var health: Int,
                    var damage: Int,
                    var armor: Int) {
        var potions = Inventory().potions
        var key = false


        private fun addPotion(type: Int) {
            this.potions.map { if (it.type == type) it.value ++ }
        }


        private fun healthEffect(effect: Int) {
            this.health += effect
            if (this.health > 40)
                this.health = 40
        }


        fun attack(enemy: Enemy): Boolean {
            enemy.health -= this.damage
            if (enemy.health > 0) {
                this.health -= enemy.damage
                return false
            }
            return true
        }


        private fun upgradeAttack(newAttack: Int): Boolean {
            return if (this.damage < newAttack) {
                this.damage = newAttack
                true
            } else false
        }


        private fun upgradeArmor(newArmor: Int): Boolean {
            return if (this.armor < newArmor) {
                this.armor = newArmor
                true
            } else false
        }


        fun upgrade(type: Int, value: Int): Boolean {
            when(type) {
                1 -> { this.healthEffect(value)
                    return true }
                2 -> return this.upgradeAttack(value)
                3 -> return this.upgradeArmor(value)
                4 -> { this.addPotion(value)
                    return true}
            }
            return false
        }
    }

    fun createCharacter(name: String): Character {
        return Character(Image(File("images/minichel.png").toURI().toString()), name, 40, 5, 0)
    }


class Exit{
    val image = Image(File("images/door.png").toURI().toString())
    var check = false
}

class Key{
    val image = Image(File("images/key.png").toURI().toString())
}

class EmptyCell{
    val image = Image(File("images/empty.png").toURI().toString())
}


/**
 * Функия - создатель набора игровых элементов для поля
 */
fun createGamingKit(k: Double): List<Any> {
    val listOfCells = mutableListOf<Any>()
    for (i in 1..18)
        listOfCells.add(createEvent(k))
    for (i in 1..6)
        listOfCells.add(createBox(k))
    for (i in 1..10)
        listOfCells.add(createEnemy(k))
    listOfCells.add(Exit())
    listOfCells.add(Key())
    return listOfCells.shuffled().toMutableList()

}