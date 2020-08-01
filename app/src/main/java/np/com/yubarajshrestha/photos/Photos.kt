package np.com.yubarajshrestha.photos

import android.os.Parcel
import android.os.Parcelable

data class Photos(val name: String, val profileImage: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(profileImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photos> {
        override fun createFromParcel(parcel: Parcel): Photos {
            return Photos(parcel)
        }

        override fun newArray(size: Int): Array<Photos?> {
            return arrayOfNulls(size)
        }
    }
}
