package com.axa.dil

import com.axa.dil.service.api.{IdService, TodoStore}
import com.axa.dil.service.domain.Todo
import com.google.inject.testing.fieldbinder.Bind
import com.twitter.finagle.http.Status._
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.Mockito
import com.twitter.inject.server.FeatureTest
import com.twitter.util.Future
import org.joda.time.DateTime

class TodoServerTest extends FeatureTest with Mockito {

  override val server = new EmbeddedHttpServer(new TodoServer {
    override val overrideModules = Seq(integrationTestModule)
  })

  @Bind
  val idService = smartMock[IdService]

  @Bind
  val todoStore = smartMock[TodoStore]


  "Post todo" in {
    val id = 456
    idService.nextId() returns Future(id)

    val date = DateTime.parse("2016-12-01")

    val todo = Todo(id, date, event = "Go to dev summit")
    todoStore.saveTodo(todo) returns Future.Unit

    server.httpPost(path = "/api/todos/", postBody =
      s"""
        |{
        |   "date":"$date",
        |   "event":"Go to dev summit"
        |}
      """.stripMargin, andExpect = Ok,
      withJsonBody =
        s"""
          |{
          |  "id": $id,
          |  "date":"$date",
          |  "event":"Go to dev summit"
          |}
        """.stripMargin)

  }
}
