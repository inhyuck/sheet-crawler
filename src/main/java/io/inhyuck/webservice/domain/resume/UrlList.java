package io.inhyuck.webservice.domain.resume;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Data
public class UrlList {
    static final String urlRegex = "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    static final String HTTP = "http";

    private String originContents;
    private List<String> urlList;

    public UrlList(String originContents) {
        this.originContents = originContents;
        this.urlList = computeUrlList(originContents);
    }

    private List<String> computeUrlList(String originContents) {
        StringTokenizer tokenizer = new StringTokenizer(originContents);
        List<String> urlList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String item = tokenizer.nextToken();
            if (item.matches(urlRegex)) {
                if(!item.contains(HTTP)) {
                    urlList.add(new StringBuilder(HTTP).append("://").append(item).toString());
                    continue;
                }
                urlList.add(item);
            }
        }
        return urlList;
    }
}
