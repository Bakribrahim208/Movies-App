package com.example.usersfiles.moveapp.links;

/**
 * Created by UsersFiles on 8/8/2016.
 */
public class Links_class {
    private static final String website="http://api.themoviedb.org/3/movie/";
    private static final String getImgae="http://image.tmdb.org/t/p/w185/";

    public static String getYoutube() {
        return youtube;
    }

    private static final String youtube="https://www.youtube.com/watch?v=";

    public static String getGetvideo() {
        return getvideo;
    }

    private static final String getvideo="/videos?api_key=777660159186d81259c9dcfa910ad0f1";

    public static String getWebsite() {
        return website;
    }

    public static String getGetImgae() {
        return getImgae;
    }

    public static String getReview() {
        return Review;
    }

    private static final String  Review  ="/reviews?api_key=777660159186d81259c9dcfa910ad0f1";


    public static String getGetTOpRatePath() {
        return getTOpRatePath;
    }

    private static final String getTOpRatePath="http://api.themoviedb.org/3/movie/top_rated?api_key=777660159186d81259c9dcfa910ad0f1";

    public static String getPopularMovePath() {
        return PopularMovePath;
    }

    private static  final  String PopularMovePath="http://api.themoviedb.org/3/movie/popular?api_key=777660159186d81259c9dcfa910ad0f1";
}
