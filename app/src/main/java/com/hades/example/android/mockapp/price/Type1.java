package com.hades.example.android.mockapp.price;


import android.util.Log;

import com.google.common.base.Optional;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Type1 implements IType {
    private static final String TAG = Type1.class.getSimpleName();

    public void getSessionToken() {
        RunTimeManagement.INSTANCE.getSession1DataObservable()
                .distinctUntilChanged()
                .doOnNext(sessionData -> {
                    Log.d(TAG, "getSessionToken: " + sessionData);
                }).flatMap((Function<Optional<? extends ISessionData>, Observable<Optional<? extends ISessionData>>>) sessionData ->
                sessionData.isPresent() ? Observable.just(sessionData) : refreshSession());
    }

    public void clearSessionData() {
        RunTimeManagement.INSTANCE.clearSession1();
    }

    private Observable<Optional<? extends ISessionData>> refreshSession() {
        return Observable.just("A").take(1)
                .doOnSubscribe(disposable -> {
                    Log.d(TAG, "refreshSession: ");
                })
                .doOnError(e -> {

                })
                .map((Function<String, Optional<? extends ISessionData>>) this::newToken)
                .onErrorReturn(throwable -> {
                    return Optional.absent();
                });
    }

    private Optional<? extends ISessionData> newToken(String usr) {
        RunTimeManagement.INSTANCE.newSession1(usr);
        return RunTimeManagement.INSTANCE.getSession1Data();
    }
}
