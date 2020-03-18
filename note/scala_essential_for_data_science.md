# Table of Contents
*  ### [Introduction](#introduction)
*  ### [Parallel Processing](#scala-parallel-processing)
*  ### [Scala and SQL](#using-scala-in-sql)




# Introduction
### Advantage of using Scala
Scala is a valuable language for data science, because it is a language designed for scalability. Beneficial of using Scala:
1. JVM Language: it complies to Java bytecode and executes on the Java runtime;
2. Function programming: Compute by evaluating functions and minimizing the need to maintain state;
3. OOP: program are structured around data structures and methods rather than actions;
4. Work with SQL: JDBC support;
5. Parallel processing: use multiple cores, support for parallel collections, and especially important for scaling applications to large data volumes;
6. Spark: big data platform written in Scala
### Scala data structures covered in this tutorial
* `Sequential Collections`: Arrays, Vectors, HashMap, HashSet
* `Parallel Collections`: ParArrays, ParVector, ParHashMap, ParHashSet
### Installation
```
# remove the installed scala if any
sudo apt-get remove scala-library scala
sudo wget https://downloads.lightbend.com/scala/2.12.3/scala-2.12.3.deb
sudo dpkg -i scala-2.12.3.deb
sudo apt-get update
sudo apt-get install scala
```
### Scala Data types: Scalar
Scala command line tool: REPL(REad Eval Print Loop)
Byte, short, Int, long, float, double, char
```
# Not need to declare the type
var a_int: Int = 3
var a_char : Char = 'd'
var a_long : Long = 8345679
var b_int = 3
var b_char = 'd'
var b_long = 8345679489l  #default is int
var c_float = 1.2345f     #default is double
```
### Types of collections
1. Sequences
   Seq: Vectors, Streams, Lists, Queues, Strings, Stacks
2. Sets: HashSet, SortedSet, TreeSet, BitSet, ListSet
3. Maps: HashMaps, SortedMaps, TreeMaps, ListMaps

`Note`: Collections are either mutable or immutable
 ```
 # Mutable Array
 val temps = Array(50, 51, 56, 53, 40)
 temps.length
 temps(0) = 52
 val temps2: Array[Int] = new Array[Int](10)
 val temps3 = Array.ofDim[Int](10, 10)
 import Array._
 concat(temps, temps2)
 temps. #/t

 # Immutable Vectors
 val vec1 :Vector[Int] = Vector(1,2,3)
 vec1(2)
 val myRange = 1 to 10
 val myRange2: Range = new Range(1, 101, 2)

 # Map
 val capitals = Map("Argentina" -> "Buenos Aires", "Canada" -> "Ottowa")
 capitals.keys
 capitals.values
 capitals get "Argentina"
 capitals("Canada")
 capitals contains "Egypt"
 capitals getOrElse("China", "No capitals found")
 capitals + ("Ireland" -> "Dublin")
 capitals - "Liberia"
 ```
### Expressions
When you have multiple expressions and you want to treat as one. Then use braces:
```
println({
     val a = 2*3
     a + 4
})
```
### Functions
```
def myFunction(a: Int, b: Int): Int = {
  val c = a*b
  return c
}

# a function that is not required for return value, we call it procedures
def myProcedure(inStr: String): Unit = {
  println(inStr)
}
```
### Objects
```
class location(var latitude:Int, var lat_direction:Char, var longitude: Int,  var long_direction: Char, var altitude: Int)
val loc1 = new location(45, 'N', 120, 'W', 300)
loc1.altitude
loc1.lat_direction
loc1. #tab

# define a private member
class myPubliPrivate(val x:Int=0, val y:Int=0, private val z: Int=0)
val myPP = new myPublicPrivate
myPP. #tab
# private member can only be referenced inside the class 
class Point2D(coord1:Int, coord2: Int) {
  var a: Int = coord1
  var b: Int = coord2
  def move(delta_a: Int, delta_b: Int) {
    a = a + delta_a
    b = b + delta_b
  }
}
val point1 = new Point2D(10, 20)
point1.move(5, 15)
```
# Scala Parallel Processing
| Sequential Collections | Parallel Collections|
| -----------------------| -------------------:|
| Arrays, Vectors,Hashmap, HashSet| ParArrays, ParVector, ParHashMap|
1. Parallel collections should be considered only when you have at least thousands 
2. For some types of collections, converting between sequential and parallel collections requires copying the content of a collection
3. Best to avoid applying procedures with the side effects
4. avoid nonassociative operations

```
# convert a sequential collection into a parallel collections
val rng = 1 to 100
val prang = rng.par
# define a parallel collections
import scala.collection.parallel.immutable.ParVector
val pevac = ParVector.range(0, 200)
val v = (1 to 100).toArray
val pv = v.par
v.map(_ * 2)
pv.map(_ * 2)

def square (x: Int): Int = {
  return x*x
}
v.map(square(_))
pv.map(square(_))
# filtering
val v = (1 to 10000).toArray
val pv = v.par
v.length
pv.length
val pvf =pv.filter(_ > 5000)
pvf.length
val pvf2 = pv.filterNot(_ > 5000)
pvf2.length
# filter can work on customized function that return boolean value
def div3 (x: Int): Boolean = {
  val y: Int = (x%3); return (y == 0)
}
pv.filter(div3(_))
```

# Using Scala in SQL