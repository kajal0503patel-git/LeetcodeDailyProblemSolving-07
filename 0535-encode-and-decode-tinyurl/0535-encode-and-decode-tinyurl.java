public class Codec {
    private static final String BASE = "http://tinyurl.com/";
    private final Map<String, String> map = new HashMap<>();
    private int counter = 0;

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key = String.valueOf(counter++);
        map.put(key, longUrl);
        return BASE + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String key = shortUrl.substring(BASE.length());
        return map.get(key);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));