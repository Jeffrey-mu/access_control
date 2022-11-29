package com.access.service;

import org.springframework.stereotype.Service;

//@Service
public class TransIsapiServise {
    public static String get_isapi(int lUserID, String url) {
        NetSDKDemo.HCNetSDK.NET_DVR_XML_CONFIG_INPUT struXMLInput = new NetSDKDemo.HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struXMLInput.read();
        NetSDKDemo.HCNetSDK.BYTE_ARRAY stringRequest = new NetSDKDemo.HCNetSDK.BYTE_ARRAY(1024);
        stringRequest.read();
        //输入ISAPI协议命令
        System.arraycopy(url.getBytes(), 0, stringRequest.byValue, 0, url.length());
        stringRequest.write();
        struXMLInput.dwSize = struXMLInput.size();
        struXMLInput.lpRequestUrl = stringRequest.getPointer();
        struXMLInput.dwRequestUrlLen = url.length();
        struXMLInput.lpInBuffer = null;
        struXMLInput.dwInBufferSize = 0;
        struXMLInput.write();

        NetSDKDemo.HCNetSDK.BYTE_ARRAY stringXMLOut = new NetSDKDemo.HCNetSDK.BYTE_ARRAY(8 * 1024);
        stringXMLOut.read();
        NetSDKDemo.HCNetSDK.BYTE_ARRAY struXMLStatus = new NetSDKDemo.HCNetSDK.BYTE_ARRAY(1024);
        struXMLStatus.read();
        NetSDKDemo.HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struXMLOutput = new NetSDKDemo.HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struXMLOutput.read();
        struXMLOutput.dwSize = struXMLOutput.size();
        struXMLOutput.lpOutBuffer = stringXMLOut.getPointer();
        struXMLOutput.dwOutBufferSize = stringXMLOut.size();
        struXMLOutput.lpStatusBuffer = struXMLStatus.getPointer();
        struXMLOutput.dwStatusSize = struXMLStatus.size();
        struXMLOutput.write();
        if (!UserAccessServe.hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struXMLInput, struXMLOutput)) {
            int iErr = UserAccessServe.hCNetSDK.NET_DVR_GetLastError();
            System.err.println("NET_DVR_STDXMLConfig失败，错误号" + iErr+"----URL:"+url);
            return null;
        } else {
            stringXMLOut.read();
            System.out.println("输出文本大小：" + struXMLOutput.dwReturnedXMLSize);
            //打印输出XML文本
            String strOutXML = new String(stringXMLOut.byValue).trim();
            System.out.println(strOutXML);
            struXMLStatus.read();
            String strStatus = new String(struXMLStatus.byValue).trim();
            System.out.println(strStatus);
            return strOutXML;
        }
    }


    public static String put_isapi(int lUserID, String url, String inputXml) {
        NetSDKDemo.HCNetSDK.NET_DVR_XML_CONFIG_INPUT struXMLInput = new NetSDKDemo.HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struXMLInput.read();
        NetSDKDemo.HCNetSDK.BYTE_ARRAY stringRequest = new NetSDKDemo.HCNetSDK.BYTE_ARRAY(1024);
        stringRequest.read();
        //输入ISAPI协议命令
        System.arraycopy(url.getBytes(), 0, stringRequest.byValue, 0, url.length());
        stringRequest.write();
        struXMLInput.dwSize = struXMLInput.size();
        struXMLInput.lpRequestUrl = stringRequest.getPointer();
        struXMLInput.dwRequestUrlLen = url.length();
        NetSDKDemo.HCNetSDK.BYTE_ARRAY ptrInBuffer = new NetSDKDemo.HCNetSDK.BYTE_ARRAY(inputXml.length());
        ptrInBuffer.read();
        System.arraycopy(inputXml.getBytes(), 0, ptrInBuffer.byValue, 0, inputXml.length());
        ptrInBuffer.write();
        struXMLInput.lpInBuffer = ptrInBuffer.getPointer();
        struXMLInput.dwInBufferSize = inputXml.length();
        struXMLInput.write();
        NetSDKDemo.HCNetSDK.BYTE_ARRAY stringXMLOut = new NetSDKDemo.HCNetSDK.BYTE_ARRAY(8 * 1024);
        stringXMLOut.read();
        NetSDKDemo.HCNetSDK.BYTE_ARRAY struXMLStatus = new NetSDKDemo.HCNetSDK.BYTE_ARRAY(1024);
        struXMLStatus.read();
        NetSDKDemo.HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struXMLOutput = new NetSDKDemo.HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struXMLOutput.read();
        struXMLOutput.dwSize = struXMLOutput.size();
        struXMLOutput.lpOutBuffer = stringXMLOut.getPointer();
        struXMLOutput.dwOutBufferSize = stringXMLOut.size();
        struXMLOutput.lpStatusBuffer = struXMLStatus.getPointer();
        struXMLOutput.dwStatusSize = struXMLStatus.size();
        struXMLOutput.write();
        if (!UserAccessServe.hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struXMLInput, struXMLOutput)){
            int iErr = UserAccessServe.hCNetSDK.NET_DVR_GetLastError();
            System.err.println("NET_DVR_STDXMLConfig失败，错误号" + iErr+"----URL:"+url);
            return null;
        } else {
            stringXMLOut.read();
            System.out.println("输出文本大小：" + struXMLOutput.dwReturnedXMLSize);
            //打印输出XML文本
            String strOutXML = new String(stringXMLOut.byValue).trim();
            struXMLStatus.read();
            String strStatus = new String(struXMLStatus.byValue).trim();
            return strOutXML;
        }
    }
}
