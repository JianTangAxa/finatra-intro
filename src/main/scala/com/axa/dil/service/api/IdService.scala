package com.axa.dil.service.api

import com.twitter.util.Future

trait IdService {
  def nextId(): Future[Int]
}
