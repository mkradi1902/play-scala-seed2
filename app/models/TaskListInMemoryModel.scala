package models

import collection.mutable

object TaskListInMemoryModel {
  private val users = mutable.Map[String, String]("Mohammad" -> "password")
 private val tasks=mutable.Map[String,List[String]]("Mohammad" -> List("Attend Calls", "Have Lunch","Complete excersise", "sleep"))

  def validateUser(username: String, password: String): Boolean = {
users.get(username).map(_==password).getOrElse(false)
  }

def createUser(username: String, password: String): Boolean = {
if(users.contains(username)) false else {
  true
}

}

def getTasks(username: String): Seq[String] = {
  tasks.get(username).getOrElse(Nil)
}
def addTask(username: String, task: String): Unit = ???
def removeTask(username: String, index: Int): Boolean = ???
}

