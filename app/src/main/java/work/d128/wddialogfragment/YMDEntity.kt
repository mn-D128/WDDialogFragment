package work.d128.wddialogfragment

import android.os.Parcel
import android.os.Parcelable

@Suppress("MemberVisibilityCanBePrivate")
class YMDEntity(val year: Int, val month: Int, val dayOfMonth: Int) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readInt(), parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(year)
        parcel.writeInt(month)
        parcel.writeInt(dayOfMonth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<YMDEntity> {
        override fun createFromParcel(parcel: Parcel): YMDEntity {
            return YMDEntity(parcel)
        }

        override fun newArray(size: Int): Array<YMDEntity?> {
            return arrayOfNulls(size)
        }
    }

}