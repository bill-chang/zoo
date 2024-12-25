package Data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ZooAnimalResult (
    val result: AnimalResultData ?= AnimalResultData()
)

@Serializable
data class AnimalResultData (
    val limit: String ?= "",
    val offset: String ?= "",
    val count: String ?= "",
    val sort: String ?= "",
    val results: List<AnimalResultItem> ?= emptyList(),
)

@Serializable
data class AnimalResultItem(
    @SerializedName("_id")
    val id: String ?= "",
    @SerializedName("_importdate")
    val importDate: DateInfoList ?= DateInfoList(),
    @SerializedName("a_name_ch")
    val aNameCh: String ?= "",
    @SerializedName("a_summary")
    val aSummary: String ?= "",
    @SerializedName("a_keywords")
    val aKeyword: String ?= "",
    @SerializedName("a_alsoknown")
    val aAlsoKnown: String ?= "",
    @SerializedName("a_geo")
    val aGeo: String ?= "",
    @SerializedName("a_location")
    val aLocation: String ?= "",
    @SerializedName("a_poigroup")
    val aPoiGroup: String ?= "",
    @SerializedName("a_name_en")
    val aNameEn: String ?= "",
    @SerializedName("a_name_latin")
    val aNameLatin: String ?= "",
    @SerializedName("a_phylum")
    val aPhylum: String ?= "",
    @SerializedName("a_class")
    val aClass: String ?= "",
    @SerializedName("a_order")
    val aOrder: String ?= "",
    @SerializedName("a_family")
    val aFamily: String ?= "",
    @SerializedName("a_conservation")
    val aConservation: String ?= "",
    @SerializedName("a_distribution")
    val aDistribution: String ?= "",
    @SerializedName("a_habitat")
    val aHabitAt: String ?= "",
    @SerializedName("a_feature")
    val aFeature: String ?= "",
    @SerializedName("a_behavior")
    val aBehavior: String ?= "",
    @SerializedName("a_diet")
    val aDiet: String ?= "",
    @SerializedName("a_crisis")
    val aCrisis: String ?= "",
    @SerializedName("a_interpretation")
    val aInterpretation: String ?= "",
    @SerializedName("a_theme_name")
    val aThemeName: String ?= "",
    @SerializedName("a_theme_url")
    val aThemeUrl: String ?= "",
    @SerializedName("a_adopt")
    val aAdopt: String ?= "",
    @SerializedName("a_code")
    val aCode: String ?= "",
    @SerializedName("a_pic01_alt")
    val aPic01Alt: String ?= "",
    @SerializedName("a_pic01_url")
    val aPic01Url: String ?= "",
    @SerializedName("a_pic02_alt")
    val aPic02Alt: String ?= "",
    @SerializedName("a_pic02_url")
    val aPic02Url: String ?= "",
    @SerializedName("a_pic03_alt")
    val aPic03Alt: String ?= "",
    @SerializedName("a_pic03_url")
    val aPic03url: String ?= "",
    @SerializedName("a_pic04_alt")
    val aPic04Alt: String ?= "",
    @SerializedName("a_pic04_url")
    val aPic04Url: String ?= "",
    @SerializedName("a_pdf01_alt")
    val aPdf01Alt: String ?= "",
    @SerializedName("a_pdf01_url")
    val aPdf01Url: String ?= "",
    @SerializedName("a_pdf02_alt")
    val aPdf02Alt: String ?= "",
    @SerializedName("a_pdf02_url")
    val aPdf02Url: String ?= "",
    @SerializedName("a_voice01_alt")
    val aVoice01Alt: String ?= "",
    @SerializedName("a_voice01_url")
    val aVoice01Url: String ?= "",
    @SerializedName("a_voice02_alt")
    val aVoice02Alt: String ?= "",
    @SerializedName("a_voice02_url")
    val aVoice02Url: String ?= "",
    @SerializedName("a_voice03_alt")
    val aVoice03Alt: String ?= "",
    @SerializedName("a_voice03_url")
    val aVoice03Url: String ?= "",
    @SerializedName("a_vedio_url")
    val aVedioUrl: String ?= "",
    @SerializedName("a_update")
    val aUpdate: String ?= "",
    @SerializedName("a_cid")
    val aCid: String ?= "",
)