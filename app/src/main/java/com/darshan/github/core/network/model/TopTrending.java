package com.darshan.github.core.network.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class TopTrending implements Parcelable {

    @AutoValue
    public static abstract class User implements Parcelable {

        public static TypeAdapter<User> typeAdapter(Gson gson) {
            return new $AutoValue_TopTrending_User.GsonTypeAdapter(gson);
        }

        @SerializedName("username")
        public abstract String getUsername();

        @SerializedName("name")
        public abstract String getName();

        @SerializedName("type")
        public abstract String getType();

        @SerializedName("url")
        public abstract String getURL();

        @SerializedName("avatar")
        public abstract String getAvatar();

        @SerializedName("repo")
        public abstract Repo getRepo();
    }

    @AutoValue
    public static abstract class Repo implements Parcelable {

        public static TypeAdapter<Repo> typeAdapter(Gson gson) {
            return new $AutoValue_TopTrending_Repo.GsonTypeAdapter(gson);
        }

        @SerializedName("name")
        public abstract String getName();

        @SerializedName("description")
        public abstract String getDescription();

        @SerializedName("url")
        public abstract String getURL();

    }

}
