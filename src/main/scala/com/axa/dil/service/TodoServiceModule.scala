package com.axa.dil.service

import com.axa.dil.service.api.{IdService, TodoStore}
import com.axa.dil.service.impl.{AtomicIntegerIdService, MemoryBackedTodoStore}
import com.twitter.inject.TwitterModule

object TodoServiceModule extends TwitterModule {
  override def configure(): Unit = {
    bind[IdService].to[AtomicIntegerIdService]
    bind[TodoStore].to[MemoryBackedTodoStore]
  }
}
