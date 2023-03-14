package com.roadsafety.tememeticsProvider;

import android.app.Activity;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.raxeltelematics.v2.sdk.TrackingApi;
import com.raxeltelematics.v2.sdk.server.model.Locale;
import com.raxeltelematics.v2.sdk.server.model.sdk.DashboardInfo;
import com.raxeltelematics.v2.sdk.server.model.sdk.Track;
import com.raxeltelematics.v2.sdk.utils.permissions.PermissionsDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ZenRoadTelemeticsProvider implements ITelemeticsProvider {
    Activity _activity;
    DashboardInfo dashboardInfo;
    private final TrackingApi trackingApi = TrackingApi.getInstance();
    public ZenRoadTelemeticsProvider(Activity activity, String deviceId) {
        this._activity = activity;
        trackingApi.setDeviceID(deviceId);
    }

    @Override
    public void ValidatePermissons() {
        if (!TrackingApi.getInstance().isAllRequiredPermissionsAndSensorsGranted()) {
            PermissionsDialogFragment permsFragment = PermissionsDialogFragment.Companion.newInstants("Permissions", "Validate permissions", true);
            permsFragment.setPermissionsGrantedListener(allPermsGranted -> Log.d("TAG", "PermissionsDialogFragment onGrantedStatus: " + allPermsGranted));
            permsFragment.show(((FragmentActivity) _activity).getSupportFragmentManager(), "telematics_permission_tag");
        } else {
            this.enableSdk();
            Toast.makeText(_activity, "All permissions are already granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean startTracking() {
        return trackingApi.startTracking();
    }

    @Override
    public boolean stopTracking() {
        return trackingApi.stopTracking();
    }

    @Override
    public boolean isTracking() {
        return trackingApi.isTracking();
    }

    @Override
    public void apiScoreParameters(Callback<int[]> callback) {
        getDashboardInfo(value -> callback.Success(new int[]{
                value.getMileageLevel(),
                value.getDrivingLevel(),
                value.getSpeedLevel(),
                value.getPhoneLevel()
        }));

        callback.Success(new int[]{100, 100, 58, 73});
    }

    void getDashboardInfo(Callback<DashboardInfo> callback){
        if(dashboardInfo != null){
            callback.Success(dashboardInfo);
            return;
        }

        Single.fromCallable(()->trackingApi.getDashboardInfo(null))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value->{
                    this.dashboardInfo = value;
                    callback.Success(value);
                });
    }

    @Override
    public void apiFinalScore(Callback<Integer> callback) {
        getDashboardInfo(value -> callback.Success(value.getRating()));
    }

    @Override
    public List<ScoreModel> apiGetScores() {
        return TelemeticsFactory.getMockTelematicsProvider().apiGetScores();
    }

    @Override
    public List<ScoreModel> apiGetImprovedScore() {
        return TelemeticsFactory.getMockTelematicsProvider().apiGetImprovedScore();
    }

    void getAllTracks(Callback<Track[]> callback){
        Single.fromCallable(() ->
                TrackingApi.getInstance().getTracks(Locale.EN, null, null, 0, 10)
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        value -> callback.Success(value)
                );
    }

    @Override
    public void apiGetReportParams(Callback<int[]> callback) {
        int[] r = {0,0,0,0,0,12000};
        getAllTracks(tracks -> {
            for (Track track: tracks) {

                r[0] += track.getDistance()/(tracks.length* track.getDuration()/60);// / (hours*tracks.length);
                r[1] += track.getDistance()/(tracks.length* track.getDuration()/60);// / (hours*tracks.length);
                r[2] += track.getAccelerationCount();
                r[3] += track.getDecelerationCount();
                r[4] += track.getPhoneUsage();
            }

            callback.Success(r);
        });
    }

    @Override
    public String apiGetQuickTips() {
        return TelemeticsFactory.getMockTelematicsProvider().apiGetQuickTips();
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

    @Override
    public boolean enableSdk() {
        if (!trackingApi.isSdkEnabled() && TrackingApi.getInstance().isAllRequiredPermissionsAndSensorsGranted()) {
            trackingApi.setEnableSdk(true);
            return true;
        }

        return false;
    }

    @Override
    public void disableSdk() {
        trackingApi.setEnableSdk(false);
    }
}
