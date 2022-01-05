package com.spark.input.sources

import org.apache.spark.sql.execution.streaming.FileStreamSource.Timestamp
import org.apache.spark.sql.{Encoder, Encoders, SparkSession}
import org.apache.spark.sql.functions._

import java.util.Date


case class Data(timestamp: Timestamp, value: Long, incremented_value: Long)

object RateStreamSource {

  def main(args:Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("RateStream")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._
    implicit val rateEncoder:Encoder[Data] = Encoders.product[Data]

    val rateDF = spark
      .readStream
      .format("rate")
      .load()
    println(s"Rate DataSet : ${rateDF.isStreaming}")

    val parsedDF = rateDF.withColumn("incremented_value",$"value"+1).as[Data]

    parsedDF
      .writeStream
      .format("console")
      .option("truncate",false)
      .start()
      .awaitTermination()
  }
}
