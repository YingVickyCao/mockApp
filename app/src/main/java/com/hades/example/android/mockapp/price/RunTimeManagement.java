package com.hades.example.android.mockapp.price;


import com.google.common.base.Optional;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public enum RunTimeManagement {
    INSTANCE;

    private volatile Optional<ISessionData> mSession1Data = Optional.absent();
    private volatile Optional<ISessionData> mSession2Data = Optional.absent();

    private final BehaviorSubject<Optional<ISessionData>> mSession1DataSubject;
    private final BehaviorSubject<Optional<ISessionData>> mSession2DataSubject;

    private final Observable<Optional<? extends ISessionData>> mSession1DataObservable;
    private final Observable<Optional<? extends ISessionData>> mSession2DataObservable;


    RunTimeManagement() {
        mSession1DataSubject = BehaviorSubject.createDefault(Optional.<ISessionData>absent());
        mSession2DataSubject = BehaviorSubject.createDefault(Optional.<ISessionData>absent());

        final Object object = mSession1DataSubject.distinctUntilChanged();
        mSession1DataObservable = (Observable<Optional<? extends ISessionData>>) object;

        final Object object2 = mSession1DataSubject.distinctUntilChanged();
        mSession2DataObservable = (Observable<Optional<? extends ISessionData>>) object2;
    }

    public void newSession1(String user) {
        setSession1Data(Optional.of(SessionData.create(user)));
    }

    public void newSession2(String user) {
        setSession2Data(Optional.of(SessionData.create(user)));
    }

    public void setSession1Data(final Optional<ISessionData> session) {
        mSession1Data = session;
        mSession1DataSubject.onNext(session);
    }

    public void setSession2Data(final Optional<ISessionData> session) {
        mSession2Data = session;
        mSession2DataSubject.onNext(session);
    }

    public void clearSession1() {
        setSession1Data(Optional.<ISessionData>absent());
    }

    public void clearSession2() {
        setSession2Data(Optional.<ISessionData>absent());
    }


    public Optional<ISessionData> getSession1Data() {
        return mSession1Data;
    }

    public Optional<ISessionData> getSession2Data() {
        return mSession2Data;
    }

    public Observable<Optional<? extends ISessionData>> getSession1DataObservable() {
        return mSession1DataObservable;
    }

    public Observable<Optional<? extends ISessionData>> getSession2DataObservable() {
        return mSession2DataObservable;
    }
}
