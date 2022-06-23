package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api

import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.*
import com.moufans.lib_base.request.BaseResp
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppURLConstants
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface AppApi {

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST(AppURLConstants.ABILITY_END_POLITICAL_JUNE)
    suspend fun getVerifyCode(@FieldMap map: Map<String, String>): BaseResp<GetCodeDataBean>

    /**
     * 登录注册
     */
    @FormUrlEncoded
    @POST(AppURLConstants.ABILITY_GATHER_HOPELESS_PRESCRIPTION)
    suspend fun login(@FieldMap map: Map<String, String>): BaseResp<UserDataBean>

    /**
     * 首页
     */
    @FormUrlEncoded
    @POST(AppURLConstants.VARIOUS_IRRIGATION_PRESENT_ASHAMED_PARDON)
    suspend fun homeStatus(@FieldMap map: Map<String, String>): BaseResp<HomeStatusDataBean>

    /**
     * 首页-banner
     */
    @FormUrlEncoded
    @POST(AppURLConstants.NATURAL_NUT_SEIZE_SOUTHERN_STONE)
    suspend fun homeBanner(@FieldMap map: Map<String, String>): BaseResp<HomeBannerDataBean>

    /**
     * 性别
     */
    @FormUrlEncoded
    @POST(AppURLConstants.VARIOUS_IRRIGATION_FORECAST_BOUND_SUNDAY)
    suspend fun sex(@FieldMap map: Map<String, String>): BaseResp<List<SexDataBean>>

    /**
     * 省市
     */
    @FormUrlEncoded
    @POST(AppURLConstants.VARIOUS_IRRIGATION_DRY_SURROUNDING_MESS)
    suspend fun provinceCity(@FieldMap map: Map<String, String>): BaseResp<List<AddressDataBean>>

    @FormUrlEncoded
    @POST(AppURLConstants.NATURAL_NUT_COMMAND_UNFAIR_DATA)
    suspend fun commandUnfairData(@FieldMap map: Map<String, String>): BaseResp<Unit>

    @FormUrlEncoded
    @POST(AppURLConstants.NATURAL_NUT_LOSE_CROWDED_MEANING)
    suspend fun loseCrowdedMeaning(@FieldMap map: Map<String, String>): BaseResp<Unit>

    @POST(AppURLConstants.NATURAL_NUT_ENCOURAGE_AUSTRALIAN_BROOMS)
    suspend fun encourageAustralianBrooms(@Body multipartBody: MultipartBody): BaseResp<Unit>

    @POST(AppURLConstants.BLUE_IRELAND_IMPRESS_LAZY_BEING)
    suspend fun impressLazyBeing(@Body requestBody: RequestBody): BaseResp<Unit>

    @FormUrlEncoded
    @POST(AppURLConstants.POT_MASK_SOUTH_SHADOW)
    suspend fun maskSouthShadow(@FieldMap map: Map<String, String>): BaseResp<MaskSouthShadowDataBean>

    @FormUrlEncoded
    @POST(AppURLConstants.POT_DREAM_PHYSICAL_RELATIONSHIP)
    suspend fun dreamPhysicalRelationship(@FieldMap map: Map<String, String>): BaseResp<DreamPhysicalRelationshipDataBean>

    @FormUrlEncoded
    @POST(AppURLConstants.CHALLENGING_ATMOSPHERE_INTEND_SOCIAL_CANADA)
    suspend fun intendSocialCanada(@FieldMap map: Map<String, String>): BaseResp<DreamPhysicalRelationshipDataBean>

    @FormUrlEncoded
    @POST(AppURLConstants.CHALLENGING_ATMOSPHERE_RACE_HUGE_STEWARD)
    suspend fun raceHugeSteward(@FieldMap map: Map<String, String>): BaseResp<DreamPhysicalRelationshipDataBean>

    @FormUrlEncoded
    @POST(AppURLConstants.VARIOUS_IRRIGATION_RELAY_FOND_SHORT)
    suspend fun appInfo(@FieldMap map: Map<String, String>): BaseResp<AppInfoDataBean>

    @FormUrlEncoded
    @POST(AppURLConstants.CHALLENGING_ATMOSPHERE_BAN_LATEST_BRIDE)
    suspend fun banLatestBride(@FieldMap map: Map<String, String>): BaseResp<String>

    @FormUrlEncoded
    @POST(AppURLConstants.NATURAL_NUT_BEG_RICH_BIT)
    suspend fun begRichBit(@FieldMap map: Map<String, String>): BaseResp<MeDataBean>

}

