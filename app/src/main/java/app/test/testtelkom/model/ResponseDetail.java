package app.test.testtelkom.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ResponseDetail {

    @SerializedName("score")
    private int score;

    @SerializedName("by")
    private String by;

    @SerializedName("id")
    private int id;

    @SerializedName("time")
    private int time;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("descendants")
    private int descendants;

    @SerializedName("url")
    private String url;

    @SerializedName("kids")
    private List<Long> kids;
}