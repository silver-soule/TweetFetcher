package edu.knoldus
//import twitter4j.Twitter
import java.util.Date

import twitter4j.TwitterFactory

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * Created by Neelaksh on 26/7/17.
  */
class TweetApp {

  private val twitterObj = new TwitterFactory().getInstance

  def getTweetByHashtag(hashtag:String):Future[List[String]] = {
    Future {
      val query = new twitter4j.Query(hashtag)
      val result = twitterObj.search(query)
      val nameAndTweetList =
        for {tweet <- result.getTweets.asScala.toList
        } yield s"Name -> ${tweet.getUser.getScreenName} \n tweet text -> ${tweet.getText}\n\n"
      nameAndTweetList
    }
  }

  def getAccountLife(userName:String): Future[Long] = {
    Future {
      val accountCreatedAt = twitterObj.showUser(userName).getCreatedAt
      val today = new Date()
      (today.getTime - accountCreatedAt.getTime) / TweetApp.MILLISECONDSINADAY
    }
  }

  def getTotalTweets(userName:String) : Future[Int]  = {
    Future {
      twitterObj.showUser(userName).getStatusesCount
    }
  }

  def getAvgTweets(userName:String) : Future[Float] = {
      for {
        days <- getAccountLife(userName)
        tweetCount <- getTotalTweets(userName)
      } yield tweetCount.toFloat / days
  }

  def getAvgLikes(userName:String):Future[Float] = {
    Future {
      val tweetList = twitterObj.timelines().getUserTimeline(userName).asScala.toList
      val totalLikes = tweetList.map(_.getFavoriteCount).sum
      totalLikes / tweetList.size
    }
  }

  def getAvgRetweets(userName:String):Future[Float] = {
    Future {
      val tweetList = twitterObj.timelines().getUserTimeline(userName).asScala.toList
      val totalRetweets = tweetList.map(_.getRetweetCount).sum
      totalRetweets / tweetList.size
    }
  }

}


object TweetApp {
  private val MILLISECONDSINADAY = 86400000
}
