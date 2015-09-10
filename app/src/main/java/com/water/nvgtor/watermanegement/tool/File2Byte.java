package com.water.nvgtor.watermanegement.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by dell on 2015/9/6.
 */
public class File2Byte {
    public static byte[] getBytesFromFile(File f){
        if (f == null){
            return null;
        }
        try{
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            System.out.println(out.toByteArray());
            return out.toByteArray();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
