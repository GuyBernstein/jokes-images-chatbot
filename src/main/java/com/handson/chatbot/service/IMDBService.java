package com.handson.chatbot.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IMDBService {

    public String searchMovies(String keyword) {
        return "Searched for:" + keyword;
    }

    private String getProductHtml(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://www.imdb.com/chart/top/?ref_=nv_mv_250")
                .method("GET", body)
                .addHeader("authority", "www.imdb.com")
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("accept-language", "en-US,en;q=0.9")
                .addHeader("cache-control", "max-age=0")
                .addHeader("cookie", "session-id=139-6828648-4111061; session-id-time=2082787201l; ubid-main=132-4313314-1018035; ad-oo=0; ci=e30; __gads=ID=a9db086099168dde:T=1728374060:RT=1728374060:S=ALNI_MbDwGtVdIDiAllx5dSQ2wx6ZKypEQ; __gpi=UID=00000f377cefbc9b:T=1728374060:RT=1728374060:S=ALNI_MayO1y27RdEm9h4n48y3sK2fRvJZA; __eoi=ID=521d1342fe156d46:T=1728374060:RT=1728374060:S=AA-AfjaOKuV6yHth9upvoPEyQ5Qv; _cc_id=48b559608a6f8a3ec59156d4499c6e58; panoramaId_expiry=1728978864845; panoramaId=a07c5c74aa2100a2dd76b44fe924185ca02c6da12572c501a270170341c3474b; panoramaIdType=panoDevice; session-token=9sAmaT+Dop0lkHFzkLd1sjHkk1G1PrIyadMAdncEN8qoFLt4P7RJ8DYgdnoTWe2bSuk+3i3zHgNCMtT4qBvWkwvPXkxrf7O0cj/xalYt4i04nHd0NYrLJjvZ/w2cvfHtLq0ZubQlZKGrtu6NnF4/5ZNz3cH7SMk3XJDI0JThU+7ILPrOe23KSY9ODxJAXToIB7wKKULRtGCSBf15Zd0PkW+ud9RCVmvnBpaALQ5C2KmZeWtdB6dRW8y61D44QSpx5/EViOVGZ1eFMpO2CUppecNt+S3Grw/PCo3wjETcPwr9uOKUqnhBi1rn0ggJ3iW9cbWfRLOuaduH7CNgLFigy4DoKycGntdJ; _au_1d=AU1D-0100-001728374069-X4Q3XKHU-HR56; _gid=GA1.2.1046025606.1728374071; _ga_FVWZ0RM4DH=GS1.1.1728374089.1.1.1728374114.35.0.0; csm-hit=tb:9TE1PG5ZTGE69KXKPZJC+s-VZZW36AH0GV1PBM7CC7M|1728374120905&t:1728374120905&adb:adblk_no; _ga=GA1.2.710268946.1728374071")
                .addHeader("referer", "https://www.imdb.com/")
                .addHeader("sec-ch-ua", "\"Not_A Brand\";v=\"99\", \"Google Chrome\";v=\"109\", \"Chromium\";v=\"109\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Linux\"")
                .addHeader("sec-fetch-dest", "document")
                .addHeader("sec-fetch-mode", "navigate")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-user", "?1")
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}