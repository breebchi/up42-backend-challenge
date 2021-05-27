package com.up42.chanllenge.util;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.SQLException;

public class BlobStringInterConverter
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
        return blb.getBytes(1, (int) blb.length());
    }
}
