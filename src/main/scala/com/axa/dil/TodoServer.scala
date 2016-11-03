package com.axa.dil

import com.axa.dil.controller.TodoController
import com.axa.dil.service.TodoServiceModule
import com.github.xiaodongw.swagger.finatra.SwaggerController
import com.google.inject.Module
import com.twitter.finagle.http.filter.CorsFilter
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import io.swagger.models.{Info, Swagger}


object DocumentationSwagger extends Swagger

class TodoServer extends HttpServer {
  val info = new Info()
    .description("Todo server")
    .version("1.0")
    .title("Todo list management")

  DocumentationSwagger.info(info)

  protected override def modules: Seq[Module] = Seq(TodoServiceModule)

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter(CorsFilter("*", "GET, POST, PUT, DELETE", "x-requested-with,Content-Type", "Content-Type"))
      .filter[CommonFilters]
      .add(new SwaggerController(swagger = DocumentationSwagger))
      .add[TodoController]


  }
}


object TodoServerMain extends TodoServer
