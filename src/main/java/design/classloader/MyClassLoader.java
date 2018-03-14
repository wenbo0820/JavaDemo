package design.classloader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by GongWenBo on 2018/1/31.
 */
public class MyClassLoader extends ClassLoader {
    private String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(path, getFileName(name));
        try {
            FileInputStream stream = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            FileChannel channel = stream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            WritableByteChannel write = Channels.newChannel(out);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                write.write(buffer);
                buffer.clear();
            }
            byte[] data = out.toByteArray();
            out.close();
            write.close();
            channel.close();
            stream.close();
            return defineClass(name, data, 0, data.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return name + ".class";
        }
        return name.substring(index + 1) + ".class";
    }
}
