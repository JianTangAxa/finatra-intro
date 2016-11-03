package com.axa.dil.controller

import javax.inject.Inject

import com.axa.dil.DocumentationSwagger
import com.axa.dil.service.TodoService
import com.github.xiaodongw.swagger.finatra.SwaggerSupport
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
//import com.twitter.finatra.validation.Size
import io.swagger.models.Swagger
import org.joda.time.DateTime


case class PostTodo(date: DateTime, /*@Size(min = 4, max=10)*/ event: String)

class TodoController @Inject()(todoService: TodoService) extends Controller with SwaggerSupport {

  override protected implicit val swagger: Swagger = DocumentationSwagger

/*
  get("/webapp/:*") { request: Request =>
    response.ok.fileOrIndex(
      request.params("*"),
      "index.html")
  }
*/

  get("/api/todos", swagger {
    o => o.summary("Get todo list")
      .tag("Todo service")
  }) {
    request: Request => todoService.listAll()
  }

  get("/api/todos/:id") {
    request: Request => todoService.getTodo(request.getIntParam("id"))
  }

  post("/api/todos", swagger {
    o => o.summary("Create new todo entry")
      .tag("Todo service")
      .bodyParam[PostTodo]("todo", "Todo to create")
  }) {
    todo: PostTodo => todoService.createTodo(todo)
  }
}