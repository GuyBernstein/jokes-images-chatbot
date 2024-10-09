package com.handson.chatbot.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class IMDBService {
    public String searchMovies(String keyword) throws UnirestException {
        List<Movie> movies = parseProductHtml();

        // Using lambda to create a string with name, year, and rating
        return movies.stream()
                .filter(movie -> movie.getName().toLowerCase().contains(keyword.toLowerCase()))
                .findFirst()
                .map(movie -> String.format("%s (%s) - Rating: %s",
                        movie.getName(),
                        movie.getInfo(),
                        movie.getRating()))
                .orElse("Movie not found");
    }

    private List<Movie> parseProductHtml() throws UnirestException {

        Document doc = Jsoup.parse(getProductHtml());
        Elements items = doc.select("li.cli-parent");

        List<Movie> movies = new ArrayList<>();


        for (Element item : items) {
            Element head = item.selectFirst("h3.ipc-title__text");
            Element rating = item.selectFirst("span.ratingGroup--imdb-rating");
            Elements infos = item.select("span.cli-title-metadata-item");

            String infoStr = infos.stream()
                    .map(Element::text)
                    .collect(Collectors.joining(" "));

            assert head != null;
            assert rating != null;
            movies.add(new Movie(head.text(), infoStr, rating.text()));
        }
        return movies;
    }

    private String getProductHtml() throws  UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://www.imdb.com/chart/top/?ref_=nv_mv_250")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8")
                .header("accept-language", "he-IL,he;q=0.7")
                .header("cache-control", "max-age=0")
                .header("cookie", "session-id=132-0389847-3081906; session-id-time=2082787201l; ad-oo=0; gpc-cache=1; ci=e30; ubid-main=135-8056317-7686431; session-token=vvGwmK8Vjxz1ynHK3x6hiK2AeKIZxfB0CUlrDpZJb2z8Yxk8M1lEARZCWbgy4oqsCR1R4iDo+mGn2C3SwPSJM2oJl8tfq0gaJLeaeV7yVDj8InPcSO+/+a9wqxzi+NhRwPKRDZyDPAs1cw2s0OE8mFW8cIR6Gs8eQiOKtD+zJNDWede7LTAnABen7P/RQ4qbTy2WEmkgtHyJEXswXekzrWtiP6czG+/3oUnqW4gAKO24x2BiGy4TuHT5jLWpETjgZsOHHw0Haw4bkOOS4MnDZ/D2QAZnAyaPOuFo0tQhV/viwmNUyYTxjC0FlcqeXUOdt/2aB4JScxPGytpJJzWS+0EErvHk2LiS; csm-hit=tb:s-81R7CPSVJ87RMN81N4KS|1728387312350&t:1728387313209&adb:adblk_yes; session-id=132-0389847-3081906; session-id-time=2082787201l; session-token=lXoLr59FUV69zhSECoYan/rgNDAfM2/4OkleP9gcaaY7EHQoDv2AWXnLD+7jfIWhQobadH+28N6E7l53I5EXRf+TsJZ48WkVzlvtFFHmXxB9Mg6AkzV1aWP5FKpRejrQBrpcZPMSg+qza5i0d7RSYlzeeNUdMAUY20w+9EQkXqIHchd9MCJr5JrIweuBdCQoCrUgbL/8exDxGqHJrdCJEXfBK8cOeTmuFZCLmd0LwTi2OG06A0YP1UiAz1sSyOmoMzQY2u8gZqvMCIH3lh/6mag4n8Qbls2j/a/QN/iYGlhuqCMaFYyfwInT0KyzmevIoWaMm7gXIkxTaHXHCel65k4TvmLbQANj")
                .header("priority", "u=0, i")
                .header("sec-ch-ua", "\"Brave\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"macOS\"")
                .header("sec-fetch-dest", "document")
                .header("sec-fetch-mode", "navigate")
                .header("sec-fetch-site", "same-origin")
                .header("sec-fetch-user", "?1")
                .header("sec-gpc", "1")
                .header("upgrade-insecure-requests", "1")
                .header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36")
                .asString();
        return response.getBody();
    }

    private static class Movie {
        private final String name;
        private final String info;
        private final String rating;

        public Movie(String name, String info, String rating) {
            this.name = name;
            this.info = info;
            this.rating = rating;
        }

        public String getName() {
            return name;
        }

        public String getInfo() {
            return info;
        }

        public String getRating() {
            return rating;
        }
    }
}