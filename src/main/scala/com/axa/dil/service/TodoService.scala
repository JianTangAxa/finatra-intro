package com.axa.dil.service

import javax.inject.{Inject, Singleton}

import com.axa.dil.controller.PostTodo
import com.axa.dil.service.api.{IdService, TodoStore}
import com.axa.dil.service.domain.Todo
import com.twitter.util.Future


@Singleton
class TodoService @Inject()(idService: IdService, store: TodoStore) {
  def listAll(): Future[List[Todo]] = store.getAll

  def createTodo(todo: PostTodo): Future[Todo] = {

    for {
      id <- idService.nextId()
      newTodo <- Future(Todo(id, todo.date, todo.event))
      _ <- store.saveTodo(newTodo)
    } yield newTodo
  }

  def getTodo(id: Int): Future[Option[Todo]] = store.getTodo(id)
}
