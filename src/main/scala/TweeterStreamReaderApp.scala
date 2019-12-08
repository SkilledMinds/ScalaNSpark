/**
 * @author CloudAI Pvt Ltd
 * @Date : 08/12/19
 */

import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import twitter4j.auth.OAuthAuthorization
import twitter4j.conf.ConfigurationBuilder


object TweeterStreamReaderApp {
  def main(args: Array[String]) {

    var twitterCredentials = new Array[String](4);
    //consumerKey
    twitterCredentials(0) = "gA7xFE3S1QfXRkbZVTN55Uuzb";
    //consumerSecret
    twitterCredentials(1) = "2te2Z1yFvynXcp06rc2j3zg38tNAa1zY29iGPnSUArOT3d5BFI";
    //accessToken
    twitterCredentials(2) = "1063309301232660480-61DChczOivazJZTWodLfuRRW8gDNfJ";
    //accessTokenSecret
    twitterCredentials(3) = "bFYPmpiWhFgOtdJGe95YyhOntxOQAmx698imO1N0xEYtF";

    val appName = "TweeterStreamReader"
    val conf = new SparkConf()
    conf.setAppName(appName).setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(5))
    val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = twitterCredentials.take(4)
    val filters = args.takeRight(args.length - 4)
    val cb = new ConfigurationBuilder
    cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)
    val auth = new OAuthAuthorization(cb.build)
    val tweets = TwitterUtils.createStream(ssc, Some(auth), filters)
    val englishTweets = tweets.filter(_.getLang() == "en")

    englishTweets.repartition(1)
    englishTweets.foreachRDD { (rdd, time) =>
      p(rdd)
    }

    def p(rdd: org.apache.spark.rdd.RDD[_]) = rdd.foreach(println)
    ssc.start()
    ssc.awaitTermination()
  }
}