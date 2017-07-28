package edu.knoldus

import org.scalatest.AsyncFunSuite
import twitter4j.TwitterException

import scala.concurrent.Await

/**
  * Created by Neelaksh on 27/7/17.
  */
class TweetAppTest extends AsyncFunSuite {

  val tweetapp = new TweetApp()

  test("test getAccountLife positive") {
    val dayCount = tweetapp.getAccountLife("raymondh")
    dayCount.map {
      days => assert(days > 0)
    }
  }

  test(" test getAccountLife negative") {
    recoverToSucceededIf[TwitterException] {
       tweetapp.getAccountLife("alfmowqcafn")
    }
  }

  test("test getAvgTweets positive") {
    val avgTweets = tweetapp.getAvgTweets("raymondh")
    avgTweets.map {
      tweetsperDay => assert(tweetsperDay > 0)
    }
  }

  test("test getAvgTweets negative") {
    recoverToSucceededIf[TwitterException] {
      val avgTweets = tweetapp.getAvgTweets("afnwfnwjfwf")
      avgTweets
    }
  }


  test("test getTweetByHashtag positive") {
    val tweetData = tweetapp.getTweetByHashtag("slvind")
    tweetData.map {
      tweetList => assert(tweetList.nonEmpty)
    }
  }

  test("test getTweetByHashtag negative") {
    recoverToSucceededIf[TwitterException] {
      val tweetData = tweetapp.getTweetByHashtag("")
      tweetData
    }
  }



  test("test getTotalTweets positive") {
    val totalTweets = tweetapp.getTotalTweets("raymondh")
    totalTweets.map {
      totalTweets => assert(totalTweets > 0)
    }
  }

  test("test getTotalTweets negative") {
    recoverToSucceededIf[TwitterException] {
      val totaltweets = tweetapp.getTotalTweets("afnwfnwjfwf")
      totaltweets
    }
  }

  test("test getAvgLikes positive") {
    val avgLikes = tweetapp.getAvgLikes("raymondh")
    avgLikes.map {
      avgLikes => assert(avgLikes > 0)
    }
  }

  test("test getAvgLikes negative") {
    recoverToSucceededIf[TwitterException] {
      val avgLikes = tweetapp.getAvgLikes("afnwfnwjfwf")
      avgLikes
    }
  }

  test("test getAverageRetweets positive") {
    val avgretweets = tweetapp.getAvgRetweets("raymondh")
    avgretweets.map {
      avgretweets => assert(avgretweets > 0)
    }
  }

  test("test getAvgReTweets negative") {
    recoverToSucceededIf[TwitterException] {
      val avgReTweets = tweetapp.getAvgRetweets("afnwfnwjfwf")
      avgReTweets
    }
  }

}
