package cn.icutool.service.adapter;

import cn.icutool.domain.vo.WxCaptchaVO;
import cn.icutool.domain.vo.response.WSBaseResp;
import cn.icutool.domain.vo.ws.WSLoginCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import cn.icutool.domain.enums.WSRespTypeEnum;

@Component
public class WSAdapter {
    public static WSBaseResp<WSLoginCode> buildLoginResp(WxCaptchaVO wxCaptchaVO) {
        WSBaseResp<WSLoginCode> wsBaseResp = new WSBaseResp<>();
        wsBaseResp.setType(WSRespTypeEnum.LOGIN_CODE.getType());
        wsBaseResp.setData(WSLoginCode.builder().loginCode(wxCaptchaVO.getCode()).build());
        return wsBaseResp;
    }

    public static WSBaseResp<String> buildStrResp(WSRespTypeEnum wSRespTypeEnum, String msg) {
        WSBaseResp<String> wsBaseResp = new WSBaseResp<>();
        wsBaseResp.setType(wSRespTypeEnum.getType());
        if (null == msg) {
            wsBaseResp.setData(wSRespTypeEnum.getDesc());
        } else {
            wsBaseResp.setData(msg);
        }
        return wsBaseResp;
    }

    public static <T> WSBaseResp<T> buildTData(WSRespTypeEnum wSRespTypeEnum, T data) {
        WSBaseResp<T> wsBaseResp = new WSBaseResp<>();
        wsBaseResp.setType(wSRespTypeEnum.getType());
        wsBaseResp.setData(data);
        return wsBaseResp;
    }
}
