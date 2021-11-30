package com.shopping.service;
import com.shopping.db.User;
import com.shopping.db.UserDAO;
import com.shopping.stubs.user.*;
import io.grpc.stub.StreamObserver;
import java.sql.SQLException;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUserDetails(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            UserDAO ud = new UserDAO();
            User user = null;
            user = ud.getDetails(request.getUserName());

            UserResponse.Builder urb = UserResponse.newBuilder()
                    .setId(user.getId())
                    .setUserName(user.getUserName())
                    .setName(user.getName())
                    .setAge(user.getAge())
                    .setGender(Gender.valueOf(user.getGender()));

            UserResponse userResponse = urb.build();
            responseObserver.onNext(userResponse);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
