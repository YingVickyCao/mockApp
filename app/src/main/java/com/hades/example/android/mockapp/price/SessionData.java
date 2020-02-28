package com.hades.example.android.mockapp.price;

public final class SessionData implements ISessionData {
    private String mUser;

    private SessionData(String user) {
        this.mUser = user;
    }

    public static SessionData create(final String user) {
        return new SessionData(user);
    }

    public String getUser() {
        return mUser;
    }

    protected void print() {
        toString();
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "mUser='" + mUser + '\'' +
                '}';
    }
}
