package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class TaskList1 @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def login = Action {
    Ok(views.html.login1())
  }

  def validateLoginGet(username: String, password: String) = Action {
    Ok(s"$username logged in with $password.")
  }

  def validateLoginPost = Action { request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      println(args)
      val username = args("username").headOption
      val password = args("password").headOption
      Redirect(routes.TaskList1.taskList)
    }.getOrElse(Redirect(routes.TaskList1.login))


  }

  def taskList = Action {
    val tasks = List("task1", "task2", "task3", "Sleep", "Eat")
    Ok(views.html.taskList1(tasks))
  }

}
