package psug.dojos
 

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner.RunWith
import psug.dojos.P009._
import psug.dojos.Utils._
import org.specs2.ScalaCheck
import org.scalacheck.Prop
import org.specs2.execute.Result

@RunWith(classOf[JUnitRunner])
class TestP009 extends Specification with ScalaCheck {
  
  val solutions = List[(String, List[Symbol] => List[List[Symbol]])](
    ( "BenjaminLerman (1)" , BenjaminLerman.pack[Symbol]) 
    , ("SteveGury", SteveGury.pack)
    , ("AlexisAgahi" , AlexisAgahi.pack[Symbol])
    , ( "Francois" , Francois.pack[Symbol])
    , ( "BenjaminLerman (2)" , BenjaminLerman.pack2[Symbol]) 
    , ( "StefanChris" , StefanChris.pack[Symbol] )
    , ( "JérômeMainaud", JérômeMainaud.pack[Symbol] )
    , ( "AriéBenichou", AriéBenichou.pack[Symbol] )
  )
  
  def miniBench() : Unit = {
    val r = new util.Random 
    val input = (1 to 100000).map{ _ => Symbol( r.nextInt(10).toString ) }.toList
    for (s <- solutions) {
      println(time(s._1, s._2(input)))
    }
  }

  "All possible solutions" should {
    
    "at least pass the problem example" in {
      val expected = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
     ( (x:(String,List[List[Symbol]])) => (x._1,x._2) === (x._1,expected) ).foreach( 
         solutions.map {case(name, f) => (name,f(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))) }
     )
    }    
  }
 
  
// don't seem to work... I don't know how to compose examples.   
//  solutions.foreach { case (name, pack) => 
//    this.addExample {
//      name + " pass DavidBernard.propFlattenPackIsIdentity" in {
//        check(DavidBernard.propFlattenPackIsIdentity( pack  ) )
//       }
//    }
//  }
  
  
}