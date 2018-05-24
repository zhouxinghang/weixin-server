package com.zxh.weixinserver.demo;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;

/**
 * Created by zhouxinghang on 2018/5/19.
 */
public class HelloDemo {

    public static void main(String[] args) {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId("wxed940e90d09b8da0"); // 设置微信公众号的appid
        config.setSecret("00daf89d45fa09b84fdcd9c44c349b89"); // 设置微信公众号的app corpSecret
        config.setToken("9_Dq6WvUlVEh26LcE-70K3jIIqtx8RZvrN077VR_h36SOmKmQg8WxZtu0wGDFGTuhp_MMh-Q23dizfvj8N6PT_LC8gJw8ZHnFKtFW6m6XKKfrIqytf10d50w-TP6jdQlgPiluKgvldPr73Qem4VJQhACALZG"); // 设置微信公众号的token
        config.setAesKey("..."); // 设置微信公众号的EncodingAESKey

        WxMpService wxService = new WxMpServiceImpl();// 实际项目中请注意要保持单例，不要在每次请求时构造实例，具体可以参考demo项目
        wxService.setWxMpConfigStorage(config);

// 用户的openid在下面地址获得
// https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=用户管理&form=获取关注者列表接口%20/user/get
        String openid = "...";
        WxMpKefuMessage message = WxMpKefuMessage.TEXT().toUser(openid).content("Hello World").build();
        try {
            wxService.getKefuService().sendKefuMessage(message);
        } catch (WxErrorException e) {
            System.out.println("发送出错");
            e.printStackTrace();
        }
    }
}
