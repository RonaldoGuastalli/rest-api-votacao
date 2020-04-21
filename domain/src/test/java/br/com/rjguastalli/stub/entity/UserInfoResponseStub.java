package br.com.rjguastalli.stub.entity;

import br.com.rjguastalli.model.UserInfoEnum;
import br.com.rjguastalli.model.UserInfoResponse;

public class UserInfoResponseStub {

    public static UserInfoResponse userInfoResponseCpfAbleMock() {
        return UserInfoResponse.builder()
                .result(UserInfoEnum.ABLE_TO_VOTE)
                .build();
    }

    public static UserInfoResponse userInfoResponseCpfUnableMock() {
        return UserInfoResponse.builder()
                .result(UserInfoEnum.UNABLE_TO_VOTE)
                .build();
    }

    public static UserInfoResponse userInfoResponseCpfExceptionfMock() {
        return UserInfoResponse.builder()
                .result(UserInfoEnum.SOME_EXCEPTION_OCCURRED)
                .build();
    }
}
