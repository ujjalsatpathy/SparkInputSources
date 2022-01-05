# Spark Input Sources
This repository contains examples of Spark Structure Streaming with the following input sources

**Socket Source** - Used for testing purpose and reads UTF8 text data from a socket connection\
**Rate Source** - Used for testing purpose and generates data at the specified number of rows per second.Each output contains a timestamp and a value\
**Kafka Source** - Reads data from Kafka. It’s compatible with Kafka broker versions 0.10.0 or higher.\
**File Source** - Reads files written in a directory as a stream of data.Supported file formats are text, CSV, JSON, ORC, Parquet.