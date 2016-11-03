package com.axa.dil.service.api

import com.axa.dil.service.domain.Todo
import com.twitter.util.Future


trait TodoStore {
  def getTodo(id: Int): Future[Option[Todo]]

  def getAll: Future[List[Todo]]

  def saveTodo(todo: Todo): Future[Unit]
}
