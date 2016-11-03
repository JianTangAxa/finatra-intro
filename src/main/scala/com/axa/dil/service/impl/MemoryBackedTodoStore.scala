package com.axa.dil.service.impl

import java.util.concurrent.ConcurrentHashMap

import com.axa.dil.service.api.TodoStore
import com.axa.dil.service.domain.Todo
import com.twitter.util.Future
import scala.collection.JavaConversions._

class MemoryBackedTodoStore extends TodoStore {

  val store: ConcurrentHashMap[Int, Todo] = new ConcurrentHashMap[Int, Todo]()

  override def getTodo(id: Int): Future[Option[Todo]] = Future(Option(store.get(id)))

  override def saveTodo(todo: Todo): Future[Unit] = Future {
    store.put(todo.id, todo)
  }

  override def getAll: Future[List[Todo]] = Future {
    store.values().toList
  }
}
