import javafx.scene.paint.Color
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import javafx.scene.shape.Rectangle
import javafx.scene.image.*
import javafx.scene.text.Font
import javafx.stage.Stage
import sun.misc.Signal
import java.io.File
import javax.imageio.ImageIO

@Suppress("UNREACHABLE_CODE")
class Main: Application() {
    private val stage = Stage()
    private val root = Pane()
    private val deathroot = Pane()
    private val backField = GameField(1.0)
    private val portrait = Image(File("images/minichel.png").toURI().toString())
    private val pole = Image(File("images/pole.png").toURI().toString())
    private val character = backField.character
    private val up = Button("UP")
    private val down = Button("DOWN")
    private val left = Button("LEFT")
    private val right = Button("RIGHT")
    private val charName = Label(character.name)
    private var charHp = Label(character.health.toString())
    private val charAttack = Label(character.damage.toString())
    private val matrix = backField.gameField
    private val charArmor = Label(character.armor.toString())
    private val message = Label()

    fun ss(i: Int,k: Int): Image {
        val a = matrix[i, k]
        return when (a) {
            is Enemy -> a.image
            is Event -> a.image
            is Character -> a.image
            is Box -> a.image
            is Key -> a.image
            is Exit -> a.image
            else -> pole
        }
    }
    fun sss(i: Int, k: Int): Label{
        val a = matrix[i, k]
        var b = Label()
        if(a is Enemy) b.text = "${a.damage}         ${a.health}"
        return b
    }

    fun buttons(){
        var charPos = Pair(5, 5)
        var charCoord = Pair(500.0, 500.0)
        up.setOnAction {
            if (charCoord.second > 0.0) {

            if (matrix[charPos.first , charPos.second - 1] is Enemy){
                backField.interact(character, matrix[charPos.first, charPos.second - 1 ] as Enemy)
                message.text = "Вы сразились с ${( matrix[charPos.first , charPos.second - 1] as Enemy).name}"
            }
            if (matrix[charPos.first , charPos.second - 1] is Box){
                message.text =  backField.interact(character, matrix[charPos.first , charPos.second - 1] as Box)
            }
            if (matrix[charPos.first , charPos.second - 1] is Event){
                message.text =  backField.interact(character, matrix[charPos.first , charPos.second - 1] as Event)
            }

                if(character.health <= 0) deathscene() else {
                    if (matrix[charPos.first , charPos.second - 1] is Key){
                        character.key = true
                        message.text = "Вы подобрали ключ"
                    }
                    val imgchar = ImageView(portrait)
                    val imgpole = ImageView(pole)
                    imgpole.x = charCoord.first
                    imgpole.y = charCoord.second
                    root.children.add(imgpole)
                    imgchar.x = charCoord.first
                    imgchar.y = charCoord.second - 100.0
                    root.children.add(imgchar)
                    if (matrix[charPos.first , charPos.second -1] is Exit){
                        if (character.key) nextLevelScene()
                    }
                    matrix[charPos.first, charPos.second] = EmptyCell()
                    matrix[charPos.first, charPos.second - 1] = character
                    charPos = Pair(charPos.first, charPos.second - 1)
                    charCoord = Pair(charCoord.first, charCoord.second - 100.0)
                    charHp.text = character.health.toString()
                    charArmor.text = character.armor.toString()
                    charAttack.text = character.damage.toString()


                }
        }
        }
        down.setOnAction {
            if (charCoord.second < 500.0) {

            if (matrix[charPos.first , charPos.second + 1] is Enemy){
                backField.interact(character, matrix[charPos.first , charPos.second + 1] as Enemy)
                message.text = "Вы сразились с ${( matrix[charPos.first , charPos.second + 1] as Enemy).name}"
            }
            if (matrix[charPos.first , charPos.second + 1] is Box){

                message.text =  backField.interact(character, matrix[charPos.first , charPos.second + 1] as Box)
            }
            if (matrix[charPos.first , charPos.second + 1] is Event){
                message.text =  backField.interact(character, matrix[charPos.first , charPos.second + 1] as Event)
            }
                if(character.health <= 0) deathscene() else {
                    val imgchar = ImageView(portrait)
                    val imgpole = ImageView(pole)
                    imgpole.x = charCoord.first
                    imgpole.y = charCoord.second
                    root.children.add(imgpole)
                    imgchar.x = charCoord.first
                    imgchar.y = charCoord.second + 100.0
                    root.children.add(imgchar)
                        if (matrix[charPos.first , charPos.second + 1] is Key){
                        character.key = true
                        message.text = "Вы подобрали ключ"
                    }
                    if (matrix[charPos.first , charPos.second + 1] is Exit){
                        if (character.key) nextLevelScene()
                    }
                    matrix[charPos.first, charPos.second] = EmptyCell()
                    matrix[charPos.first, charPos.second + 1] = character
                    charPos = Pair(charPos.first, charPos.second + 1)
                    charCoord = Pair(charCoord.first, charCoord.second + 100.0)
                    charHp.text = character.health.toString()
                    charArmor.text = character.armor.toString()
                    charAttack.text = character.damage.toString()
                }
        }}
        left.setOnAction {
            if (charCoord.first > 0.0){

            if (matrix[charPos.first - 1 , charPos.second] is Enemy){
                backField.interact(character, matrix[charPos.first - 1, charPos.second ] as Enemy)
                message.text = "Вы сразились с ${( matrix[charPos.first- 1 , charPos.second ] as Enemy).name}"
            }
            if (matrix[charPos.first - 1, charPos.second ] is Box){
                message.text =  backField.interact(character, matrix[charPos.first - 1 , charPos.second] as Box)
            }
            if (matrix[charPos.first - 1 , charPos.second] is Event){
                message.text =  backField.interact(character, matrix[charPos.first -1 , charPos.second] as Event)
            }
                if(character.health <= 0) deathscene() else {
                    val imgchar = ImageView(portrait)
                    val imgpole = ImageView(pole)
                    imgpole.x = charCoord.first
                    imgpole.y = charCoord.second
                    root.children.add(imgpole)
                    imgchar.x = charCoord.first - 100.0
                    imgchar.y = charCoord.second
                    root.children.add(imgchar)
                    if (matrix[charPos.first - 1 , charPos.second] is Key){
                        character.key = true
                        message.text = "Вы подобрали ключ"
                    }
                    if (matrix[charPos.first -1, charPos.second] is Exit){
                        if (character.key) nextLevelScene()
                    }
                    matrix[charPos.first, charPos.second] = EmptyCell()
                    matrix[charPos.first - 1, charPos.second] = character
                    charPos = Pair(charPos.first - 1, charPos.second)
                    charCoord = Pair(charCoord.first - 100.0, charCoord.second)
                    charHp.text = character.health.toString()
                    charArmor.text = character.armor.toString()
                    charAttack.text = character.damage.toString()

                }
        }}
        right.setOnAction {
            if (charCoord.first < 500.0){

            if (matrix[charPos.first + 1 , charPos.second] is Enemy){
                backField.interact(character, matrix[charPos.first + 1, charPos.second ] as Enemy)
                message.text = "Вы сразились с ${( matrix[charPos.first +1, charPos.second ] as Enemy).name}"
            }
            if (matrix[charPos.first + 1, charPos.second ] is Box){

                message.text =  backField.interact(character, matrix[charPos.first +1 , charPos.second] as Box)
            }
            if (matrix[charPos.first + 1 , charPos.second] is Event){
                message.text =  backField.interact(character, matrix[charPos.first +1, charPos.second] as Event)
            }
                if(character.health <= 0) deathscene() else {
                    val imgchar = ImageView(portrait)
                    val imgpole = ImageView(pole)
                    imgpole.x = charCoord.first
                    imgpole.y = charCoord.second
                    root.children.add(imgpole)
                    imgchar.x = charCoord.first + 100.0
                    imgchar.y = charCoord.second
                    root.children.add(imgchar)
                    if (matrix[charPos.first +1, charPos.second] is Key){
                        character.key = true
                        message.text = "Вы подобрали ключ"
                    }
                    if (matrix[charPos.first + 1 , charPos.second] is Exit){
                        if (character.key) nextLevelScene()
                    }
                    matrix[charPos.first, charPos.second] = EmptyCell()
                    matrix[charPos.first + 1, charPos.second] = character
                    charPos = Pair(charPos.first + 1, charPos.second)
                    charCoord = Pair(charCoord.first + 100.0, charCoord.second)
                    charHp.text = character.health.toString()
                    charArmor.text = character.armor.toString()
                    charAttack.text = character.damage.toString()
                }
        }}

    }



