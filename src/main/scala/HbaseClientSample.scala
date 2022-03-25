import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.ConnectionFactory

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

    println("End....")
  }

}
