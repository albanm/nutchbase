package org.apache.nutch.storage;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class Driver {

  public static void main(String[] args) throws Exception {
    HBaseAdmin admin  = new HBaseAdmin(new HBaseConfiguration());
    HTableDescriptor tableDesc = new HTableDescriptor("webtable");
    tableDesc.addFamily(new HColumnDescriptor("p"));
    tableDesc.addFamily(new HColumnDescriptor("f"));
    tableDesc.addFamily(new HColumnDescriptor("ol"));
    tableDesc.addFamily(new HColumnDescriptor("mtdt"));
    
    admin.createTable(tableDesc);
    
    HTable table = new HTable("webtable");
    Put put = new Put(Bytes.toBytes("http://com.google/"));
    put.add(Bytes.toBytes("f"), Bytes.toBytes("ts"), Bytes.toBytes(System.currentTimeMillis()));
    put.add(Bytes.toBytes("f"), Bytes.toBytes("st"), Bytes.toBytes(200));
    put.add(Bytes.toBytes("p"), Bytes.toBytes("t"), Bytes.toBytes("This is the title"));
    put.add(Bytes.toBytes("p"), Bytes.toBytes("c"), Bytes.toBytes("This is the content"));

    table.put(put);
    table.close();
  }
}
