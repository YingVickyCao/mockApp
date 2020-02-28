package com.hades.example.android.mockapp.price;


import android.util.Log;

import com.google.common.base.Optional;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Type2 implements IType {
    private static final String TAG = Type2.class.getSimpleName();

    public void getSessionToken() {
        RunTimeManagement.INSTANCE.getSession2DataObservable()
                .distinctUntilChanged()
                .doOnNext(sessionData -> {
                    Log.d(TAG, "getSessionToken: " + sessionData);
                }).flatMap((Function<Optional<? extends ISessionData>, Observable<Optional<? extends ISessionData>>>) sessionData ->
                sessionData.isPresent() ? Observable.just(sessionData) : refreshSession2());
    }

    public void clearSessionData() {
        RunTimeManagement.INSTANCE.clearSession2();
    }

    private Observable<Optional<? extends ISessionData>> refreshSession2() {
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
        RunTimeManagement.INSTANCE.newSession2(usr);
        return RunTimeManagement.INSTANCE.getSession2Data();
    }
}
