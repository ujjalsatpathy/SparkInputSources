package com.spark.input.sources

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

object SocketSourceStream {

  def main(args:Array[String]): Unit = {

    val host = "localhost"
    val port = 9999

    val spark = SparkSession.builder()
      .appName("SocketStream")
      .master("local[*]")
      .getOrCreate()


    val socketDF = spark
      .readStream
      .format("socket")
      .option("host", host)
      .option("port", port)
      .load()

    println(s"Streaming dataframe: ${socketDF.isStreaming}")

    socketDF
      .writeStream
      .outputMode(OutputMode.Append)
      .option("truncate", false)
      .format("console")
      .start()
      .awaitTermination()
  }
}
