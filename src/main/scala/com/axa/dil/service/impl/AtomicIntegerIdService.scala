package com.axa.dil.service.impl

import java.util.concurrent.atomic.AtomicInteger

import com.axa.dil.service.api.IdService
import com.twitter.util.Future


class AtomicIntegerIdService extends IdService {

  val idGenerator = new AtomicInteger()

  override def nextId(): Future[Int] = Future(idGenerator.incrementAndGet())
}
