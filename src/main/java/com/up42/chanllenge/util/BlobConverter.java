package com.up42.chanllenge.util;

import org.hibernate.engine.jdbc.BlobProxy;

import java.sql.Blob;
import java.sql.SQLException;

/**
 * This class presents logic to convert to or from a blob.
 */
public class BlobConverter
{

    public static Blob convertStringToBlob(String str)
    {
        return BlobProxy.generateProxy(str.getBytes());
    }


    public static String convertBlobToString(Blob blb) throws SQLException
    {
        return new String(convertBlobToByteArray(blb));
    }


    public static byte[] convertBlobToByteArray(Blob blb) throws SQLException
    {
        byte[] bytes = blb.getBytes(1, (int) blb.length());
        blb.free();
        return bytes;
    }
}
