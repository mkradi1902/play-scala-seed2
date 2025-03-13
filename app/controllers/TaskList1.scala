package controllers

import models.TaskListInMemoryModel

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
      val username = args("username").head
      val password = args("password").head
      if(TaskListInMemoryModel.validateUser(username , password)){
        Redirect(routes.TaskList1.taskList)
      }
      else {
        Redirect(routes.TaskList1.login)
      }
    }.getOrElse(Redirect(routes.TaskList1.login))

  }
  
  def createUser  =  Action { request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val username = args("username").head
      val password = args("password").head
      if(TaskListInMemoryModel.createUser(username , password)){
        Redirect(routes.TaskList1.taskList)
      }
      else {
        Redirect(routes.TaskList1.login)
      }
    }.getOrElse(Redirect(routes.TaskList1.login))

  }

  def taskList = Action {
    val username="Mohammad"
    val tasks= TaskListInMemoryModel.getTasks(username)
    Ok(views.html.taskList1(tasks))
  }

}
