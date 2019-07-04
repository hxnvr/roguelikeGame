import javafx.scene.paint.Color
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.Pane
import javafx.scene.shape.Rectangle
import javafx.scene.image.*
import javafx.stage.Stage
import sun.misc.Signal
import java.io.File
import javax.imageio.ImageIO

class Main: Application() {

    private val root = Pane()

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

    fun ss(i: Int,k: Int): Image {
        val a = backField.gameField[i, k]
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
    fun buttons(){
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
        up.setOnAction {
            character.health -= 20
            charHp.text = character.health.toString()
        }
    }
    fun draw(){
        var x = 500.0
        var y = 400.0
        for (i in 0..5)
            for (k in 0..5){
                val imgv = ImageView(ss(i,k))
                imgv.x = x
                imgv.y = y
                if (i == 5 && k == 5) {
                    imgv.x = 500.0
                    imgv.y = 500.0
                }
                root.children.add(imgv)
                x = 100.0 * i
                y = 100.0 * k
            }
    }
    private fun draw2(){
        charName.layoutX = 1110.0
        root.children.add(charName)
        charHp.layoutX =1110.0
        charHp.layoutY = 20.0
        charAttack.layoutX =1110.0
        charAttack.layoutY = 40.0
        root.children.add(charHp)
        root.children.add(charAttack)
        val imgv = ImageView(portrait)
        imgv.x = 1000.0
        imgv.y = 0.0
        root.children.add(imgv)
    }

    private fun createContent(): Pane {
        root.setPrefSize(1200.0, 600.0)
        buttons()
        draw()
        draw2()
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