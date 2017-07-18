package book.performance.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class NioCopyFile {
    public static void nioCopyFile(String resource, String destination) throws IOException {
        File file = new File(destination);
        if (file.exists()) {
            file.delete();
        }
        FileInputStream fis = new FileInputStream(resource);
        FileOutputStream fos = new FileOutputStream(destination);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer);
            if (len == -1) {
                break;
            }
            buffer.flip();
            writeChannel.write(buffer);
        }
        readChannel.close();
        writeChannel.close();
    }

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources/";
        nioCopyFile(path + "test.txt", path + "b.txt");
    }
}
