package com.qf.xmall.sso.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.qf.xmall.sso.demo.controller.SSOController;
import com.qf.xmall.sso.demo.common.result.ResultModel;
import com.qf.xmall.sso.demo.common.dto.LoginResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class XmallSsoDemoApplicationTests {
    private MockMvc loginControllerMock;

    @Autowired
    private SSOController ssoController;

    @BeforeEach
    void setUp() {
        this.loginControllerMock = MockMvcBuilders.standaloneSetup(ssoController).build();
    }

    @Test
    void loginParameterTest() {
        assertDoesNotThrow(() -> {
            MvcResult res = this.loginControllerMock.perform(MockMvcRequestBuilders.post("/sso/login")
                    .content("{\"userPwd\":\"12345678\"}")
                    .contentType("application/json"))
                    .andReturn();
            assertEquals(200, res.getResponse().getStatus());
            String resModelStr = res.getResponse().getContentAsString();
            ResultModel<LoginResponse> resModel = new ObjectMapper().readValue(resModelStr, new TypeReference<ResultModel<LoginResponse>>() {});
            assertEquals(500, resModel.getCode());
        });
    }

    @Test
    void loginLogoutTest() {
        assertDoesNotThrow(() -> {
            MvcResult res = this.loginControllerMock.perform(MockMvcRequestBuilders.post("/sso/login")
                    .content("{\"userName\":\"test\",\"userPwd\":\"test\"}")
                    .contentType("application/json"))
                    .andReturn();
            assertEquals(200, res.getResponse().getStatus());
            String resModelStr = res.getResponse().getContentAsString();
            ResultModel<LoginResponse> resModel = new ObjectMapper().readValue(resModelStr, new TypeReference<ResultModel<LoginResponse>>() {});
            assertEquals(1000, resModel.getCode());

            String token = resModel.getResult().getToken();
            assertNotNull(token);
            assertTrue(token.length() > 0);
            
            MvcResult res2 = this.loginControllerMock.perform(MockMvcRequestBuilders.get("/sso/checkLogin")
                    .param("token", token))
                    .andReturn();
            String resModelStr2 = res2.getResponse().getContentAsString();
            ResultModel<LoginResponse> resModel2 = new ObjectMapper().readValue(resModelStr2, new TypeReference<ResultModel<LoginResponse>>() {});
            assertEquals(1000, resModel2.getCode());
            assertEquals(1, resModel2.getResult().getState());

            MvcResult res3 = this.loginControllerMock.perform(MockMvcRequestBuilders.get("/sso/loginOut")
                    .param("token", token))
                    .andReturn();
            String resModelStr3 = res3.getResponse().getContentAsString();
            ResultModel resModel3 = new ObjectMapper().readValue(resModelStr3, new TypeReference<ResultModel>() {});
            assertEquals(1000, resModel3.getCode());
            
            MvcResult res4 = this.loginControllerMock.perform(MockMvcRequestBuilders.get("/sso/checkLogin")
                    .param("token", token))
                    .andReturn();
            String resModelStr4 = res4.getResponse().getContentAsString();
            ResultModel<LoginResponse> resModel4 = new ObjectMapper().readValue(resModelStr4, new TypeReference<ResultModel<LoginResponse>>() {});
            assertEquals(1000, resModel4.getCode());
            assertEquals(0, resModel4.getResult().getState());
        });
    }
}
