//package com.commerce.agent.controller;
//
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class HttpController {
//    @RequestMapping("/testhttp")
//    public void testHttp(){
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        FormBody.Builder formBodyBuilder = new FormBody.Builder();
//        //        Set<String> keySet = paramsMap.keySet();
////        formBodyBuilder.add("content", contentInfo.getContent());
////        FormBody formBody = formBodyBuilder.build();
//        Request request = new Request
//                .Builder()
//                .get()
//                .url("http://127.0.0.1:8080/content")
//                .build();
//        try (Response response = mOkHttpClient.newCall(request).execute()) {
//            System.out.println(response.body().string());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
