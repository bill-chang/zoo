package Data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZooBody(
    val ddd: String,
)

@Serializable
data class ZooResult(
    val result: ZooResultData = ZooResultData()
)

@Serializable
data class ZooResultData(
    val limit: Int = 0,
    val offset: Int = 0,
    val count: Int = 0,
    val sort: String? = "",
    val results: List<ZooResultItem> = emptyList()
)

@Serializable
data class ZooResultItem(
    @SerialName("_id")
    val id: String ?= "",
    @SerialName("e_no")
    val eNo: String ?= "",
    @SerialName("e_category")
    val eCategory: String ?= "",
    @SerialName("e_name")
    val eName: String ?= "",
    @SerialName("e_pic_url")
    val ePicUrl: String ?= "",
    @SerialName("e_info")
    val eInfo: String ?= "",
    @SerialName("e_memo")
    val eMemo: String ?= "",
    @SerialName("e_geo")
    val eGeo: String ?= "",
    @SerialName("e_url")
    val eUrl: String ?= "",
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