    fun buttonsPosition(){
        up.layoutX = 1000.0
        up.layoutY = 400.0
        down.layoutX = 1000.0
        down.layoutY = 500.0
        left.layoutY = 500.0
        left.layoutX = 900.0
        right.layoutX = 1100.0
        right.layoutY = 500.0
        up.setPrefSize(100.0,100.0)
        down.setPrefSize(100.0,100.0)
        left.setPrefSize(100.0,100.0)
        right.setPrefSize(100.0,100.0)
        root.children.add(up)
        root.children.add(down)
        root.children.add(left)
        root.children.add(right)
    }

    fun draw(){
        var x: Double
        var y: Double
        for (i in 0..5)
            for (k in 0..5){
                val imgv = ImageView(ss(i,k))
                val label = sss(i,k)
                x = 100.0 * i
                y = 100.0 * k
                imgv.x = x
                imgv.y = y
                label.layoutX = x + 12
                label.layoutY = y + 3
                label.font = Font.font(17.0)
                root.children.add(imgv)
                root.children.add(label)

            }
    }
    private fun draw2(){
        charName.layoutX = 1110.0
        root.children.add(charName)
        charHp.layoutX =1110.0
        charHp.layoutY = 20.0
        charArmor.layoutX =1110.0
        charArmor.layoutY = 60.0
        charAttack.layoutX =1110.0
        charAttack.layoutY = 40.0
        charHp.font = Font.font("Arial", 20.0)
        root.children.add(charHp)
        root.children.add(charArmor)
        root.children.add(charAttack)
        val imgv = ImageView(portrait)
        imgv.x = 1000.0
        imgv.y = 0.0
        imgv.prefHeight(150.0)
        root.children.add(imgv)
        message.layoutX = 700.0
        message.layoutY = 300.0
        root.children.add(message)
    }
    private fun deathscene(): Pane{
        root.children.clear()
        message.layoutX = 550.0
        message.layoutY = 300.0
        message.text = "ВЫ ПРОИГРАЛИ"
        root.children.add(message)
        return root
    }
    private fun nextLevelScene(): Pane{
        root.children.clear()
        message.layoutX = 550.0
        message.layoutY = 300.0
        message.text = "TODO()"
        root.children.add(message)
        return root
    }

    private fun createContent(): Pane {
        root.setPrefSize(1200.0, 600.0)
        buttonsPosition()
        draw()
        draw2()
        buttons()
        return root
    }


    override fun start(stage: Stage) {
        stage.scene = Scene(createContent())
        stage.show()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }

}