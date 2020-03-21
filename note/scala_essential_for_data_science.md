# Table of Contents
*  ### [Introduction](#introduction)
*  ### [Parallel Processing](#scala-parallel-processing)
*  ### [Scala and SQL](#using-scala-in-sql)
*  ### [Scala and Saprk RDDS](#scala-and-spark-rdds)
*  ### [Scala and Spark DataFrames](#spark-dataframes)




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
```bash
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
```scala
//Not need to declare the type
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
 ```scala
 //Mutable Array
 val temps = Array(50, 51, 56, 53, 40)
 temps.length
 temps(0) = 52
 val temps2: Array[Int] = new Array[Int](10)
 val temps3 = Array.ofDim[Int](10, 10)
 import Array._
 concat(temps, temps2)
 temps. //enter tab

 //Immutable Vectors
 val vec1 :Vector[Int] = Vector(1,2,3)
 vec1(2)
 val myRange = 1 to 10
 val myRange2: Range = new Range(1, 101, 2)

 //Map
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
```scala
println({
     val a = 2*3
     a + 4
})
```
### Functions
```scala
def myFunction(a: Int, b: Int): Int = {
  val c = a*b
  return c
}

//a function that is not required for return value, we call it procedures
def myProcedure(inStr: String): Unit = {
  println(inStr)
}
```
### Objects
```scala
class location(var latitude:Int, var lat_direction:Char, var longitude: Int,  var long_direction: Char, var altitude: Int)
val loc1 = new location(45, 'N', 120, 'W', 300)
loc1.altitude
loc1.lat_direction
loc1. //tab

// define a private member
class myPubliPrivate(val x:Int=0, val y:Int=0, private val z: Int=0)
val myPP = new myPublicPrivate
myPP. //tab
// private member can only be referenced inside the class 
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

```scala
// convert a sequential collection into a parallel collections
val rng = 1 to 100
val prang = rng.par
// define a parallel collections
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
// filtering
val v = (1 to 10000).toArray
val pv = v.par
v.length
pv.length
val pvf =pv.filter(_ > 5000)
pvf.length
val pvf2 = pv.filterNot(_ > 5000)
pvf2.length
// filter can work on customized function that return boolean value
def div3 (x: Int): Boolean = {
  val y: Int = (x%3); return (y == 0)
}
pv.filter(div3(_))
```

# Using Scala in SQL
### Set up PostgreSQL
> Download postgreSQL from: https://www.postgresql.org/download/
* Create database:  `createdb scala_db`
* Create user: `createuser scala_db`
* Run the command to import the query: `psql -U scala_db -d scala_db -a -f ~/Desktop/Exercises/foo.sql`
* Connect to the db: `psql -U scala_db -d scala_db` 
### Use scala to connect to PostgreSQL
> Download JDBC for PostgreSQL if needed: https://jdbc.postgresql.org/
* Import the plugin: `scala -classpath /usr/local/lib/psql/share/java/postgresql-42.1.1.jre6.jar`
* Command samples:
```scala
import java.sql.D #\t
import java.sql.DriverManager
import java.sql.Connection
val driver = "org.postgresql.Driver"
val url = "kdbc:postgresql://localhost/scala_db?user=scala_db"
Class.forName(driver)
var connection: Connection = null
connection = DirverManager.getConnection(url)
val statement = connection.createStatement()
val resultSet = statement.excuteQuery("select * from emps") # a cursor
resultSet.next
resultSet.getString("last_name") 
val resultSet2 = statement.excuteQuery("select * from company_divisions")
while (resultSet.next()) {
  val dept = resultSet.getString("department")
  val comp_div = resultSet2.getString("company_division")
  println(dept + " " + comp_div)
}
// work with prepared statements
val query_str =  "select * from company_regions where region_id > ?"
val ps = connection.prepareStatement(query_str)
ps.setInt(1, 5) //set the first question mark as 5
val rs = ps.executeQuery() 
rs.getString("company_regions")
rs.getInt("region_id")
```
Summary
1. Requires installation of a JDBC driver
2. Queries return cursors
3. SQL statements can be constructed as strings
4. Prepared statements are better for a repeatedly used statements

# Scala and Spark RDDs
Advantage of Spark
1. Fast processing of libraries for analytics
2. Stream processing
3. Fault tolerant
4. Scalable
### Resilient Distributed Datasets(RDDS)
1. Immutable distributed collection
2. Organized into logic partitions
3. Fault-tolerant collection
4. May keep data in memory or persisted into disk

RDDs VS Parallel Collections

|Similarities| Difference|
|----|:---------------------|
|1. Groups of data of the same type of structure|1. RDDs are partitioned by a hash function and Parallel collections are broken into subsets|
|2. Data processed in parallel|2. RDDs are distributed across multiple servers and Parallel collections are distributed across cores or threads within a server at runtime|
|3. Faster than working with sequential operations|3. RDD data can be easily persisted to permanent storage|

```scala
import scala.util.Random
val bigRang = scala.util.Random.shuffle(1 to 100000)
val bigPRng = sc.parallelize(bigRng)
bigPrng.take(25)
// Mapping function
val bigPRng2 = bigPRng.map(_ * 2)
bitPRng2.take(25)
def div(x:Int): Boolean = {
  val y:Int(x%3):
  return (y == 0)
}
val bigBool = bigPRng2.map(div(_))
bigBool.take(25)
val republic = sc.textFile("PATH_OF_THE_TEXT")
republic.take(25).foreach(println)
val linesWithSocrates = republic.filter(line  => line.contains("Socrates"))
linesWithSocrates.take(10).foreach(println)
// Statistics
import org.apache.spark.mllib.stat.Statistics
val x = bigPRng3.takeSample(true, 1000) // drawing with replacement
bigPRng2.stats
val series1 = Array.fill(100000)(Random.nextDouble)
val series2 = Array.fill(100000)(Random.nextDouble)
val pseries1 = sc.parallelize(series1)
val pseries2 = sc.parallelize(series2)
val myCorrelation: Double = Statistics.corr(pseries1, pseries2, "pearson")
val distTest = Statistics.kolmogorovSmirnovTest(pseries1, pseries2)
```

# Spark DataFrames
DataFrames: Like relational tables. They are a data structure that's organized into rows and they have name columns
```scala
import org.apache.spark.sql.SparkSession
val spark = Spark.Session.builder().appName("DataFramesExercise").getOrCreate()
val df_emps = spark.read.option("header", "true").csv("PATH_OF_THE_FILE")
df_emps.take(10)
df_emps.show() // table like
val df_cr = spark.read.option("header", "true").csv("PATH_OF_THE_FILE")
df_cr.columns // List the columns
val df_dd = spark.read.option("header", "true").csv("PATH_OF_THE_FILE")

// Grouping and Filtering
df_emps.createOrReplaceTempView("employees")
val sqldf_emps = spark.sql("SELETC * FROM employees")
val sqldf_emps_by_dept = spark.sql("SELECT department, count(*) FROM employees GROUP BY department")
val sqldf_depts = spark.sql("SELECT DISTINCT department FROM employees")
val sqldf_emps_100 = spark.sql("SELECT * FROM employees WHERE id < 100")

// Joining
val df_joined = df_emps.join(df_cr, "region_id")

// Working with JSON file
vla df_json_dd = spark.read.json("PATH_OF_THE_FTILE")
```



