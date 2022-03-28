import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{ConnectionFactory, Get, Put, Scan}
import org.apache.hadoop.hbase.util.Bytes


object HbaseClientSample {

  def main(args:Array[String]):Unit = {

    println("Connecting to Hbase....")

    val conf : Configuration = HBaseConfiguration.create()

    val ZOOKEEPER_QUORUM = "localhost"
    conf.set("hbase.zookeeper.quorum", ZOOKEEPER_QUORUM)
    conf.set("hbase.zookeeper.property.clientPort","2181")
    conf.set("zookeeper.znode.parent","/hbase")
    conf.set("hbase.cluster.distributed","false")

    val connection = ConnectionFactory.createConnection(conf)

    val admin = connection.getAdmin()

    val listtables=admin.listTables()

    println("******************************************")

    listtables.foreach(println)

    println("__________________________________________")

    val table = connection.getTable(TableName.valueOf("users"))

    val key = new Get(Bytes.toBytes(1))

    val row = table.get(key);

    println(row);
    println("_____________before adding rows_____________________________")

    val newrow = new Put(Bytes.toBytes(2))

    newrow.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("Name"),Bytes.toBytes("Santhosh"))
    newrow.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("age"),Bytes.toBytes("88"))

    table.put(newrow)
    println("_____________after adding rows_____________________________")
    val scan = new Scan()
    scan.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("Name"))
    scan.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("age"))
    val scaniterator = table.getScanner(scan).iterator()

    scaniterator.forEachRemaining({x =>
      println(Bytes.toString(x.getValue(Bytes.toBytes("cf"),Bytes.toBytes("Name"))))
    })

    connection.close()
    println("End....")
  }

}
