package d4.deadline;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tomi on 5.4.2017.
 */

public class MyObject implements Parcelable {
    String color;
    String number;

    public MyObject(String number, String color) {
        this.color = color;
        this.number = number;
    }

    private MyObject(Parcel in) {
        color = in.readString();
        number = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(color);
        dest.writeString(number);

    }

    @Override
    public String toString() {
        return number + ": " + color;
    }

    public static final Parcelable.Creator<MyObject> CREATOR = new Parcelable.Creator<MyObject>() {
        public MyObject createFromParcel(Parcel in) {
            return new MyObject(in);
        }

        public MyObject[] newArray(int size) {
            return new MyObject[size];
        }
    };
}