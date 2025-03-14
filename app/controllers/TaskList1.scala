package controllers

import models.TaskListInMemoryModel

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class TaskList1 @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def login = Action { implicit request =>
    Ok(views.html.login1())
  }

  def validateLoginGet(username: String, password: String) = Action {
    Ok(s"$username logged in with $password.")
  }

  def validateLoginPost = Action { implicit request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val username = args("username").head
      val password = args("password").head
      if(TaskListInMemoryModel.validateUser(username , password)){
        Redirect(routes.TaskList1.taskList).withSession("ussername" -> "username")
      }
      else {
        Redirect(routes.TaskList1.login)
      }
    }.getOrElse(Redirect(routes.TaskList1.login))

  }
  
  def createUser  =  Action { implicit request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val username = args("username").head
      val password = args("password").head
      if(TaskListInMemoryModel.createUser(username , password)){
        Redirect(routes.TaskList1.taskList).withSession("username" -> "username")
      }
      else {
        Redirect(routes.TaskList1.login)
      }
    }.getOrElse(Redirect(routes.TaskList1.login))

  }

  def taskList = Action { implicit request =>
    val usernameOption = request.session.get("username")
    usernameOption.map { username =>
      val tasks = TaskListInMemoryModel.getTasks(username)
      Ok(views.html.taskList1(tasks))
    }.getOrElse(Redirect(routes.TaskList1.login))
  }

  def Logout = Action {
    (Redirect(routes.TaskList1.login)).withNewSession
  }
}
