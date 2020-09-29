package multithreading2

import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView
import scalafx.scene.paint.Color
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.application.Platform

class Julia(c: Complex) extends Stage {
  title = "Julia: " + c
  scene = new Scene(1000, 1000) {
    val image = new WritableImage(1000, 1000)
    content = new ImageView(image)
    fillImage(image, -1.0, 1.0, -1.0, 1.0)
  }
  
  def fillImage(image: WritableImage, rmin: Double, rmax: Double, imin: Double, imax: Double): Unit = {
    val start = System.nanoTime()
    val writer = image.pixelWriter
    val futures = for (x <- 0 until image.width().toInt) yield Future {
      for (y <- 0 until image.height().toInt) yield {
        val c = Complex(rmin + x * (rmax-rmin) / image.width(), imin + y * (imax-imin) / image.height())
        val cnt = juliaCount(c)
        (x, y, countToColor(cnt))
      }
    }
    val futures2 = for (f <- futures) yield {
      f.map(col => Platform.runLater(for ((x, y, color) <- col) writer.setColor(x, y, color)))
    }
    Future.sequence(futures2).foreach(_ => println((System.nanoTime() - start)*1e-9))
  }

  def countToColor(cnt: Int): Color = {
    if (cnt < Mandelbrot.maxCount) Color(1.0 - math.log(cnt)/Mandelbrot.logMax, math.log(cnt)/Mandelbrot.logMax, 0.0, 1.0) else Color.Black
  }

  def juliaCount(z0: Complex): Int = {
    var z = z0
    var cnt = 0
    while (cnt < Mandelbrot.maxCount && z.r*z.r + z.i*z.i < 4) {
      z = z*z+c
      cnt += 1
    }
    cnt
  }

}