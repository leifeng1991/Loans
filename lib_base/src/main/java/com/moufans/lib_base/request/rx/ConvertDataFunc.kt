package com.moufans.lib_base.request.rx

import com.moufans.lib_base.request.BaseResp
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function

/**
 * 通用数据类型转换封装
 */
class ConvertDataFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (t.likelyFruitUndergroundBridge != "1000"  || t.rawArtGiftedTeam == null) {
            return Observable.error(ResultErrorException(if (t.rawArtGiftedTeam == null) "$FAILED_STATUS_NO_DATA" else t.likelyFruitUndergroundBridge, t.minibusForeignAtlanticSuffering))
        }
        return Observable.just(t.rawArtGiftedTeam)
    }

}
