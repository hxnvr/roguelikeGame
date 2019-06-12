import javafx.scene.paint.Color
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.Rectangle
import javafx.scene.image.*
import javafx.stage.Stage
import sun.misc.Signal
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO

class Main: Application() {

    val gField = mutableListOf<ImageView>()

    private val img = Image(File("images/key.png").toURI().toString())

    private val imgv = ImageView(img)

    val backField = GameField(1.0)

    val a = backField.gameField[2, 3]



    val test = createEnemy(1.0).image


    private fun createContent(): Pane {
        imgv.x = 50.0
        imgv.y = 50.0


        val root = Pane()
        root.setPrefSize(100.0, 100.0)

        root.children.add(imgv)

        root.children.add(ImageView(test))


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