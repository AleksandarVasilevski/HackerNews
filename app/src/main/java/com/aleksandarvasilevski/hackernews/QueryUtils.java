package com.aleksandarvasilevski.hackernews;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving news data from Hacker News.
 */
public final class QueryUtils {

    /** Sample JSON response for a Hacker News query */
    private static final String SAMPLE_JSON_RESPONSE = "{\"status\":\"ok\",\"source\":\"hacker-news\",\"sortBy\":\"top\",\"articles\":[{\"author\":null,\"title\":\"Setting the Record Straight: containers vs. Zones vs. Jails vs. VMs\",\"description\":\"Design differences of containers, Zones, Jails and VMs.\",\"url\":\"https://blog.jessfraz.com/post/containers-zones-jails-vms/\",\"urlToImage\":\"/img/share.png\",\"publishedAt\":null},{\"author\":\"{        map[]}\",\"title\":\"Flex\",\"description\":\"Home page of the Flex team at YC Research HARC\",\"url\":\"https://harc.ycr.org/flex/\",\"urlToImage\":null,\"publishedAt\":null},{\"author\":\"Austin Cheney\",\"title\":\"Fastest diff algorithm – only 3 passes through the data with minimal logic\",\"description\":\"Pretty Diff tool can minify, beautify (pretty-print), or diff between minified and beautified code. This tool can even beautify and minify HTML.\",\"url\":\"http://prettydiff.com/overview.xhtml\",\"urlToImage\":null,\"publishedAt\":null},{\"author\":\"\",\"title\":\"Vivaldi Makes History\",\"description\":\"Introducing the new Vivaldi History – a powerful tool that lets you explore your browsing habits and finding previously visited web pages like never before.\",\"url\":\"https://vivaldi.com/blog/vivaldi-makes-history/\",\"urlToImage\":\"https://vivaldi.com/wp-content/uploads/2017/03/vivaldi-1.8-history-1.jpg\",\"publishedAt\":\"2017-03-29T09:26:31Z\"},{\"author\":null,\"title\":\"M. I. Jordan: An Introduction to Probabilistic Graphical Models\",\"description\":null,\"url\":\"https://people.eecs.berkeley.edu/~jordan/prelims/\",\"urlToImage\":null,\"publishedAt\":null},{\"author\":null,\"title\":\"f.lux vs. Night Shift in macOS 10.12.4\",\"description\":\"I have two things to say about Apple's copy of f.lux. #1 Amount Night Shift's defaults are pretty gentle, and for most people they won't reduce the impact of a screen by very much. Here's what Night Shift does before bedtime:  And here's what f.lux does b...\",\"url\":\"https://forum.justgetflux.com/topic/3655/f-lux-vs-night-shift-in-macos-10-12-4/8\",\"urlToImage\":\"https://forum.justgetflux.com/uploads/profile/3-profileimg.png\",\"publishedAt\":\"2017-03-29T02:13:56.889Z\"},{\"author\":null,\"title\":\"How to write Common Lisp in 2017 – an initiation manual\",\"description\":null,\"url\":\"http://articulate-lisp.com\",\"urlToImage\":null,\"publishedAt\":null},{\"author\":\"Kyle Halladay\",\"title\":\"Getting started with gameboy advance development\",\"description\":\"I build shaders, renderers, games, and other stuff that's fun to stare at.\",\"url\":\"http://kylehalladay.com/blog/tutorial/2017/03/28/GBA-By-Example-1.html\",\"urlToImage\":null,\"publishedAt\":null},{\"author\":null,\"title\":\"Deep Learning: mathematics and neuroscience [pdf]\",\"description\":null,\"url\":\"https://cbmm.mit.edu/sites/default/files/publications/Deep%20Learning-%20mathematics%20and%20neuroscience.pdf\",\"urlToImage\":null,\"publishedAt\":null},{\"author\":null,\"title\":\"Beej's Guide to Network Programming\",\"description\":null,\"url\":\"http://beej.us/guide/bgnet/\",\"urlToImage\":null,\"publishedAt\":null}]}";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<News> extractNewsList() {

        // Create an empty ArrayList that we can start adding news to
        ArrayList<News> newsList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray newsArray = baseJsonResponse.getJSONArray("articles");

            for (int i = 0; i < newsArray.length(); i++) {
                // All of code in the loop
                JSONObject currentNews = newsArray.getJSONObject(i);
                String title = currentNews.getString("title");
                String description = currentNews.getString("description");

                News news = new News(title, description);
                newsList.add(news);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of news
        return newsList;
    }

}