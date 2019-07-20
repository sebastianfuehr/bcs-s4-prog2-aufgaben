package streams;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Citizens {
    public static final String SEX_M = "1";
    public static final String SEX_F = "2";

    public static List<Record> citizenList() throws FileNotFoundException, UnsupportedEncodingException {
        String name = "EWR_Ortsteile_Berlin_2015_utf8.csv";
        FileInputStream fileInputStream = new FileInputStream(getResourceFile(name));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        return bufferedReader.lines().skip(1).map(Record::fromLine).collect(Collectors.toList());
    }

    private static File getResourceFile(String name) throws UnsupportedEncodingException {
        URL resource = Citizens.class.getClassLoader().getResource(name);
        URL url = Objects.requireNonNull(resource);
        String decode = URLDecoder.decode(url.getFile(), "UTF-8");
        return new File(decode);
    }
}
