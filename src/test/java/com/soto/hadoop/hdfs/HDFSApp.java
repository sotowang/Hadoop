package com.soto.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * Hadoop HDFS Java API 操作
 */
public class HDFSApp {

    public static final String HDFS_PATH = "hdfs://sotowang-pc:8020";

    FileSystem fileSystem = null;
    Configuration configuration = null;

    /**
     * 创建HDFS目录
     * @throws Exception
     */
    @Test
    public void mkdir() throws Exception {
        fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }

    /**
     * 创建文件
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        FSDataOutputStream output = fileSystem.create(new Path("/hdfsapi/test/a.txt"));
        output.write("hello hadoop".getBytes());
        output.flush();
        output.close();
    }

    /**
     * 查看HDFS文件上的内容
     * @throws Exception
     */
    @Test
    public void cat() throws Exception {
        FSDataInputStream in = fileSystem.open(new Path("/hdfsapi/test/a.txt"));
        IOUtils.copyBytes(in, System.out, 1024);
    }




    @Before
    public void setUp() throws Exception {
        System.out.println("HDFSApp.setUp");

        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration,"sotowang");
    }


    @After
    public void tearDown() throws Exception {
        configuration = null;
        fileSystem = null;
        System.out.println("HDFSApp.tearDown");

    }


}
