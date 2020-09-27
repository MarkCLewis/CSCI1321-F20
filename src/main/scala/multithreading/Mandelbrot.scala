package multithreading

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView
import scalafx.scene.image.PixelWriter
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent

object Mandelbrot extends JFXApp {
  val maxCount = 1000
  val logMax = math.log(maxCount)
  val rmin = -1.5
  val rmax = 0.5
  val imin = -1.0
  val imax = 1.0

  stage = new JFXApp.PrimaryStage {
    title = "Mandelbrot"
    scene = new Scene(1000, 1000) {
      val image = new WritableImage(1000, 1000)
      content = new ImageView(image)
      val start = System.nanoTime()
      fillImage(image)
      println((System.nanoTime() - start)*1e-9)

      onMouseClicked = (me: MouseEvent) => {
        val clickReal = rmin + me.x * (rmax - rmin) / image.width()
        val clickImaginary = imin + me.y * (imax - imin) / image.height()
        new Julia(Complex(clickReal, clickImaginary)).show()
      }
    }
  }

  def fillImage(image: WritableImage): Unit = {
    val writer = image.pixelWriter
    for (x <- (0 until image.width().toInt).par; y <- 0 until image.height().toInt) {
      val c = Complex(rmin + x * (rmax-rmin) / image.width(), imin + y * (imax-imin) / image.height())
      val cnt = mandelCount(c)
      val color = countToColor(cnt)
      writer.setColor(x, y, color)
    }
  }

  def countToColor(cnt: Int): Color = {
    if (cnt < maxCount) Color(1.0 - math.log(cnt)/logMax, math.log(cnt)/logMax, 0.0, 1.0) else Color.Black
  }

  def mandelCount(c: Complex): Int = {
    var z = c
    var cnt = 0
    while (cnt < maxCount && z.r*z.r + z.i*z.i < 4) {
      z = z*z+c
      cnt += 1
    }
    cnt
  }
}