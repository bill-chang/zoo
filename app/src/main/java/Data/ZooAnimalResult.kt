package Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZooAnimalResult (
    val result: AnimalResultData
)

@Serializable
data class AnimalResultData (
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: Int,
    val resultList: List<AnimalResultItem>,
)

@Serializable
data class AnimalResultItem(
    @SerialName("_id")
    val id: String,
    @SerialName("a_name_ch")
    val animalId: String,
    @SerialName("a_summary")
    val animalKind: String,
    @SerialName("a_keywords")
    val animalSex: String,
    @SerialName("a_alsoknown")
    val animalAge: String,
    @SerialName("a_geo")
    val aGeo: String,
    @SerialName("a_location")
    val aLocation: String,
    @SerialName("a_poigroup")
    val aPoiGroup: String,
    @SerialName("a_name_en")
    val aNameEn: String,
    @SerialName("a_name_latin")
    val aNameLatin: String,
    @SerialName("a_phylum")
    val aPhylum: String,
    @SerialName("a_class")
    val aClass: String,
    @SerialName("a_order")
    val aOrder: String,
    @SerialName("a_family")
    val aFamily: String,
    @SerialName("a_conservation")
    val aConservation: String,
    @SerialName("a_distribution")
    val aDistribution: String,
    @SerialName("a_habitat")
    val aHabitAt: String,
    @SerialName("a_feature")
    val aFeature: String,
    @SerialName("a_behavior")
    val aBehavior: String,
    @SerialName("a_diet")
    val aDiet: String,
    @SerialName("a_crisis")
    val aCrisis: String,
    @SerialName("a_interpretation")
    val aInterpretation: String,
    @SerialName("a_theme_name")
    val aThemeName: String,
    @SerialName("a_theme_url")
    val aThemeUrl: String,
    @SerialName("a_adopt")
    val aAdopt: String,
    @SerialName("a_code")
    val aCode: String,
    @SerialName("a_pic01_alt")
    val aPic01Alt: String,
    @SerialName("a_pic01_url")
    val aPic01Url: String,
    @SerialName("a_pic02_alt")
    val aPic02Alt: String,
    @SerialName("a_pic02_url")
    val aPic02Url: String,
    @SerialName("a_pic03_alt")
    val aPic03Alt: String,
    @SerialName("a_pic03_url")
    val aPic03url: String,
    @SerialName("a_pic04_alt")
    val aPic04Url: String,
    @SerialName("a_pdf01_alt")
    val aPdf01Alt: String,
    @SerialName("a_pdf01_url")
    val aPdf01Url: String,
    @SerialName("a_pdf02_alt")
    val aPdf02Alt: String,
    @SerialName("a_pdf02_url")
    val aPdf02Url: String,
    @SerialName("a_voice01_alt")
    val aVoice01Alt: String,
    @SerialName("a_voice01_url")
    val aVoice01Url: String,
    @SerialName("a_voice02_alt")
    val aVoice02Alt: String,
    @SerialName("a_voice02_url")
    val aVoice02Url: String,
    @SerialName("a_voice03_alt")
    val aVoice03Alt: String,
    @SerialName("a_voice03_url")
    val aVoice03Url: String,
    @SerialName("a_vedio_url")
    val aVedioUrl: String,
    @SerialName("a_update")
    val aUpdate: String,
    @SerialName("a_cid")
    val aCid: String,
)