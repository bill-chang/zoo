package Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZooBody(
    val ddd: String,
)

@Serializable
data class ZooResult(
    val result: ZooResultData
)

@Serializable
data class ZooResultData(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<ZooResultItem>
)

@Serializable
data class ZooResultItem(
    @SerialName("_id")
    val id: ZooResultData,
    @SerialName("e_no")
    val eNo: String,
    @SerialName("e_category")
    val eCategory: ZooResultData,
    @SerialName("e_name")
    val eName: String,
    @SerialName("e_pic_url")
    val ePicUrl: ZooResultData,
    @SerialName("e_info")
    val eInfo: String,
    @SerialName("e_memo")
    val eMemo: ZooResultData,
    @SerialName("e_geo")
    val eGeo: String,
    @SerialName("e_url")
    val eUrl: ZooResultData,
)