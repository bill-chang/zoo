package Data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZooResult(
    val result: ZooResultData ?= ZooResultData()
)

@Serializable
data class ZooResultData(
    val limit: Int = 0,
    val offset: Int = 0,
    val count: Int = 0,
    val sort: String? = "",
    val results: List<ZooResultItem> ?= emptyList()
)

@Serializable
data class ZooResultItem(
    @SerializedName("_id")
    val id: String ?= "",
    @SerializedName("_importdate")
    val importDate: DateInfoList,
    @SerializedName("e_no")
    val eNo: String ?= "",
    @SerializedName("e_category")
    val eCategory: String ?= "",
    @SerializedName("e_name")
    val eName: String ?= "",
    @SerializedName("e_pic_url")
    val ePicUrl: String ?= "",
    @SerializedName("e_info")
    val eInfo: String ?= "",
    @SerializedName("e_memo")
    val eMemo: String ?= "",
    @SerializedName("e_geo")
    val eGeo: String ?= "",
    @SerializedName("e_url")
    val eUrl: String ?= "",
)

@Serializable
data class DateInfoList(
    val date: String ?= "",
    @SerializedName("timezone_type")
    val timeZoneType: String ?= "",
    val timeZone: String ?= "",
)

@Serializable
data class Posts (
//    @SerializedName("userId")
    val userId: Int = 0,
//    @SerializedName("id")
    val id: Int = 0,
//    @SerializedName("title")
    val title: String? = null,
//    @SerializedName("body")
    val body: String? = null,
)