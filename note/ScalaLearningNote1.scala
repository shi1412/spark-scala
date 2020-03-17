import scala.io.StdIn.{readLine, readInt}
import scala.math._
import scala.collection.mutable.ArrayBuffer
import java.io.PrintWriter
import scala.io.Source

// object ScalaLearnLoop {
//     def main(args: Array[String]) {
//         var i = 0
//         var randLetters = "ABCDEFGHIJKOLMOP"
//         var aList = List(1,2,3,4,5)
//         var evenList = for { i <- 1 to 20 if(i%2)==0} yield i
//         for( i <- 1 to 10) {
//             println(i)
//         }
//         for( i <- 0 until randLetters.length) {
//             println(randLetters(i))
//         }
//         for (i <- aList) {
//             println(i)
//         }
//         for (i <- evenList) {
//             println(i)
//         }
//         for (i <- 1 to 5; j <- 6 to 10) {
//             println(i , j)
//         }
//     }
// }

// object ScalaLearnInput {
//     def main(args: Array[String]) {
//         var numberGuess = 0
//         do {
//             print("Guess a number")
//             numberGuess = readLine.toInt
//         } while(numberGuess != 15) 

//         printf("You guessed the secret number %d\n", 15)
//     }
// }

// object ScalaLearnOutput {
//    def main(args: Array[String]) {
//        val name = "Kai Xin"
//        val age = 23
//        val weight = 123.5
//        // different in print, println and printf: print add no new lines, println: add new line, printf: add format
//        println (s"Hello")
//        println (f"I am ${age} and weight $weight%.2f")
//        printf("'%5d'\n", 5)
//        printf("'%-5d'\n",5)
//        printf("'%05d'\n",5)
//    }
// }

// object ScalaLearnString {
//    def main(args: Array[String]) {
//        var randSet = "I saw a dragon fly by"
//        val randSentArray = randSet.toArray
//        println("3rd index :" + randSet(3))
//        println("String length" + randSet.length)
//        println(randSet.concat(" and explodes"))
//        println("Are strings are equal" + "I saw a dragon".equal(randSet))
//        println("dragon starts ar index" + randSet.indexOf("dragon"))
//    }
// }

// object ScalaLearnFunc {
//     def main(args: Array[String]) {
//         // def funcName (param1: dataType, param2: dataType): returnValue = {
//         //     function body
//         //     return value
//         // }
//         def getSum(num1: Int =1, num2: Int =1): Int = {
//             return num1 + num2
//         }

//         def sayHi(): Unit = {
//             println("Hello World")
//         }

//         def getSum2(args: Int*): Int = {
//             var sum : Int = 0
//             for (num <- args) {
//                 sum += num
//             }
//             // In scala, you dont need to mention the return keywords
//             sum
//         }
//         //println("Get sum" + getSum(1,2,3,4,5))

//        def factorial(num: BigInt): BigInt = {
//            if(num <= 1) 
//               1
//            else
//               num*factorial(num-1)
//        }
//     }
// } 

// object ScalaLearnArray {
//     def main(args: Array[String]) {
//      // Array and Array Buffer
//       val favNums = new Array[Int](20)
//       val favNums2 = ArrayBuffer[String]()
//       val test1 = List(2)
//       val test2 = 1 :: test1

//       for(i <- test2 ) {
//           println(i)
//       }

//       for(j <- 0 to 5) {
//         favNums(j) = j
//       }

//       // Some operations of the array in scala
//       println("Sum: " + favNums.sum)
//       println("Min: " + favNums.min)
//       println("Max: " + favNums.max)
//       
//       // you can sort the string by simply using the _>_ sign
//       val sortedNums = favNums.sortWith(_>_)
//       println(sortedNums.deep.mkString(", "))

//       // Several ways of inserting elems into array
//       favNums2.insert(0, "Phil(1)")
//       favNums2 += "Mark(2)"
//       favNums2 ++= Array("Susy(3)", "Paul(4)" )
//       for(i <- favNums2) {
//           println(i)
//       }
//       val favNumsTimes2 = for(num <- favNums) yield 2 * num
//       favNumsTimes2.foreach(println)
//       val favNumsDiv4  = for(num <- favNums if num %4 == 0 ) yield num
//       favNumsDiv4.foreach(println)

//       // Multi-dimensional array
//       val multiTable = Array.ofDim[Int](10, 10)
//       for(i <- 0 to 9) {
//           for(j <- 0 to 9) {
//               multiTable(i)(j) = i*j
//           }
//       }

//     }
// }


// // Map is a collection type just like array and array buffers
// // except they store key-value pairs
// object ScalaMaps {
//     def main(args: Array[String]) {
//        val employees = Map ("Manager" -> "Test1", "Secretary" -> "Test2")

//        if(employees.contains("Manager"))
//         //  printf("Manager: %s\n", employees("Manager"))
       
//        val customers = collection.mutable.Map("cust1" -> "Some", "cust2" -> "Some Else")

//        //  printf("Test: %s\n", customers("cust1"))
       
//        customers("cust1") = "lala"
//        customers("cust2") = "lala whoop"
//        customers("cust3") = "haha"

//        for ((k, v) <- customers)
//            printf("%s: %s\n", k,v)

//     }
// }

