package multithreading2

import scalafx.application.JFXApp
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView
import scalafx.scene.Scene
import scalafx.scene.paint.Color

object Mandelbrot extends JFXApp {
  val maxCount = 1000

  stage = new JFXApp.PrimaryStage {
    title = "Mandelbrot"
    scene = new Scene(1000, 1000) {
      val image = new WritableImage(1000, 1000)
      content = new ImageView(image)
      val start = System.nanoTime()
      drawMandel(image, -1.5, 0.5, -1.0, 1.0)
      println((System.nanoTime()-start)/1e9)
    }
  }

  def drawMandel(image: WritableImage, rmin: Double, rmax: Double, imin: Double, imax: Double): Unit = {
    val writer = image.pixelWriter
    for (x <- (0 until image.width().toInt).par; y <- 0 until image.height().toInt) {
      val c = Complex(rmin + x*(rmax-rmin)/image.width(), imin + y*(imax-imin)/image.height())
      val cnt = mandelCount(c)
      val color = if (cnt == maxCount) Color.Magenta else Color(0,math.sqrt(cnt.toDouble/maxCount),math.sqrt(cnt.toDouble/maxCount), 1.0)
      writer.setColor(x, y, color)
    }
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