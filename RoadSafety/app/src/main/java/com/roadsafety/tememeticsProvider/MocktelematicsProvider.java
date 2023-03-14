package com.roadsafety.tememeticsProvider;

import android.telecom.Call;

import java.util.ArrayList;
import java.util.List;

public class MocktelematicsProvider implements ITelemeticsProvider {

    @Override
    public void ValidatePermissons() {


    }

    @Override
    public boolean enableSdk() {
        return false;
    }

    @Override
    public void disableSdk() {

    }

    @Override
    public boolean startTracking() {
        return false;
    }

    @Override
    public boolean stopTracking() {
        return false;
    }

    @Override
    public boolean isTracking() {
        return false;
    }

    @Override
    public void apiScoreParameters(Callback<int[]> callback) {
        callback.Success(new int[]{100, 100, 58, 73});
    }

    @Override
    public void apiFinalScore(Callback<Integer> callback) {
        callback.Success(79);
    }

    @Override
    public List<ScoreModel> apiGetScores() {
        List<ScoreModel> scoreModelList = new ArrayList<>();
        scoreModelList.add(new ScoreModel("1", "Amita Singh", "99"));
        scoreModelList.add(new ScoreModel("2", "Ankit Ajmera", "98"));
        scoreModelList.add(new ScoreModel("3", "Ritu Jacob", "97"));
        scoreModelList.add(new ScoreModel("4", "Jignesh Thakkar", "97"));
        scoreModelList.add(new ScoreModel("5", "Sakshi", "96"));
        scoreModelList.add(new ScoreModel("6", "Anirudh Agarwal", "95"));
        scoreModelList.add(new ScoreModel("7", "Nitin Sethi", "95"));
        scoreModelList.add(new ScoreModel("8", "Arqum Mateen", "94"));
        scoreModelList.add(new ScoreModel("9", "Avneesh Walker", "93"));
        scoreModelList.add(new ScoreModel("10", "Ankit Agarwal", "91"));
        return scoreModelList;
    }

    @Override
    public List<ScoreModel> apiGetImprovedScore(){
        List<ScoreModel> scoreModelList = new ArrayList<>();
        scoreModelList.add(new ScoreModel("1", "MS Kohli", "90"));
        scoreModelList.add(new ScoreModel("2", "Virat Sharma", "85"));
        scoreModelList.add(new ScoreModel("3", "Kabir Oberoi", "80"));
        return scoreModelList;

    }

    @Override
    public void apiGetReportParams(Callback<int[]> callback) {
        callback.Success(new int[]{20,60,5,5,15,12000});
    }

    @Override
    public String apiGetQuickTips() {
        return "Too much over speeding";
    }

    @Override
    public List<List<String>> apiGetTripList(String userid) {
        return null;
    }

    @Override
    public List<List<String>> apiGetTripDetails(String userid, int tripid) {
        return null;
    }

    @Override
    public String[] getUserDetails() {
        return new String[0];
    }

    @Override
    public void setUserDetails(String id, String name, String email, String address) {

    }
}
