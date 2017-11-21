package com.lxg.springboot.controller;

import com.lxg.springboot.model.HttpResult;
import com.lxg.springboot.model.Token;
import com.lxg.springboot.service.HttpAPIService;
import com.lxg.springboot.util.AesCbcUtil;

import net.sf.json.JSONObject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhenghong on 2017/4/25.
 */
@RestController
public class WxApiController {

	@Value("${wx.appid}")
	private String appid;
	@Value("${wx.appSecret}")
	private String appSecret;

	@Resource
	private HttpAPIService httpAPIService;

	@RequestMapping("video/wx/getopenid")
	public String getopenid(String code) throws Exception {

		String url = "https://api.weixin.qq.com/sns/jscode2session?" + "appid=" + appid + "&" + "secret=" + appSecret
				+ "&" + "js_code=" + code + "&" + "grant_type=authorization_code";

		String res = httpAPIService.doGet(url);

		return res;

	}
	
	@RequestMapping("video/wx/access_token")
	public String getaccess_token() throws Exception {

		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&" + "secret=" + appSecret;

		String res = httpAPIService.doGet(url);

		return res;

	}

    @RequestMapping("video/wx/decodeUserInfo")
    public Map decodeUserInfo(String encryptedData, String iv, String openid , String session_key) {

        Map map = new HashMap();

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.fromObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }
	
	@RequestMapping("video/wx/getUnionID")
	public String getUnionID(String code,String token) throws Exception {

		String url = "GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&" + "openid=" + code
				+ "&" + "lang=zh_CN";

		String res = httpAPIService.doGet(url);

		return res;

	}
	
	@RequestMapping("video/wx/getliteopenid")
	public String getliteopenid(String code) throws Exception {

		String url = "https://api.weixin.qq.com/sns/jscode2session?" + "appid=wx6fabc07e4965d33e" + "&" + "secret=9356f5ed37a87e5cd930f134e86adf74"
				+ "&" + "js_code=" + code + "&" + "grant_type=authorization_code";

		String res = httpAPIService.doGet(url);

		return res;

	}
	
	@RequestMapping("video/wx/litesend")
	public HttpResult litesend(String openid, String templateid, String formid, String data, String page) throws Exception {

		// 获取token
		String urltoken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&" + "appid=wx6fabc07e4965d33e"
				+ "&" + "secret=9356f5ed37a87e5cd930f134e86adf74";

		Token token = JSON.parseObject(httpAPIService.doGet(urltoken), Token.class);

		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="
				+ token.getAccess_token();

		// 参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("touser", openid);
		map.put("template_id", templateid);
		map.put("form_id", formid);
		map.put("data", JSON.parse(data));
		map.put("page", page);
		map.put("emphasis_keyword", "keyword1.DATA");

		HashMap<String, Object> maptemp = new HashMap<String, Object>();
		maptemp.put("Jsondata", JSON.toJSONString(map));

		// 请求头
		HashMap<String, Object> header = new HashMap<String, Object>();

		HttpResult res = httpAPIService.doPostJson(url, maptemp, header);

		return res;

	}
	

	@RequestMapping("video/wx/send")
	public HttpResult send(String openid, String templateid, String formid, String data, String page) throws Exception {

		// 获取token
		String urltoken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&" + "appid=" + appid
				+ "&" + "secret=" + appSecret;

		Token token = JSON.parseObject(httpAPIService.doGet(urltoken), Token.class);

		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="
				+ token.getAccess_token();

		// 参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("touser", openid);
		map.put("template_id", templateid);
		map.put("form_id", formid);
		map.put("data", JSON.parse(data));
		map.put("page", page);
		map.put("emphasis_keyword", "keyword1.DATA");

		HashMap<String, Object> maptemp = new HashMap<String, Object>();
		maptemp.put("Jsondata", JSON.toJSONString(map));

		// 请求头
		HashMap<String, Object> header = new HashMap<String, Object>();

		HttpResult res = httpAPIService.doPostJson(url, maptemp, header);

		return res;

	}
	
	@RequestMapping("video/wx/getTwoBarCodes")
	public void  getTwoBarCodes(String StoreId,String StoreName,int width,HttpServletResponse response) throws Exception {
		// 获取token
		String urltoken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&" + "appid=" + appid
				+ "&" + "secret=" + appSecret;

		Token token = JSON.parseObject(httpAPIService.doGet(urltoken), Token.class);

		String url = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="
				+ token.getAccess_token();

		// 参数

		// 参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("path","pages/index/index"+"?StoreId="+ StoreId+"&StoreName="+ StoreName);
		map.put("width",width);

		HashMap<String, Object> maptemp = new HashMap<String, Object>();
		maptemp.put("Jsondata", JSON.toJSONString(map));

		// 请求头
		HashMap<String, Object> header = new HashMap<String, Object>();

		byte[] data = httpAPIService.doPostImg(url, maptemp, header);
		response.setContentType("image/png");

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
	}

}
