package com.roadsafety.tememeticsProvider;

import java.util.List;

public interface ITelemeticsProvider
{
    void ValidatePermissons();
    boolean enableSdk();
    void disableSdk();


    //home fragment
    boolean startTracking();
    boolean stopTracking();
    boolean isTracking();

    //Example:
    //    ret[0] = 100; breaking
    //    ret[1] = 100; accelerating
    //    ret[2] = 52;  speed
    //    ret[3] = 48;  phonediscipline
    void apiScoreParameters(Callback<int[]> callback); // integer array of 4 values breaking, acceleration, speed, phone distractions
    void apiFinalScore(Callback<Integer> callback); // final score after applying the algorithm



    //leaderboard fragment
    // ScoreModel is class which contains 3 values string rank, string name, string score of one driver
    // apiGetScore() is the top part of leaderboard page
    List<ScoreModel> apiGetScores();
    // apiGetimprovedScore is the bottom part of the leaderboard page
    List<ScoreModel> apiGetImprovedScore();




    //Dashboard fragment
    //    Example of api parameters
    //    params[0] = 20;     // city speed
    //    params[1] = 60;    // highway speed
    //    params[2] = 5;      // city limit
    //    params[3] = 5;      // highway limit
    //    params[4] = 15;     // phone distractions
    //    params[5] = 12000;  // fines hardcode
    void apiGetReportParams(Callback<int[]> callback); // params description as shown above
    String apiGetQuickTips(); //insights for the user

    //rewards & paychallan use hardcoded values

    //Trips page
    // need to be updated
    //[0] : first location name
    //[0][0] : start point of 1st location
    //[0][1] : end point of 1st location
    //[1][0] : start point of 2nd location
    //[1][1] : end point of 2nd location
    List<List<String>> apiGetTripList(String userid);  // show list of trips for a particular user

    //[0] : start location [1]: end location
    //[0] : first location coordinate
    //[0][0] : latitude of 1st location
    //[0][1] : longitude of 1st location
    //[1][0] : latitude of 1st location
    //[1][1] : longitude of 2nd location
    List<List<String>> apiGetTripDetails(String userid, int tripid);   //show trip details coordinates for a particular trip of a particular user




    //profile page
    String[] getUserDetails();
    void setUserDetails(String id, String name, String email, String address); // set and create
}
