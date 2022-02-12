import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class JsonRequest {
    public final String copyright;
    public final String date;
    public final String explanation;
    public final String hdurl;
    public final String media_type;
    public final String service_version;
    public final String title;
    public final String url;

    public JsonRequest(@JsonProperty("copyright") String copyright,
                       @JsonProperty("date") String date,
                       @JsonProperty("explanation") String explanation,
                       @JsonProperty("hdurl") String hdurl,
                       @JsonProperty("media_type") String media_type,
                       @JsonProperty("service_version") String service_version,
                       @JsonProperty("title") String title,
                       @JsonProperty("url") String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "\nJsonRequest" +
                "\ncopyright=" + copyright +
                "\ndate='" + date + '\'' +
                "\nexplanation='" + explanation + '\'' +
                "\nhdurl='" + hdurl + '\'' +
                "\nmedia_type=" + media_type + '\'' +
                "\nservice_version=" + service_version + '\'' +
                "\ntitle=" + title + '\'' +
                "\nurl=" + url;

    }
}