// object ScalaTuples {
//     def main(args: Array[String]) {
//          var tupleManager = (103, "TestName", 10.25)
//          printf("%.2f\n", tupleManager._3)
//          tupleManager.productIterator.foreach{ i => println(i) }
//          println(tupleManager.toString())
//     }
// }

// object ScalaClasses {
//     def main(args: Array[String]) {
//         val rover = new Animal
//         rover.setName("Rover")
//         rover.setSound("Woof")
//         printf("%s says %s\n", rover.getName, rover.getSound)

//         val whiskers = new Animal("Whiskers", "Meow")
//         println(s"${whiskers.getName} with id ${whiskers.id} says ${whiskers.sound}")

//         println(whiskers.toString)

//         val spike = new Dog("Spike", "Woof", "Grrr")
//         println(spike.toString)
//     } // END of MAIN

//     class Animal(var name: String, var sound: String) {
//           this.setName(name)
//           val id = Animal.newIdNum
//           // protected var name = "No Name"

//           def getName() : String = name
//           def getSound() : String = sound

//           def setName(name: String) {
//               if (!(name.matches(".*\\d+.*")))
//                  this.name = name
//               else
//                  this.name = "No Name"
//           }

//           def setSound(sound: String) {
//               this.sound = sound
//           }

//           // When creating a animal objects, but you dont have the variables
//           def this(name: String) {
//               this("No Name", "No Sound")
//               this.setName(name)
//           }

//           def this() {
//               this("No Name", "No Sound")
//           }

//           // override method
//           override def toString() :String = {
//               return "%s with the id %d says %s".format(this.name, this.id, this.sound)
//           }

//     }

//     // Create a static static fields and static function
//     object Animal {
//         private var idNumber = 0
//         private def newIdNum = { idNumber += 1; idNumber}
//     }

//     // inheritance
//     class Dog(name: String, sound: String, growl: String) extends Animal(name, sound) {
//        def this(name: String, sound: String) {
//            this("No Name", sound, "No Growl")
//            this.setName(name)
//        }

//        def this(name: String) {
//            this("No Name", "No Sound", "No Growl")
//            this.setName(name)
//        } 

//        def this() {
//            this("No Name", "No Sound", "No Growl")
//        }

//        override def toString(): String = {
//            return return "%s with the id %d says %s".format(this.name, this.id, this.sound, this.growl)
//        }
//     }
// } 

// object ScalaAbstract {
//     def main(args: Array[String]) {
//         val fang = new Wolf("Fang")
//         fang.moveSpeed = 36.0
//         println(fang.move)
//     } // END OF MAIN

//     abstract class Mammal(val name: String) {
//         var moveSpeed: Double
//         def move: String
//     }

//     class Wolf(name: String) extends Mammal (name) {
//         var moveSpeed = 35.0
//         def move = "The wolf %s runs %.2f mph".format(this.name, this.moveSpeed)
//     }
// }

// // Scala trait is that you can can extend the class more than one
// // You cant extend multiple classes or abstract classes, but you can extend multiple traits
// object ScalaTrait {
//     def main(args: Array[String]) {
//        val superman = new Superhero("superman")
//        println(superman.fly)
//        println(superman.hitByBullet)
//        println(superman.ricochet(100))
//     } // END OF MAIN
    
//     trait Flyable {
//         // Method
//         def fly: String
//     }

//     trait BulletProof {
//         def hitByBullet: String

//         def ricochet(startSpeed: Double): String = {
//             "The bullet ricochets at a speed of %.1f/sec".format(startSpeed*.75)
//         }
//     }
    
//     class Superhero(val name: String) extends Flyable with BulletProof{
//         def fly = "%s flys through the air".format(this.name)

//         def hitByBullet = "The bullet bounces off of %s".format(this.name)
//     } 
// }

// object ScalaHighOrderFunc {
//     def main(args: Array[String]) {
//         // val log10Func = log10 _
//         // List(1000.0, 10000.0).map(log10Func).foreach(println)
//         // List(1,2,3,4,5).map((x:Int) => x*50).foreach(println)
//         // List(1,2,3,4,5).filter(_ %2 == 0).foreach(println)
//         def times3(num : Int) = num *3
//         def times4(num: Int) = num*4
//         def multIt(func: (Int) => Double, num: Int) = {
//              func(num)
//         }
//         printf("3*100 = %.1f\n", multIt(times3, 100))
//         // a closure is a function that declare variable outside that of the function
//         val divisorVal = 5
//         val divisor5 = (num: Double) => num / divisorVal
//         printf("5 /5 = %.1f\n", divisor5(5.0))
//     } // END OF MAIN
// }

// object ScalaFileIO {
//     def main(args: Array[String]) {
//         val writer = new PrintWriter("test.txt")
//         writer.write("some tests")
//         writer.close()

//         val textFromFile = Source.fromFile("text.txt", "UTF-8")
//         val lineIterator = textFromFile.getLines

//         for (line <- lineIterator)
//             println(line)
//     } // END OF MAIN
// }

// object ScalaException {
//     def main(args: Array[String]) {
//         def divideNums(num1: Int, num2: Int) = try {
//             (num1 / num2)
//         } catch {
//             case ex: java.lang.ArithmeticException => "Can't divide by zero"
//         } finally {
//             // clean up after exception
//         }

//         println("3/0=" + divideNums(3, 0))
//     } // END OF MAIN
// }