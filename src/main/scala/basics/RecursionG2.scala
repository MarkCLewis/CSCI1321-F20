package basics

object RecursionG2 extends App {

    for(i <- 0 to 10){
        println(factorial(i))
    }
    for(i <- 0 to 10){
        println(fibonacci(i))
    }

    def factorial(n: Int): Int= {
        if(n == 0) return 1
        n*factorial(n-1)
    }

    def fibonacci(n: Int): Int = {
        if(n == 0 || n == 1) return n
        fibonacci(n-1)+fibonacci(n-2)
    }
}