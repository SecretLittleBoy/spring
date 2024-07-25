package com.qf.xmall.sso.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.qf.xmall.sso.demo.common.exception.SSOException;
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
            assertEquals(resModel.getCode(), 500);
        });
    }

    @Test
    void loginAdminTest() {
        assertDoesNotThrow(() -> {
            MvcResult res = this.loginControllerMock.perform(MockMvcRequestBuilders.post("/sso/login")
                    .content("{\"userName\":\"test\",\"userPwd\":\"test\"}")
                    .contentType("application/json"))
                    .andReturn();
            assertEquals(200, res.getResponse().getStatus());
            String resModelStr = res.getResponse().getContentAsString();
            ResultModel<LoginResponse> resModel = new ObjectMapper().readValue(resModelStr, new TypeReference<ResultModel<LoginResponse>>() {});
            assertEquals(resModel.getCode(), 1000);
        });
    }
}
