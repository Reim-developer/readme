package readme.app.ulti;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressedCache {
    private final Map<Integer, byte[]> cache =  new LinkedHashMap<>(500, 0.75f, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry<Integer, byte[]> eldest) {
          return size() > 200;
      }
    };

    @NotNull
    private byte[] compress (@NotNull String data) throws Exception {
        Deflater deflater = new Deflater();
        deflater.setInput(data.getBytes());
        deflater.finish();

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
          byte[] buffer = new byte[512];

          while(!deflater.finished()) {
              int compressedSize = deflater.deflate(buffer);
              outputStream.write(buffer, 0, compressedSize);
          }
          deflater.end();
          return outputStream.toByteArray();
        }
    }

    @NotNull
    private String decompress(@NotNull byte[] compressedData) {
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(compressedData);

            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[512];

                while(!inflater.finished()) {
                    int deCompressedSize = inflater.inflate(buffer);
                    outputStream.write(buffer, 0, deCompressedSize);
                }
                inflater.end();
                return outputStream.toString();
            }
        } catch (Exception exception) {
            System.out.println("" + exception);
            return  "";
        }
    }

    @NotNull
    public String getChunk(int index) {
        byte[] compressedData = cache.get(index);
        return compressedData == null ? "" : decompress(compressedData);
    }

    public void cacheChunk(int index, String content) {
        try {
            byte[] compressedData = compress(content);
            cache.put(index, compressedData);
        } catch (Exception exception) {
            System.out.println("" + exception);
        }
    }
}
